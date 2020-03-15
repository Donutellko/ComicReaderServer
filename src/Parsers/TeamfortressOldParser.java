package Parsers;

import static xyz.donutellko.comicreaderserver.Util.*;

public class TeamfortressOldParser extends SinglePageParser {

	// private final static String BASE_URL = "http://www.teamfortress.com/";
	private String BASE_URL = null;

	TeamfortressOldParser(String url, String html) {
		super(url, html);
		BASE_URL = url.substring(0, url.lastIndexOf('/') + 1);
	}

	@Override
	public String getAlias() {
		return "tf2old";
	}

	@Override
	protected String getTitle(String html) {
		String tmp = getByBegin(html, "<title>Team Fortress 2 - ", "</title>");
		return "TF2 " + tmp;
	}

	@Override
	protected String getDescription(String html) {
		return "";
	}

	@Override
	protected String getImgUrl(String html) {
		String tmp = getByBegin(html, "<div id=\"comic\"", "</div>");
		return getByBegin(tmp, "<img src=\"","\" />");
	}

	@Override
	protected String getBonusUrl(String html) {
		return null;
	}

	@Override
	protected String getNextUrl(String html) {
		String tmp = getByEnd(html, "<a href=\"", "\">Next");
		return tmp == null ? null : BASE_URL + tmp;
	}

}