package Parsers;

@SuppressWarnings("UnnecessaryLocalVariable")
public class AcomicsParser extends UniversalParser {

	public AcomicsParser(String url, String html) {
		super(url, html);
	}

	@Override
	protected String getAlias() {
		return "acomics";
	}

	@Override
	protected String getTitle(String html) {
		return null;
	}

	@Override
	protected String getDescription(String html) {
		String tmp = getByEnd(html,"alt=\"","\" onload=\"mainImageLoad()\"");
		return tmp;
	}

	@Override
	protected String getImgUrl(String html) {
		String tmp = getByBegin(html,"mainImage\" src=\"", "\"");
		return tmp == null ? null : "https://acomics.ru" + tmp;
	}

	@Override
	protected String getBonusUrl(String html) {
		return null;
	}

	@Override
	protected String getNextUrl(String html) {
		String tmp = getByEnd(html, "<a href=\"", "\" class=\"button large comic-nav-next");
		return tmp; //Например: <a href="https://acomics.ru/~LwHG/319" class="button large comic-nav-next"><span class="icon icon-next"></span></a>
	}
}
