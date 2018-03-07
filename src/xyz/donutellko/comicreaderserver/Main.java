package xyz.donutellko.comicreaderserver;

import Parsers.*;
import com.google.gson.Gson;

import java.io.File;
		import java.lang.reflect.Constructor;

public class Main {

	static ComicParser[] comicParsers = new ComicParser[]{
			// new ComicParser(ReadmangaParser.class, "ruOnePunch", "http://readmanga.me/one_punch_man/vol0/9?mtr="),

			new ComicParser(SmbcParser.class, "enSMBC", "https://www.smbc-comics.com/comic/2002-09-05"),
			new ComicParser(XkcdParser.class, "enXKCD", "https://xkcd.com/1"),
			new ComicParser(XkcdRuParser.class, "ruXKCD", "https://xkcd.ru/1"),
			new ComicParser(DilbertParser.class, "enDilbert", "http://dilbert.com/strip/1989-04-16"),

			new ComicParser(ComicslateParser.class, "enFreefall", "https://comicslate.org/sci-fi/freefall/0001"),
			new ComicParser(ComicslateParser.class, "ruSamy", "https://comicslate.org/sci-fi/sammy/0001"),
			new ComicParser(ComicslateParser.class, "enGaMERCaT", "https://comicslate.org/gamer/gamercat/0001"),
			new ComicParser(ComicslateParser.class, "enTwoKinds", "https://comicslate.org/furry/2kinds/0001"),
			new ComicParser(ComicslateParser.class, "enLWHAG", "https://comicslate.org/gamer/lwhag/cover"),

			new ComicParser(AcomicsParser.class, "ruLWHAG", "https://acomics.ru/~LwHG/1"),
			new ComicParser(AcomicsParser.class, "ruOwlturd", "https://acomics.ru/~owlturd/1"),
			new ComicParser(AcomicsParser.class, "ruDoodleTime", "https://acomics.ru/~doodle-time/1"),
			new ComicParser(AcomicsParser.class, "ruGaMERCaT", "https://acomics.ru/~thegamercat/1"),

	};


    public static void main(String[] args) {
    	File workingPath = new File("Results");
    	if (!workingPath.mkdirs() && !workingPath.exists()) {
    		System.out.println("Failed to create the folder for results.");
    		return;
    	}
    	
		File pagesPath = new File(workingPath + "/pages/");

		FileWorker.save(new File(FileWorker.getCurrentPath() + "/Results/comiclist"), Defaults.getComicsList());
		System.out.println("Saved comiclist.");

		for (ComicParser cp : comicParsers) {
			System.out.println("Updating " + cp.shortName + ":");
			savePages(pagesPath, cp);
		}
    }

    static void savePages(File path, ComicParser comicParser) {
    	savePages(path, comicParser.parserClass, comicParser.shortName, comicParser.initialUrl);
	}

	/**
	 * @param path куда сохранять (или аппендить)
	 * @param tClass какой парсер использовать
	 * @param shortName краткое имя
	 * @param url Url начала по умолчанию (используется, если файл не существует), иначе продолжаем с последнего записанного
	 */
	private static <T extends UniversalParser> void savePages(File path, Class<T> tClass, String shortName, String url) {
    	File file = new File(path + "/" + shortName);
    	boolean create = true;
		if (file.exists()) {
			String pages = FileWorker.read(file) + "]";
			if (pages.length() > 5) {
				create = false;
				Comic.Page[] p = new Gson().fromJson(pages, Comic.Page[].class);
				url = append(tClass, null, p[p.length - 2].thisUrl); // Просто получаем ссылку на следующий
			}
		}

		if (create) {
			FileWorker.save(file, "[\n");
		}

		int count = 0;
		while (url != null && url.length() > 0) {
			url = append(tClass, file, url);
			count++;
		}
		System.out.println(count == 0 ? "No new pages." : "Added pages: " + count);
	}

    private static <T extends UniversalParser> String append(Class<T> tClass, File file, String url) {
    	String html = HttpWorker.getHtml(url);
		if (html != null && html.length() > 0) try {
			//UniversalParser.ParsedPage parsedPage = tClass.getDeclaredConstructor(String.class).newInstance(html).getParsedPage();
			Constructor c = tClass.getDeclaredConstructor(String.class, String.class);
			c.setAccessible(true);
			UniversalParser.ParsedPage parsedPage = ((UniversalParser) c.newInstance(url, html)).getParsedPage();
			if (file != null) FileWorker.append(file, new Gson().toJson(new Comic.Page(parsedPage)) + ",\n");

			return parsedPage.nextUrl;
		} catch (Exception e) { e.printStackTrace(); }

		return null;
	}

	private static class ComicParser {
		Class parserClass;
		String shortName, initialUrl;

		ComicParser(Class parserClass, String shortName, String initialUrl) {
			this.parserClass = parserClass;
			this.shortName = shortName;
			this.initialUrl = initialUrl;
		}
	}
}