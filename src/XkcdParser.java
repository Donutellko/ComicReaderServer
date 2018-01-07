public class XkcdParser extends UniversalParser {

/*
<li><a rel="next" href="/2/" accesskey="n">Next &gt;</a></li>

<div id="comic">
<img src="//imgs.xkcd.com/comics/barrel_cropped_(1).jpg" title="Don&#39;t we all." alt="Barrel - Part 1" />
</div>


 */

	private final static String BASE_URL = "https://xkcd.com";

	XkcdParser(String html) {
		super(html);
	}

	@Override
	String getTitle(String html) {
		return getByBegin(html, "<title>xkcd: ", "</title>");
	}

	@Override
	String getDescription(String html) {
		String tmp = getByBegin(html, "<div id=\"comic\">", "</div>");
		return getByBegin(tmp, "title=\"", "\" alt=");
	}

	@Override
	String getThisUrl(String html) {
		return getByBegin(html, "Permanent link to this comic: ", "<br />");
	}

	@Override
	String getImgUrl(String html) {
		return getByBegin(html, "Image URL (for hotlinking/embedding): ","\n");
	}

	@Override
	String getBonusUrl(String html) {
		return null;
	}

	@Override
	String getNextUrl(String html) {
		return BASE_URL + getByBegin(html, "<a rel=\"next\" href=\"", "/\" accesskey=\"n\">");
	}

}
