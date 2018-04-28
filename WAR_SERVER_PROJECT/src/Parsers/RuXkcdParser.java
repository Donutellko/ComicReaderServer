package Parsers;

public class RuXkcdParser extends UniversalParser {
	RuXkcdParser(String url, String html) {
		super(url, html);
	}

	@Override
	String getTitle(String html) {
		return getByBegin(html, "<h1>", "</h1>");
	}

	@Override
	String getDescription(String html) {
		return getByBegin(html, "<div class=\"comics_text\">", "</div>");
	}

	@Override
	String getImgUrl(String html) {
		return getByEnd(html, "src=\"", "\" alt=");
	}

	@Override
	String getBonusUrl(String html) {
		return null;
	}

	@Override
	String getNextUrl(String html) {
		String tmp = getByEnd(html, "href=\"", "\">сюда");
		return tmp == null || tmp.equals("#") ? null : "https://xkcd.ru" + tmp;
	}


}













