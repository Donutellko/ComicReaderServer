package Collections;

import xyz.donutellko.comicreaderserver.*;

import java.util.ArrayList;

public abstract class UniversalListParser {

	protected String url, html;

	UniversalListParser(String url, String html) {
		this.url = url;
		this.html = html;
	}

	public abstract String getAlias();
	protected abstract ArrayList<Comic> getComicList(String html);
	protected abstract String getNextUrl(String html);

	public ArrayList<Comic> getList() {
		return getComicList(html);
	}

	public String getNextUrl() {
		return getNextUrl(html);
	}
}
