package Collections;

import xyz.donutellko.comicreaderserver.*;

import java.util.ArrayList;

public abstract class UniversalListParser extends AbstractParser {

	String url;
	private ArrayList<Comic> parsed;
	private String nextUrl;

	UniversalListParser(String url, String html) {
		this.url = url;
		parsed = getComicList(html);
		nextUrl = getNextUrl(html);
	}

	abstract ArrayList<Comic> getComicList(String html);
	abstract String getNextUrl(String html);

	public ArrayList<Comic> getList() {
		return parsed;
	}

	public String getNextUrl() {
		return nextUrl;
	}
}
