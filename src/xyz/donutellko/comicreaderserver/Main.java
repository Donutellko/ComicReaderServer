package xyz.donutellko.comicreaderserver;

import Parsers.*;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
		try {
			DbConnection.connect();
			DbConnection.initialise();

			ResultSet resultSet = ComicDB.getComics();
			while (resultSet.next()) {
				ComicDB.DbComic c = new ComicDB.DbComic(resultSet);
				System.out.println("Updating " + c.title);
				Class parser = getParser(c.source);
				if (parser == null)
					System.out.println("Unable to find parser: " + c.source);
				else
					savePages(parser, c.comicId, c.initUrl);
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
		if (page != null && page.thisUrl != null) nextUrl = getNextPage(tClass, page.thisUrl).nextUrl;

		do {
			tmppage = getNextPage(tClass, nextUrl);
			nextUrl = tmppage.nextUrl;
			page = new ComicDB.DbPage(tmppage);
			ComicDB.addPage(comicId, page);
		} while (tmppage.nextUrl != null);
	}

    private static <T extends UniversalParser> UniversalParser.ParsedPage getNextPage(Class<T> tClass, String url) {
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
