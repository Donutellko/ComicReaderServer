package Parsers;

//@SuppressWarnings("UnnecessaryLocalVariable")
public class AcomicsParser extends UniversalParser {

	AcomicsParser(String url, String html) {
		super(url, html);
	}

	@Override
	String getTitle(String html) {
		return null;
	}

	@Override
	String getDescription(String html) {
		String tmp = getByEnd(html,"alt=\"","\" onload=\"mainImageLoad()\"");
		return tmp;
	}

	@Override
	String getImgUrl(String html) {
		String tmp = getByBegin(html,"mainImage\" src=\"", "\"");
		return tmp == null ? null : "https://acomics.ru" + tmp;
	}

	@Override
	String getBonusUrl(String html) {
		return null;
	}

	@Override
	String getNextUrl(String html) {
		String tmp = getByEnd(html, "<a href=\"", "\" class=\"button large comic-nav-next");
		return tmp; //Например: <a href="https://acomics.ru/~LwHG/319" class="button large comic-nav-next"><span class="icon icon-next"></span></a>
	}
}
