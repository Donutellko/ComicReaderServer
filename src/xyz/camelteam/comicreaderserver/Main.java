package xyz.camelteam.comicreaderserver;

import Parsers.ComicslateParser;
import Parsers.SmbcParser;
import Parsers.UniversalParser;
import Parsers.XkcdParser;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Constructor;

public class Main {
    final static String server_url = "http://donutellko.azurewebsites.net/";

    public static void main(String[] args) {
    	File workingPath = new File("Results");
    	if (!workingPath.mkdirs() && !workingPath.exists()) {
    		System.out.println("Failed to create the folder for results.");
    		return;
    	}
		File pagesPath = new File(workingPath + "/pages/");

		String DEFAULT = "lwhag";
    	String parameter = args.length == 0 ? DEFAULT : args[0];
    	switch (parameter.toUpperCase()) {
			case "COMICLIST":
				FileWorker.save(new File(FileWorker.getCurrentPath() + "/Results/comiclist"), new Gson().toJson(Defaults.comicsList));
				break;
			case "SMBC":
				savePages(pagesPath, SmbcParser.class, "SMBC", "https://www.smbc-comics.com/comic/2002-09-05");
				break;
			case "XKCD":
				savePages(pagesPath, XkcdParser.class, "XKCD", "https://xkcd.com/1/");
				break;
			case "FREEFALL":
				saveComicslatePages(pagesPath, "Freefall", "sci-fi/freefall/");
				break;
			case "GAMERCAT":
				saveComicslatePages(pagesPath, "GaMERCaT", "gamer/gamercat/");
				break;
			case "LWHAG":
				saveComicslatePages(pagesPath, "LWHAG", "gamer/lwhag/");
				break;
			case "TWOKINDS":
				saveComicslatePages(pagesPath, "TwoKinds", "furry/2kinds/");
				break;
			default:
				System.out.println("Unknown parameter. You should use comics' short names (i.e. SMBC, XKCD, SeqArt, GaMERCaT, LWHAG...).");
		}

        System.out.println("Done");
    }

	public static <T extends UniversalParser> void savePages(File path, Class<T> tClass, String shortName, String url) {
    	File file = new File(path + "/" + shortName);
		if (file.exists()) {
			String pages = FileWorker.read(file) + "]";
			if (pages != null) {
				Comic.Page[] p = new Gson().fromJson(pages, Comic.Page[].class);
				url = append(tClass, null, p[p.length - 2].thisUrl); // Просто получаем ссылку на следующий
			}
		} else {
			FileWorker.save(file, "[");
		}

		while (url != null && url.length() > 0) {
			url = append(tClass, file, url);
		}

		// FileWorker.append(file, "]"); // По идее, это не нужно и позволит просто аппендить новые стрнаицы
	}

    private static <T extends UniversalParser> String append(Class<T> tClass, File file, String url) {
    	String html = HttpWorker.getHtml(url);
		try {
			//UniversalParser.ParsedPage parsedPage = tClass.getDeclaredConstructor(String.class).newInstance(html).getParsedPage();
			Constructor c = tClass.getDeclaredConstructor(String.class, String.class);
			c.setAccessible(true);
			UniversalParser.ParsedPage parsedPage = ((UniversalParser) c.newInstance(url, html)).getParsedPage();
			if (file != null) FileWorker.append(file, new Gson().toJson(new Comic.Page(parsedPage)) + ",\n");

			return parsedPage.nextUrl;
		} catch (Exception e) { e.printStackTrace(); }


		return null;
	}

	private static void saveComicslatePages(File path, String shortName, String url_part) {
    	File file = new File(path.getAbsolutePath() + "/" + shortName);
//    	if (!file.exists())
    		FileWorker.save(file, "["); // Временно, пока не написан нормальный алгоритм парсинга с Comicslate

    	new ComicslateParser(url_part) {
			@Override
			protected String getHtml(String url) {
				return HttpWorker.getHtml(url);
			}

			@Override
			protected void append(File f, String s) {
				FileWorker.append(f, s);
			}
		}.savePages(file);
    	// FileWorker.append(file, "]"); // По идее, это не нужно и позволит просто аппендить новые стрнаицы
	}
}