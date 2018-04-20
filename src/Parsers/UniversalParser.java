package Parsers;

import xyz.donutellko.comicreaderserver.AbstractParser;

public abstract class UniversalParser extends AbstractParser {

	private ParsedPage parsedPage = new ParsedPage();
	String url;

	UniversalParser(String url, String html) {
		this.url = url;
		preExecute();

		String title = unescapeUtfAndHtml(getTitle(html));
		String description = unescapeUtfAndHtml(getDescription(html));
		String imgUrl = getImgUrl(html);
		String bonusUrl = getBonusUrl(html);
		String nextUrl = getNextUrl(html);

		parsedPage.set(title, description, url, imgUrl, bonusUrl, nextUrl);
	}

	/**
	 * Метод позволяет выполнить код перед вызовом всех остальных функций
	 */
	protected void preExecute() {}

	/** @param html HTML-код страницы
	 * @return Название страницы */
	abstract String getTitle(String html);
	abstract String getDescription(String html);
	abstract String getImgUrl(String html);
	abstract String getBonusUrl(String html);
	abstract String getNextUrl(String html);

	public ParsedPage getParsedPage() {
		return parsedPage;
	}

	public static class ParsedPage {
		public String title, description, thisUrl, imgUrl, bonusUrl, nextUrl;

		public ParsedPage() {
			title = description = imgUrl = bonusUrl = thisUrl = nextUrl = null;
		}

		void set(String title, String description, String thisUrl, String imgUrl, String bonusUrl, String nextUrl) {
			this.title = title;
			this.description = description;
			this.thisUrl = thisUrl;
			this.imgUrl = imgUrl;
			this.bonusUrl = bonusUrl;
			this.nextUrl = nextUrl;
		}
	}
}