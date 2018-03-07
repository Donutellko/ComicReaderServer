package Parsers;

public class ComicslateParser extends UniversalParser {
	public ComicslateParser(String url, String html) {
		super(url, html);
	}

	@Override
	String getTitle(String html) {
		String tmp = getByBegin(html, "class=\"page\"", "cnav cnavn");
		if (tmp == null) return "";
		int a = tmp.indexOf("<p>") + "<p>".length();
		int b = tmp.indexOf("</p");
		return a > 0 && b > 0 ? tmp.substring(a, b) : "";
	}

	@Override
	String getDescription(String html) {
		return "";
	}
	@Override
	String getImgUrl(String html) {
		String tmp = html.substring(html.indexOf("</table>"));
		tmp = getByBegin(tmp, "<img src=\"/_media", "\" class=\"media\""); //<img src="/_media/sci-fi/freefall/0998.jpg" class="media"
		return "https://comicslate.org/_media" + tmp;
	}

	@Override
	String getBonusUrl(String html) {
		return null;
	}

	@Override
	String getNextUrl(String html) {
		String url = getByEnd(html, "<a href=\"", "?purge=true\" accesskey=\"n\" id=\"navnext\"");
		return url == null ? null : "https://comicslate.org" + url;
	}
}