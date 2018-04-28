package Parsers;

public class ReadmangaParser extends UniversalParser {

	ReadmangaParser(String url, String html) {
		super(url, html);
	}

	@Override
	String getTitle(String html) {
		String tmp = getByBegin(html, "<title>Чтение манги ", "- самые свежие");
		return tmp;
	}

	@Override
	String getDescription(String html) {
		return null;
	}

	@Override
	String getImgUrl(String html) {
		String tmp = getByEnd(html, "src=\"", "mangaPicture");
		tmp = tmp.substring(0, tmp.indexOf('\"'));
		return tmp;
	}

	@Override
	String getBonusUrl(String html) {
		return null;
	}

	@Override
	String getNextUrl(String html) {
		String nextchapter = getByBegin(html, "nextChapterLink = \"", "\";\n");

		int curpage = url.contains("#page=") ? Integer.parseInt(url.substring(url.indexOf("#page=") + "#page=".length())) : 0;

		String tmp = getByBegin(html, "pages-count\">", "</span>");
		int pagescount = Integer.parseInt(tmp);


		if (curpage == 0) {
			return url + "#page=1";
		} else if (curpage == pagescount - 1) {
			return "http://readmanga.me" + nextchapter;
		} else {
			return url.substring(0, url.indexOf("#page=")) + "#page=" + curpage + 1;
		}
	}
}
