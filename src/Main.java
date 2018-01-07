import com.google.gson.Gson;

import java.io.File;

public class Main {
    final static String server_url = "http://donutellko.azurewebsites.net/";

    public static void main(String[] args) {
    	File workingPath = new File("Results");
    	if (!workingPath.mkdirs() && !workingPath.exists()) {
    		System.out.println("Failed to create the folder for results.");
    		return;
    	}
		File pagesPath = new File(workingPath + "/pages/");

		String DEFAULT = "XKCD";
    	String parameter = args.length == 0 ? DEFAULT : args[0];
    	switch (parameter) {
			case "comiclist":
				FileWorker.save(new File(FileWorker.getCurrentPath() + "/Results/comiclist"), new Gson().toJson(Defaults.comicsList));
				break;
			case "SMBC":
				savePages(pagesPath, SmbcParser.class, "SMBC", "https://www.smbc-comics.com/comic/2002-09-05");
				break;
			case "XKCD":
				savePages(pagesPath, XkcdParser.class, "XKCD", "https://xkcd.com/1/");
				break;
			default:
				System.out.println("Unknown parameter. You should use comics' short names (i.e. SMBC, XKCD, SeqArt...).");
		}

        System.out.println("Done");
    }

	public static <T extends UniversalParser> void savePages(File path, Class<T> tClass, String shortName, String url) {
    	File file = new File(path + "/" + shortName);
		FileWorker.save(file, "[");

		while (url != null && url.length() > 0) {
			url = append(tClass, file, url);
		}

		FileWorker.append(file, "]");
	}

    private static <T extends UniversalParser> String append(Class<T> tClass, File file, String url) {
    	String html = HttpWorker.getHtml(url);
		try {
			UniversalParser.ParsedPage parsedPage = tClass.getDeclaredConstructor(String.class).newInstance(html).getParsedPage();
			FileWorker.append(file, new Gson().toJson(new Comic.Page(parsedPage)) + ",\n");

			return parsedPage.nextUrl;
		} catch (Exception e) { e.printStackTrace(); }


		return null;
	}
}