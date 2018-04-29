package ListParsers;

import ComicsDataLogic.*;
import java.util.ArrayList;
import Parsers.AbstractParser; 

public abstract class UniversalListParser extends AbstractParser {

	protected String url, html;

	UniversalListParser(String url, String html) {
		this.url = url;
		this.html = html;
	}

	protected abstract ArrayList<Comic> getComicList(String html);
	protected abstract String getNextUrl(String html);

	public ArrayList<Comic> getList() {
		return getComicList(html);
	}

	public String getNextUrl() {
		return getNextUrl(html);
	}
}
