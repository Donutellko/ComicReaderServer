package Parsers;

import com.google.gson.Gson;
import xyz.camelteam.comicreaderserver.Comic;

import java.io.File;

public abstract class ComicslateParser {
	String url;
	String urlPart;

	/**
	 *
	 * @param urlPart вида sci-fi/freefall/
	 */
	public ComicslateParser(String urlPart) {
		if (urlPart.charAt(urlPart.length() - 1) != '/')
			urlPart += '/';
		this.urlPart = urlPart;
		this.url = "https://comicslate.org/" + urlPart;
	}

	protected abstract String getHtml(String url);

	protected abstract void append(File f, String s);

	public void savePages(File file) {
		int count = getCount(url);

		for (int i = 1; i <= count; i++) {
			String name = getNumber(i);
			Comic.Page p = new Comic.Page();
			p.imgUrl = "https://comicslate.org/_media/" + urlPart + name + extention(urlPart, i);
			p.thisUrl = url + name;
			p.title = "Page " + i;
			p.description = "Page " + i;

			append(file, new Gson().toJson(p)+ ",\n");
		}
	}

	public String extention(String urlPart, int i) {
		switch (urlPart) {
			case "sci-fi/freefall": return i < 1700 ? ".jpg" : ".png";
			case "gamer/gamercat/": return ".jpg";
			case "gamer/lwhag/": return ".jpg";
			case "sci-fi/sammy/": return ".png";
			case "furry/2kinds/": return ".jpg";
			default: return ".jpg";
		}
	}

	private int getCount(String url) {
		String html = getHtml(url + "index");

		String to_find = "title=\"Сейчас\" href=\"./";
		int length = to_find.length();
		int tmp;
		do {
			tmp = html.lastIndexOf(to_find);
		} while (!Character.isDigit(html.charAt(tmp + length)));

		return Integer.parseInt(html.substring(tmp + length, tmp + length + 4));
	}

	// Создаёт четырёхсимвольный номер (т.к. в URL номера вида 0024)
	static String getNumber(int number) {
		String n = (number + "");
		while (n.length() < 4) n = "0" + n;
		return n;
	}
}
/*

	/** Скармливать это: http://freefall.purrsia.com/default.htm
	 * Ищется вот эта строчка: a href="/ff3100/fc03066.htm">Previous, к числу прибавляется единица
	 * *
	public static int getCurrentPage(String html) {
		String a = "<TITLE>Freefall ";
		int b = html.indexOf(a) + a.length();
		String tmp = html.substring(b, b + 4);
		return  Integer.parseInt(tmp);
	}
	private static  void saveComicslatePages(File path, int number, int max) {
		if (max == -1) {
			String html = HttpWorker.getHtml("http://freefall.purrsia.com/default.htm");
			max = getCurrentPage(html);
		}

		File file = new File(path + "/" + "Freefall");
		FileWorker.save(file, "[");

		for (int i = number; i < max; i++) {
			FileWorker.append(file, new Gson().toJson(ComicslateParser.getPage(i)) + ",\n");
		}

		FileWorker.append(file, "]");
	}
}
*/