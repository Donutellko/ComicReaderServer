package Parsers;

import static xyz.donutellko.comicreaderserver.Util.*;

import java.util.ArrayList;

public class ReadmangaParser extends MultiplePageParser {

    ReadmangaParser(String url, String html) {
        super(url, html);
    }

    private String pageList = getByBegin(html, "rm_h.init( [[", ");") + "[";

    @Override
    public String getAlias() {
        return "readmanga";
    }

    @Override
    protected String getTitle(String html) {
        return getByBegin(html, "Чтение манги ", " - самые свежие");
    }

    @Override
    protected String getDescription(String html) {
        return null;
    }

    @Override
    protected ArrayList<String> getImgUrls(String html) {
        ArrayList<String> pages = new ArrayList<>();
        int count = 0;
        while (pageList.length() > 0) {
            String tmp = getByBegin(pageList, "'','", "',\"") + getByBegin(pageList, "',\"", "\",");
            pageList = pageList.substring(pageList.indexOf("[") + 1);
            pages.add(tmp);
            count++;
        }
        pageCount = count;
        return pages;
    }

    @Override
    protected String getBonusUrl(String html) {
        return null;
    }

    @Override
    protected ArrayList<String> getNextUrl(String html) {
        ArrayList<String> nextUrls = new ArrayList<>();

        // Проверка является ли переданная ссылка на первую страницу в манге
        if (url.contains("#page"))
            url = url.substring(0, url.lastIndexOf('#'));
        nextUrls.add(url);

        for (int i = 1; i < pageCount; i++) {
            nextUrls.add(url + "#page=" + i);
        }

        String nextChapter = getByBegin(html, "nextChapterLink = \"", "\";");
        if (nextChapter.contains("vol"))
            nextUrls.add("http://readmanga.me" + nextChapter);
        else
            nextUrls.add(null);
        return nextUrls;
    }

    @Override
    protected int getCurrentPageNumber(String html) {
        return url.contains("#page") ? Integer.parseInt(url.substring(url.lastIndexOf('=') + 1)) : 0;
    }
}