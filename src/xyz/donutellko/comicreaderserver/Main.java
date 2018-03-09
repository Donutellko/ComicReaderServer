package xyz.donutellko.comicreaderserver;

import Parsers.*;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static boolean debug = false; // В этом режиме парсится не более testN страниц.
	static int testN = 5; // Столько страниц парсится для каждого комикса

    public static void main(String[] args) {
    	if (args.length > 0 && args[0].equals("-debug")) {
    		debug = true; // Включение дебага
			System.out.println("Debug mode on. Only " + testN + " pages per comic will be parsed. ");
		}

		try {
			DbConnection.connect();
			DbConnection.initialise();


			ResultSet rset = ComicDB.getComics();
			List<ComicDB.DbComic> comiclist = new ArrayList<>();
			while (rset.next()) {
				comiclist.add(new ComicDB.DbComic(rset));
			}

			for (ComicDB.DbComic c : comiclist) {
				if (c.initUrl == null || c.initUrl.length() == 0) {
					System.out.println("\nNo initial URL found for " + c.title);
					continue;
				}

				System.out.println("\nUpdating " + c.title);
				Class parser = getParser(c.source);
				if (parser == null)
					System.out.println("Unable to find parser: " + c.source);
				else
					try {
						savePages(parser, c.comicId, c.initUrl);
					} catch (Exception e) {
						System.out.println("Exception while getting " + c.title);
						e.printStackTrace();
					}
			}

			DbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static Class getParser(String source) {
    	switch (source.toLowerCase()) {
			case "ensmbc" 		: return EnSmbcParser.class;
			case "enxkcd" 		: return EnXkcdParser.class;
			case "ruxkcd" 		: return RuXkcdParser.class;
			case "endilbert" 	: return EnDilbertParser.class;

			case "comicslate" 	: return ComicslateParser.class;
			case "acomics" 		: return AcomicsParser.class;
			case "readmanga" 	: return ReadmangaParser.class;
			default: return null;
		}
	}

	/**
	 * @param tClass какой парсер использовать
	 * @param comicId краткое имя
	 * @param initUrl Url начала по умолчанию (используется, если файл не существует), иначе продолжаем с последнего записанного
	 */
	private static <T extends UniversalParser> void savePages(Class<T> tClass, int comicId, String initUrl) throws SQLException {
		ComicDB.createPagesTable(comicId);

		ComicDB.DbPage page = ComicDB.getLastPage(comicId);
		UniversalParser.ParsedPage tmppage = null;
		String nextUrl = initUrl;
		if (page != null && page.thisUrl != null) nextUrl = getPage(tClass, page.thisUrl).nextUrl;

		int max = testN;
		int count = 1;
		do {
			tmppage = getPage(tClass, nextUrl);
			if (tmppage == null) {
				System.out.println("Unable to generate parsed page!");
				return;
			}
			nextUrl = tmppage.nextUrl;
			page = new ComicDB.DbPage(tmppage);
			ComicDB.addPage(comicId, page);
			count++;
		} while (tmppage.nextUrl != null && --max > 1);
		System.out.println("Added new pages: " + count);
	}

    private static <T extends UniversalParser> UniversalParser.ParsedPage getPage(Class<T> tClass, String url) {
    	String html = HttpWorker.getHtml(url);
		if (html != null && html.length() > 0) try {
			//UniversalParser.ParsedPage parsedPage = tClass.getDeclaredConstructor(String.class).newInstance(html).getParsedPage();
			Constructor c = tClass.getDeclaredConstructor(String.class, String.class);
			c.setAccessible(true);
			UniversalParser.ParsedPage parsedPage = ((UniversalParser) c.newInstance(url, html)).getParsedPage();

			return parsedPage;
		} catch (Exception e) { e.printStackTrace(); }

		return null;
	}
}
