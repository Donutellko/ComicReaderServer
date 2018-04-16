package Parsers;

public class TeamfortressOldParser extends UniversalParser {



    // private final static String BASE_URL = "http://www.teamfortress.com/";
    private String BASE_URL = null;

    TeamfortressOldParser(String url, String html) {
        super(url, html);
    }

    @Override
    protected void preExecute() {
        BASE_URL = url.substring(0, url.lastIndexOf('/') + 1);
    }

    @Override
    String getTitle(String html) {
        String tmp =UniversalParser.getByBegin(html, "<title>Team Fortress 2 - ", "</title>");
        return "TF2 " + tmp;
    }

    @Override
    String getDescription(String html) {
        return "";
    }

    @Override
    String getImgUrl(String html) {
        String tmp = UniversalParser.getByBegin(html, "<div id=\"comic\"", "</div>");
        return UniversalParser.getByBegin(tmp, "<img src=\"","\" />");
    }

    @Override
    String getBonusUrl(String html) {
        return null;
    }

    @Override
    String getNextUrl(String html) {
        String tmp = UniversalParser.getByEnd(html, "<a href=\"", "\">Next");
        return tmp == null ? null : BASE_URL + tmp;
    }

}