package Parsers;

import static xyz.donutellko.comicreaderserver.Util.*;

public class MangalibParser extends SinglePageParser {

    private String pageList = getByBegin(html, "pages: [{", "]") + "{",
            img = getByBegin(html, "\"og:image\" content=\"","\"/>"),
            nextChapter = getByBegin(html, "next: '", "'") + "/1";

    public MangalibParser(String url, String html) {
        super(url, html);
    }

    @Override
    public String getAlias(){ return "mangalib"; }

    @Override
    protected String getTitle(String html) {
        return getByBegin(html, "Читать мангу ", " . Легко и удобно");
    }

    @Override
    protected String getBonusUrl(String html) { return null; }

    @Override
    protected String getDescription(String html) { return ""; }

    @Override
    protected String getImgUrl(String html) {
        Integer n = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
        for (Integer i = 1; i < n; i++)
            pageList = pageList.substring(pageList.indexOf("{") + 1);
        return img.substring(0, img.lastIndexOf("/") + 1) + getByBegin(pageList, "\"page_image\":\"","\"");
    }

    @Override
    protected String getNextUrl(String html) {
        pageList = pageList.substring(pageList.indexOf("{") + 1);
        if (pageList.length() > 0) {
            return url.substring(0, url.lastIndexOf("/") + 1) + getByBegin(pageList, "\"page_slug\":", "}");
        }
        else if (nextChapter.compareTo("/1") != 0)
                return nextChapter;
            else
                return null;
    }
}
