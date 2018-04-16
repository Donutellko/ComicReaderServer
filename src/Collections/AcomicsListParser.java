package Collections;

import xyz.donutellko.comicreaderserver.Comic;

public class AcomicsListParser extends UniversalCollectionParser {

	AcomicsListParser(String url, String html) {
		super(url, html);
	}

	@Override
	Comic[] getComicList(String html) {

		return new Comic[0];
	}

	@Override
	String getNextUrl(String html) {
		return null;
	}
}
