package Parsers;

import static xyz.donutellko.comicreaderserver.Util.*;

public class RuXkcdParser extends SinglePageParser {
	RuXkcdParser(String url, String html) {
		super(url, html);
	}

	@Override
	protected String getAlias() {
		return "ruxkcd";
	}

	@Override
	protected String getTitle(String html) {
		return getByBegin(html, "<h1>", "</h1>");
	}

	@Override
	protected String getDescription(String html) {
		return getByBegin(html, "<div class=\"comics_text\">", "</div>");
	}

	@Override
	protected String getImgUrl(String html) {
		return getByEnd(html, "src=\"", "\" alt=");
	}

	@Override
	protected String getBonusUrl(String html) {
		return null;
	}

	@Override
	protected String getNextUrl(String html) {
		String tmp = getByEnd(html, "href=\"", "\">сюда");
		return tmp == null || tmp.equals("#") ? null : "https://xkcd.ru" + tmp;
	}


}













