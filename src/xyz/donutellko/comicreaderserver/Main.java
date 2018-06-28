package xyz.donutellko.comicreaderserver;

import Collections.UniversalListParser;
import Collections.AcomicsListParser;
import Collections.MangalibListParser;
import Parsers.*;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean debug = false; // В этом режиме парсится не более testN страниц.
    static int testN = 5; // Столько страниц парсится для каждого
    static boolean dont_add_comics = false; // Отключает обновление каталога комиксов
    private static Object newPages;

    public static void main(String[] args) {
        parseArgs(args);

        // testListParser(); if (true) return; // Тестирование работы парсеров коллекций

        DbConnectionOracle.initDb();

        updateComicsList();

        updateComicsPages();

        DbConnectionOracle.closeDb();
    }

    private static void updateComicsList() {
        //updateComicList(AcomicsListParser.class, AcomicsListParser.INITIAL_URL);
        //updateComicList(MangalibListParser.class, MangalibListParser.INITIAL_URL);
    }

    private static <T extends UniversalListParser> void updateComicList(
            Class<T> tClass, String initialUrl) {
        Constructor c;
        try {
            c = tClass.getDeclaredConstructor(String.class, String.class);
            c.setAccessible(true);

            String url = initialUrl;
            do {
                String html = HttpWorker.getHtml(url);
                if (html == null)
                    break;
                UniversalListParser p = (UniversalListParser) c.newInstance(url, html);
                List<Comic> list = p.getList();

                ComicDB.addComicList(list);

                url = p.getNextUrl();
            } while (url != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateComicsPages() {
        ResultSet rset = ComicDB.getComics();
        assert rset != null;

        for (ComicDB.DbComic c : getComicsFromDb(rset)) {
            if (c.init_url == null || c.init_url.length() == 0) {
                System.out.println("\nNo initial URL found for " + c.title);
                continue;
            }
            parse(c);
        }
    }

    private static void initDb() {
        try {
            DbConnection.connect();
            DbConnection.initialise();
        } catch (Exception e) { // SQLException, ClassNotFoundException
            e.printStackTrace();
        }
    }

    private static void parse(ComicDB.DbComic c) {
        System.out.println("\nUpdating " + c.title);

        Class parser = getParser(c.source);
        if (parser == null) {
            System.out.println("Unable to find parser: " + c.source);
        } else {
            try {
                savePages(parser, c.comic_id, c.init_url);
            } catch (Exception e) {
                System.out.println("Exception while getting " + c.title);
                e.printStackTrace();
            }
        }
    }

    private static List<ComicDB.DbComic> getComicsFromDb(ResultSet rset) {
        System.out.println("Getting comics from Db");
        List<ComicDB.DbComic> result = new ArrayList<>();

        try {
            while (rset.next())
                result.add(new ComicDB.DbComic(rset));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Got " + result.size() + " comics to update.");

        return result;
    }

    private static void parseArgs(String[] args) {
        for (String c : args)
            switch (c) {
                case "-debug":
                    debug = true; // Включение дебага
                    System.out.println("Debug mode on. Only " + testN
                            + " pages per comic will be parsed. ");
                    break;
                case "-dac":
                    dont_add_comics = true; // Отключение добавления новых комиксов из каталогов
                    System.out.println("Adding new comics from catalogs disabled.");
                    break;
                case "-h":
                    System.out.println("Available options: \n" +
                            "\t-debug   Add only 5 pages per comic\n" +
                            "\t-dac     Disable adding new comics from catalogs");
                default:
                    System.out.println("Unknown option " + c + "\nUse -h to print help.");
            }
    }

    private static Class getParser(String source) {
        switch (source.toLowerCase()) {
            case "ensmbc":
                return EnSmbcParser.class;
            case "enxkcd":
                return EnXkcdParser.class;
            case "ruxkcd":
                return RuXkcdParser.class;
            case "endilbert":
                return EnDilbertParser.class;
            case "mangalib":
                return MangalibParser.class;
            case "comicslate":
                return ComicslateParser.class;
            case "acomics":
                return AcomicsParser.class;
            case "readmanga":
                return ReadmangaParser.class;
            case "tf2old":
                return TeamfortressOldParser.class;
            default:
                return null;
        }
    }

    /**
     * @param tClass  какой парсер использовать
     * @param comicId краткое имя
     * @param initUrl Url начала по умолчанию (используется, если файл не существует),
     *                иначе продолжаем с последнего записанного
     */
    private static <T extends AbstractParser> void savePages(
            Class<T> tClass, int comicId, String initUrl) throws SQLException {

        ComicDB.createPagesTable(comicId);

        ComicDB.DbPage page = ComicDB.getLastPage(comicId);
        ArrayList<SinglePageParser.ParsedPage> tmppages;
        String nextUrl = initUrl;
        if (page != null && page.thisUrl != null) {
            ArrayList<AbstractParser.ParsedPage> tmp = getPages(tClass, page.thisUrl);
            nextUrl = tmp.get(0).nextUrl;
        }

        int max = debug ? testN : -1;
        int count = 1;
        do {
            tmppages = getPages(tClass, nextUrl);
            if (tmppages == null) {
                System.out.println("Unable to generate parsed page!");
                return;
            }
            nextUrl = tmppages.get(tmppages.size() - 1).nextUrl;
            for (AbstractParser.ParsedPage tmppage : tmppages) {
                page = new ComicDB.DbPage(tmppage);
                ComicDB.addPage(comicId, page);
                count++;
            }
        } while (nextUrl != null && --max != 1);
        System.out.println("Added new pages: " + count);
    }

    private static <T extends AbstractParser> ArrayList<SinglePageParser.ParsedPage> getPages(
            Class<T> tClass, String url) {

        String html = HttpWorker.getHtml(url);
        if (html != null && html.length() > 0) try {
            //SinglePageParser.ParsedPage parsedPage =
            //   tClass.getDeclaredConstructor(String.class).newInstance(html).getParsedPage();
            Constructor c = tClass.getDeclaredConstructor(String.class, String.class);
            c.setAccessible(true);
            ArrayList<AbstractParser.ParsedPage> parsedPages =
                    ((AbstractParser) c.newInstance(url, html)).getParsedPages();

            return parsedPages;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void closeDb() {
        try {
            DbConnection.close();
        } catch (Exception e) { // SQLException, ClassNotFoundException
            e.printStackTrace();
        }
    }
}
