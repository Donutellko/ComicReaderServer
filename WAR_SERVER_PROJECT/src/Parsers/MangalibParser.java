package Parsers;

public class MangalibParser extends UniversalParser {

    private String pagesList = getByBegin(html, "pages: [", "]") + "{",
            img = getByBegin(html, "\"og:image\" content=\"","\"/>"),
            nextChapter = getByBegin(html, "next: '", "'") + "/1";

    public MangalibParser(String url, String html) {
        super(url, html);
    }

    @Override
    protected String getAlias(){ 
    	return "mangalib"; 
    }

    @Override
    protected String getTitle(String html) {
        if (html.contains("reader-chapters__chapter-name"))
            return getByBegin(html, "class=\"reader-chapters__chapter-name\">: ", "</span>");
        else
            return "";
    }

    @Override
    protected String getBonusUrl(String html) { return null; }

    @Override
    protected String getDescription(String html) { return ""; }

    @Override
    protected String getImgUrl(String html) {
        Integer n = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
        for (Integer i = 1; i < n; i++)
            pagesList = pagesList.substring(pagesList.indexOf("{") + 1);
        return img.substring(0, img.lastIndexOf("/") + 1) + getByBegin(pagesList, "\"page_image\":\"","\"");
    }

    @Override
    protected String getNextUrl(String html) {
        pagesList = pagesList.substring(pagesList.indexOf("{") + 1);
        if (pagesList.length() > 0) {
            return url.substring(0, url.lastIndexOf("/") + 1) + getByBegin(pagesList, "\"page_slug\":", "}");
        }
        else if (nextChapter.compareTo("/1") != 0)
                return nextChapter;
            else
                return null;
    }
}
