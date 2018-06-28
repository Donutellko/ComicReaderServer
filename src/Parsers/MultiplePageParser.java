package Parsers;

import xyz.donutellko.comicreaderserver.AbstractParser;

import static xyz.donutellko.comicreaderserver.Util.*;

import java.util.ArrayList;

public abstract class MultiplePageParser extends AbstractParser {
    String url, html;

    MultiplePageParser(String url, String html) {
        this.url = url;
        this.html = html;
    }

    protected int pageCount;

    /**
     * @param html HTML-код страницы
     * @return Название страницы
     */
    protected abstract String getTitle(String html);

    protected abstract String getDescription(String html);

    protected abstract ArrayList<String> getImgUrls(String html);

    protected abstract String getBonusUrl(String html);

    protected abstract ArrayList<String> getNextUrl(String html);

    protected abstract int getCurrentPageNumber (String html);

    public ArrayList<ParsedPage> getParsedPages() {
        int curPage = getCurrentPageNumber(html);
        String title = unescapeUtfAndHtml(getTitle(html));
        String description = unescapeUtfAndHtml(getDescription(html));
        ArrayList<String> imgUrls = getImgUrls(html);
        String bonusUrl = getBonusUrl(html);
        ArrayList<String> nextUrls = getNextUrl(html);

        ArrayList<ParsedPage> tmp = new ArrayList<>();
        for ( ; curPage < pageCount; curPage++)
            tmp.add(new ParsedPage(title, description, nextUrls.get(curPage),
                    imgUrls.get(curPage), bonusUrl, nextUrls.get(curPage + 1)));
        return tmp;
    }
}