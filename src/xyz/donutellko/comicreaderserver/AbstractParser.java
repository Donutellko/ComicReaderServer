package xyz.donutellko.comicreaderserver;

public class AbstractParser {

	/** Возвращает первый найденный фрагмент, полагая значение переменной to уникальной в тексте
	 * То есть например если в коде имеется фрагмент «title="Text." id=unique_id » и
	 * нужно найти строку "Text.",
	 * а id уникален, то имеет смысл искать, опираясь на id.
	 * */
	protected static String getByEnd(String html, String from, String to) {
		int end = html.indexOf(to);
		int begin = end == -1 ? -1 : html.substring(0, end - 1).lastIndexOf(from);

		return end == -1 || begin == -1 ? null : html.substring(begin + from.length(), end);
	}

	/** Возвращает первый найденный фрагмент, полагая значение переменной from уникальной в тексте
	 * То есть например если в коде имеется фрагмент «id=unique_id title="Text."» и
	 * нужно найти строку "Text.", а id уникален, то имеет смысл искать, опираясь на id.
	 * */
	protected static String getByBegin(String where, String from, String to) {
		int begin = where.indexOf(from) + from.length();
		int end = where.indexOf(to, begin);

		return end < 0 || begin < 0 ? null :  where.substring(begin, end);
	}

	/**
	 * Метод заменяет коды символов на сами символы, например, \u0026#39 на '
	 */
	protected static String unescapeUtfAndHtml(String s) {
		if (s == null) return null;
		String r;
		r = s.replaceAll("\\u0026", "&");
		r = r.replaceAll("&#39;", "'");
		r = r.replaceAll("&quot;", "\"");

		r = r.replaceAll("<strong>", "");
		r = r.replaceAll("</strong>", "");
		r = r.replaceAll("<em>", "");
		r = r.replaceAll("</em>", "");
		r = r.replaceAll("<br>", "");

		r = r.replaceAll("&lt;", "<");
		r = r.replaceAll("&gt;", ">");

		return r;
	}
}
