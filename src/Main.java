import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    final static String server_url = "http://donutellko.azurewebsites.net/";

	static int counter = 10;

    public static void main(String[] args) {
        SmbcParser s = new SmbcParser(Defaults.smbcSample);

        //addPages(SmbcParser.class,"SMBC", "https://www.smbc-comics.com/comic/2002-09-05");
        appendPages(SmbcParser.class,"SMBC", "https://www.smbc-comics.com/comic/2002-09-05");

        System.out.println("Done");
    }

    public static <T extends UniversalParser> void appendPages(Class<T> tClass, String shortName, String url) {
		File path = new File(new File("").getAbsolutePath() + "/comic");
		path.mkdirs();

		while (url != null && url.length() > 0) {
			//next = add(tClass, result, next);
			url = append(tClass, new File(path + "/" + shortName), url);
		}
	}

    public static <T extends UniversalParser> void addPages(Class<T> tClass, String shortName, String url) {
		File path = new File(new File("").getAbsolutePath() + "/comic");
		List<TmpPage> result = new ArrayList<>();

		while (url != null && url.length() > 0) {
			url = add(tClass, result, url);
		}

		if (!result.isEmpty()) {
			path.mkdirs();
			FileWorker.save(new File(path + "/" + shortName), new Gson().toJson(result.toArray(new TmpPage[result.size()])).replace("},{", "},\n{"));
		}
	}

	/** Добавляет в переданный список объект страницы пропарсенной по переданному URL, возвращшает URL следующей
	 * @param tClass Класс парсера, унаследованный от UniversalParser
	 * @return Возвращает url следующей страницы или null в случае ошибки или отсутствия ссылки на следующую
	 */
    private static <T extends UniversalParser> String add(Class<T> tClass, List<TmpPage> list, String url) {

		//if (counter-- > 0)
			try {
				String html = HttpWorker.getHtml(url);
				UniversalParser up = tClass.getDeclaredConstructor(String.class).newInstance(html);
				up.thisUrl = url;
				list.add(up.getTmpPage());

				return up.nextUrl;
				//if (up.nextUrl != null)
				//	list.addAll(getRecursively(tClass, up.nextUrl)); // Вызывает StackOverflow
			} catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    private static <T extends UniversalParser> String append(Class<T> tClass, File file, String url) {
		List<TmpPage> tmp = new ArrayList<>(1);
		add(tClass, tmp, url);
		if (tmp.size() > 0) {
			FileWorker.append(file, new Gson().toJson(tmp.get(0)) + ",\n");
			return tmp.get(0).nextUrl;
		}
		return null;
	}

}

class TmpPage {
    String title, nextUrl, description, img_url, bonus_url, this_url;

    public TmpPage(String title, String next_url, String description, String img_url, String bonus_url, String this_url) {
        this.title = title;
        this.nextUrl = next_url;
        this.description = description;
        this.img_url = img_url;
        this.bonus_url = bonus_url;
        this.this_url = this_url;
    }
}