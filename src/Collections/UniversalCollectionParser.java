package Collections;

import xyz.donutellko.comicreaderserver.*;

public abstract class UniversalCollectionParser extends AbstractParser {

	String url;
	private Comic[] parsed;
	private String nextUrl;

	UniversalCollectionParser(String url, String html) {
		this.url = url;
		parsed = getComicList(html);
		nextUrl = getNextUrl(html);
	}

	abstract Comic[] getComicList(String html);
	abstract String getNextUrl(String html);

	public Comic[] getParsed() {
		return parsed;
	}

	public String getNextUrl() {
		return nextUrl;
	}
}
