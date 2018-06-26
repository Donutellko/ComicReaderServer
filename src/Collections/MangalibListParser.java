package Collections;

import xyz.donutellko.comicreaderserver.Comic;

import java.util.ArrayList;


public class MangalibListParser extends UniversalListParser {

    public MangalibListParser(String url, String html) {
        super(url, html);
    }

    public final static String INITIAL_URL = "https://mangalib.me/manga-list?page=1";

    @Override
    protected ArrayList<Comic> getComicList(String html) {
        ArrayList<Comic> list = new ArrayList <>();
        int a;
        a = html.indexOf("\"manga-list-item\"");
        while (a > 0) {
            html = html.substring(a + 1);
            a = html.indexOf("\"manga-list-item\"");
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
        if (Integer.parseInt(url.substring(url.lastIndexOf('=') + 1)) < 6)
            return url.substring(0, url.lastIndexOf('=') + 1) + (Integer.parseInt(url.substring(url.lastIndexOf('=') + 1)) + 1);
        else
            return null;
    }
}
