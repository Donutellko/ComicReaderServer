package Parsers;

public abstract class UniversalParser extends AbstractParser {
	String url, html;

	UniversalParser(String url, String html) {
		this.url = url;
		this.html = html;
	}

	abstract String getAlias();


	/** @param html HTML-код страницы
	 * @return Название страницы */
	protected abstract String getTitle(String html);
	protected abstract String getDescription(String html);
	protected abstract String getImgUrl(String html);
	protected abstract String getBonusUrl(String html);
	protected abstract String getNextUrl(String html);

	public ParsedPage getParsedPage() {
		String title = unescapeUtfAndHtml(getTitle(html));
		String description = unescapeUtfAndHtml(getDescription(html));
		String imgUrl = getImgUrl(html);
		String bonusUrl = getBonusUrl(html);
		String nextUrl = getNextUrl(html);

		return new ParsedPage(title, description, url, imgUrl, bonusUrl, nextUrl);
	}

	public static class ParsedPage {
		public String title, description, thisUrl, imgUrl, bonusUrl, nextUrl;

		public ParsedPage(String title, String description, String thisUrl, String imgUrl, String bonusUrl, String nextUrl) {
			this.title = title;
			this.description = description;
			this.thisUrl = thisUrl;
			this.imgUrl = imgUrl;
			this.bonusUrl = bonusUrl;
			this.nextUrl = nextUrl;
		}
	}
}