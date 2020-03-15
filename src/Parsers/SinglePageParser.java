package Parsers;

import xyz.donutellko.comicreaderserver.AbstractParser;
import static xyz.donutellko.comicreaderserver.Util.*;

import java.util.ArrayList;

public abstract class SinglePageParser extends AbstractParser {
	String url, html;

	SinglePageParser(String url, String html) {
		this.url = url;
		this.html = html;
	}

	/** @param html HTML-код страницы
	 * @return Название страницы */
	protected abstract String getTitle(String html);
	protected abstract String getDescription(String html);
	protected abstract String getImgUrl(String html);
	protected abstract String getBonusUrl(String html);
	protected abstract String getNextUrl(String html);

    public ArrayList<ParsedPage> getParsedPages() {
        String title = unescapeUtfAndHtml(getTitle(html));
        String description = unescapeUtfAndHtml(getDescription(html));
        String imgUrl = getImgUrl(html);
        String bonusUrl = getBonusUrl(html);
        String nextUrl = getNextUrl(html);

        ArrayList<ParsedPage> tmp = new ArrayList<>();
        tmp.add(new ParsedPage(title, description, url, imgUrl, bonusUrl, nextUrl));
        return tmp;
    }
}