package Collections;

import xyz.donutellko.comicreaderserver.Comic;
import static xyz.donutellko.comicreaderserver.Util.*;

import java.util.ArrayList;


public class MangalibListParser extends UniversalListParser {

    public MangalibListParser(String url, String html) {
        super(url, html);
    }

    public final static String INITIAL_URL = "https://mangalib.me/manga-list?page=1";

    @Override
    public String getAlias(){
        return "mangalib";
    }

    @Override
    protected ArrayList<Comic> getComicList(String html) {
        ArrayList<Comic> list = new ArrayList <>();
        int cursor;
        cursor = html.indexOf("\"manga-list-item\"");
        while (cursor > 0) {
            html = html.substring(cursor + 1);
            cursor = html.indexOf("\"manga-list-item\"");
            String tmp = html.substring(0, html.indexOf("</a"));
            list.add(foobar(tmp));
        }
        return list;
    }

    private Comic foobar(String tmp) {
        String title = getByBegin(tmp, "title=\"", "\"");
        String mainUrl = getByBegin(tmp,"href=\"","\"");
        String logo_url = getByBegin(tmp,"data-src=\"", "\"");
        String lang = "RU";
        String source = "mangalib";

        String mainHtml = xyz.donutellko.comicreaderserver.HttpWorker.getHtml(mainUrl);

        String description = unescapeUtfAndHtml(getByBegin(mainHtml,"og:description\" content=\"","\"/>"));
        String author = getByBegin(mainHtml,"href=\"https://mangalib.me/manga-list/author/","\"");
        String init_url = getByBegin(getByBegin(mainHtml,"manga__buttons","class"),"href=\"","\"") + "/1";

        return new Comic(title, description, author, mainUrl, null, init_url, logo_url, lang, source);
    }

    @Override
    protected String getNextUrl(String html) {
        if (Integer.parseInt(url.substring(url.lastIndexOf('=') + 1)) < 6) // без js доходит только до 6-й страницы
            return url.substring(0, url.lastIndexOf('=') + 1) + (Integer.parseInt(url.substring(url.lastIndexOf('=') + 1)) + 1);
        else
            return null;
    }
}
