package Parsers;

public class EnDilbertParser extends UniversalParser {
	EnDilbertParser(String url, String html) {
		super(url, html);
	}

	@Override
	protected String getAlias() {
		return "endilbert";
	}

	/**
	 * uses: <span class="comic-title-name">Millenial Fever</span>
	 * @return like Millenial Fever
	 */
	@Override
	protected String getTitle(String html) {
		String title = getByBegin(html, "class=\"comic-title-name\">", "<");
		return title;
	}

	/**
	 * uses: <date class="comic-title-date" itemprop="datePublished">	<span>Friday February 23,</span> 	<span itemprop="copyrightYear">2018</span>	</date>
	 * @return date like "Friday February 23, 2018"
	 */
	@Override
	protected String getDescription(String html) {
		String tmp = getByBegin(html, "<date","</date>");
		String title = getByBegin(tmp, "<span>", "</span>");
		title += " " + getByBegin(tmp, "Year\">", "</span>");
		return title;
	}

	/**
	 * uses: <img class="img-responsive img-comic" width="900" height="280" alt="Listening To A Millenial - Dilbert by Scott Adams" src="http://assets.amuniversal.com/eb928de0ed850135156f005056a9545d">
	 * @return like "http://assets.amuniversal.com/eb928de0ed850135156f005056a9545d"
	 */
	@Override
	protected String getImgUrl(String html) {
		String tmp = getByBegin(html, "<img class=\"img-responsive img-comic\"", ">");
		String url = getByBegin(tmp, "src=\"", "\"");
		return url;
	}

	/**
	 * No bonuses
	 * @return null
	 */
	@Override
	protected String getBonusUrl(String html) {
		return null;
	}

	/**
	 * uses: <a href="/strip/2018-02-24" alt="Newer Strip" title="Next Strip" accesskey="n">
	 * @return like "https://dilbert.com/strip/2018-02-24"
	 */
	@Override
	protected String getNextUrl(String html) {
		String url = getByEnd(html, "<a href=\"", "\" alt=\"Newer Strip\"");
		return url == null ? null : "http://dilbert.com" + url;
	}
}
