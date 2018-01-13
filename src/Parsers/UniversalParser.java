package Parsers;

public abstract class UniversalParser {

	ParsedPage parsedPage = new ParsedPage();
	String url;

	UniversalParser(String url, String html) {
		this.url = url;
		String title = unescapeUtfAndHtml(getTitle(html));
		String description = unescapeUtfAndHtml(getDescription(html));
		String thisUrl = getThisUrl(html);
		String imgUrl = getImgUrl(html);
		String bonusUrl = getBonusUrl(html);
		String nextUrl = getNextUrl(html);

		parsedPage.set(title, description, thisUrl, imgUrl, bonusUrl, nextUrl);
	}

	/** @param html HTML-код страницы
	 * @return Название страницы */

	abstract String getTitle(String html);
	abstract String getDescription(String html);
	abstract String getThisUrl(String html);
	abstract String getImgUrl(String html);
	abstract String getBonusUrl(String html);
	abstract String getNextUrl(String html);

	public ParsedPage getParsedPage() {
		return parsedPage;
	}

	/** Возвращает первый найденный фрагмент, полагая значение переменной to уникальной в тексте
	 * То есть например если в коде имеется фрагмент «title="Text." id=unique_id » и нужно найти строку "Text.",
	 * а id уникален, то имеет смысл искать, опираясь на id.
	 * */
	static String getByEnd(String html, String from, String to) {
		int end = html.indexOf(to);
		int begin = end == -1 ? -1 : html.substring(0, end - 1).lastIndexOf(from);

		return end == -1 || begin == -1 ? null : html.substring(begin + from.length(), end);
	}

	/** Возвращает первый найденный фрагмент, полагая значение переменной from уникальной в тексте
	 * То есть например если в коде имеется фрагмент «id=unique_id title="Text."» и нужно найти строку "Text.",
	 * а id уникален, то имеет смысл искать, опираясь на id.
	 * */
	static String getByBegin(String where, String from, String to) {
		int begin = where.indexOf(from);
		int end = where.indexOf(to, begin);

		return end == -1 || begin == -1 ? null :  where.substring(begin + from.length(), end);
	}

	/**
	 * Метод заменяет коды символов на сами символы, например, \u0026#39 на '
	 */
	static String unescapeUtfAndHtml(String s) {
		String r;
		r = s.replaceAll("\\u0026", "&");
		r = r.replaceAll("&#39;", "'");
		r = r.replaceAll("&quot;", "\"");
		return r;
	}

	public static class ParsedPage {
		public String title, description, thisUrl, imgUrl, bonusUrl, nextUrl;

		public ParsedPage() {
			title = description = imgUrl = bonusUrl = thisUrl = nextUrl = null;
		}

		public void set(String title, String description, String thisUrl, String imgUrl, String bonusUrl, String nextUrl) {
			this.title = title;
			this.description = description;
			this.thisUrl = thisUrl;
			this.imgUrl = imgUrl;
			this.bonusUrl = bonusUrl;
			this.nextUrl = nextUrl;
		}
	}
}