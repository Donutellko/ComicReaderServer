package Parsers;

import static xyz.donutellko.comicreaderserver.Util.*;

public class ComicslateParser extends SinglePageParser {
	public ComicslateParser(String url, String html) {
		super(url, html);
	}

	@Override
	protected String getAlias() {
		return "comicslate";
	}

	@Override
	protected String getTitle(String html) {
		String tmp = getByBegin(html, "class=\"page\"", "cnav cnavn");
		if (tmp == null) return "";
		int a = tmp.indexOf("<p>") + "<p>".length();
		int b = tmp.indexOf("</p");
		return a > 0 && b > 0 ? tmp.substring(a, b) : null;
	}

	@Override
	protected String getDescription(String html) {
		return "";
	}

	@Override
	protected String getImgUrl(String html) {
		String tmp = html.substring(html.indexOf("</table>"));
		tmp = getByBegin(tmp, "<img src=\"/_media", "\" class=\"media\"");
		return "https://comicslate.org/_media" + tmp;
	}

	@Override
	protected String getBonusUrl(String html) {
		return null;
	}

	@Override
	protected String getNextUrl(String html) {
		String url = getByEnd(html, "<a href=\"", "?purge=true\" accesskey=\"n\" id=\"navnext\"");
		return url == null ? null : "https://comicslate.org" + url;
	}
}