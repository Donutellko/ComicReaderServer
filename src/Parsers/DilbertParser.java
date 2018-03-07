package Parsers;

public class DilbertParser extends UniversalParser {
	DilbertParser(String url, String html) {
		super(url, html);
	}

	/**
	 * uses: <span class="comic-title-name">Millenial Fever</span>
	 * @return like Millenial Fever
	 */
	@java.lang.Override
	String getTitle(String html) {
		String title = getByBegin(html, "class=\"comic-title-name\">", "<");
		return title;
	}

	/**
	 * uses: <date class="comic-title-date" itemprop="datePublished">	<span>Friday February 23,</span> 	<span itemprop="copyrightYear">2018</span>	</date>
	 * @return date like "Friday February 23, 2018"
	 */
	@java.lang.Override
	String getDescription(String html) {
		String tmp = getByBegin(html, "<date","</date>");
		String title = getByBegin(tmp, "<span>", "</span>");
		title += " " + getByBegin(tmp, "Year\">", "</span>");
		return title;
	}

	/**
	 * uses: <img class="img-responsive img-comic" width="900" height="280" alt="Listening To A Millenial - Dilbert by Scott Adams" src="http://assets.amuniversal.com/eb928de0ed850135156f005056a9545d">
	 * @return like "http://assets.amuniversal.com/eb928de0ed850135156f005056a9545d"
	 */
	@java.lang.Override
	String getImgUrl(String html) {
		String tmp = getByBegin(html, "<img class=\"img-responsive img-comic\"", ">");
		String url = getByBegin(tmp, "src=\"", "\"");
		return url;
	}

	/**
	 * No bonuses
	 * @return null
	 */
	@java.lang.Override
	String getBonusUrl(String html) {
		return null;
	}

	/**
	 * uses: <a href="/strip/2018-02-24" alt="Newer Strip" title="Next Strip" accesskey="n">
	 * @return like "https://dilbert.com/strip/2018-02-24"
	 */
	@java.lang.Override
	String getNextUrl(String html) {
		String url = getByEnd(html, "<a href=\"", "\" alt=\"Newer Strip\"");
		return url == null ? null : "http://dilbert.com" + url;
	}
}
