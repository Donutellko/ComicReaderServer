package xyz.donutellko.comicreaderserver;

import java.util.ArrayList;

public abstract class AbstractParser {
	public class ParsedPage {
		public String title, description, thisUrl, imgUrl, bonusUrl, nextUrl;

		public ParsedPage(String title, String description, String thisUrl, String imgUrl, String bonusUrl, String nextUrl) {
			this.title = title;
			this.description = description;
			this.thisUrl = thisUrl;
			this.imgUrl = imgUrl;
			this.bonusUrl = bonusUrl;
			this.nextUrl = nextUrl;
		}
	}
	abstract protected String getAlias();
	abstract protected ArrayList<ParsedPage> getParsedPages();
}
