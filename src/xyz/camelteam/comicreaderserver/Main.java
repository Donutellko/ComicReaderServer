package xyz.camelteam.comicreaderserver;

import Parsers.*;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) {
    	File workingPath = new File("Results");
    	if (!workingPath.mkdirs() && !workingPath.exists()) {
    		System.out.println("Failed to create the folder for results.");
    		return;
    	}
		File pagesPath = new File(workingPath + "/pages/");

		String DEFAULT = "enfreefall";
    	String parameter = args.length == 0 ? DEFAULT : args[0]; // Чтоб можно было передавать в качестве аргумента программе
    	switch (parameter.toUpperCase()) {
			case "-h" : case "--help" :
				System.out.println("Use comic name to update it or 'comiclist; to update it. Comic names:");
				for (Comic c : Defaults.comicsList) System.out.println(c.shortName + "\t: " + c.name);
				break;
			case "COMICLIST":
				FileWorker.save(new File(FileWorker.getCurrentPath() + "/Results/comiclist"), Defaults.getComicsList());
				System.out.println("Saved comiclist.");
				break;
			case "ENSMBC":
				savePages(pagesPath, SmbcParser.class, "enSMBC", "https://www.smbc-comics.com/comic/2002-09-05");
				break;
			case "ENXKCD":
				savePages(pagesPath, XkcdParser.class, "enXKCD", "https://xkcd.com/1");
				break;
			case "RUXKCD":
				savePages(pagesPath, XkcdRuParser.class, "ruXKCD", "https://xkcd.ru/1");
				break;
			case "ENFREEFALL":
				savePages(pagesPath, ComicslateParser.class,"enFreefall", "https://comicslate.org/sci-fi/freefall/0001");
				break;
			case "ENGAMERCAT":
				savePages(pagesPath, ComicslateParser.class, "enGaMERCaT", "https://comicslate.org/gamer/gamercat/0001");
				break;
			case "ENTWOKINDS":
				savePages(pagesPath, ComicslateParser.class, "enTwoKinds", "https://comicslate.org/furry/2kinds/0001");
				break;
			case "RUSAMMY":
				savePages(pagesPath, ComicslateParser.class, "ruSamy", "https://comicslate.org/sci-fi/sammy/0001");
				break;
			case "ENLWHAG":
				savePages(pagesPath, ComicslateParser.class, "enLWHAG", "https://comicslate.org/gamer/lwhag/0001");
				break;
			case "RULWHAG":
				savePages(pagesPath, AcomicsParser.class, "ruLWHAG", "https://acomics.ru/~LwHG/1");
				break;
			case "RUOWLTURD":
				savePages(pagesPath, AcomicsParser.class, "ruOwlturd", "https://acomics.ru/~owlturd/1");
				break;
			case "RUDOODLETIME":
				savePages(pagesPath, AcomicsParser.class, "ruDoodleTime", "https://acomics.ru/~doodle-time/1");
				break;
			case "RUGAMERCAT":
				savePages(pagesPath, AcomicsParser.class, "ruGaMERCaT", "https://acomics.ru/~thegamercat/1");
				break;
			default:
			System.out.println("Unknown parameter. Use '-h' parameter to see, what you can do. ");
		}
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
}