package Parsers;

public class EnXkcdParser extends UniversalParser {

/*
<li><a rel="next" href="/2/" accesskey="n">Next &gt;</a></li>

<div id="comic">
<img src="//imgs.xkcd.com/comics/barrel_cropped_(1).jpg" title="Don&#39;t we all." alt="Barrel - Part 1" />
</div>

 */

	private final static String BASE_URL = "https://xkcd.com";

	EnXkcdParser(String url, String html) {
		super(url, html);
	}

	@Override
	protected String getAlias() {
		return "enxkcd";
	}

	@Override
	protected String getTitle(String html) {
		return UniversalParser.getByBegin(html, "<title>xkcd: ", "</title>");
	}

	@Override
	protected String getDescription(String html) {
		String tmp = UniversalParser.getByBegin(html, "<div id=\"comic\">", "</div>");
		return UniversalParser.getByBegin(tmp, "title=\"", "\" alt=");
	}

	@Override
	protected String getImgUrl(String html) {
		return UniversalParser.getByBegin(html, "Image URL (for hotlinking/embedding): ","\n");
	}

	@Override
	protected String getBonusUrl(String html) {
		return null;
	}

	@Override
	protected String getNextUrl(String html) {
		String tmp = UniversalParser.getByBegin(html, "<a rel=\"next\" href=\"", "/\" accesskey=\"n\">");
		return tmp == null ? null : BASE_URL + tmp;
	}

}
