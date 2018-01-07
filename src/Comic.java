public class Comic {
    public String name, shortName, lang, description, link, logoUrl;
    int curpage = 0;
    long timestamp;
    Page[] pages;

    public Comic(String name, String shortName, String lang, String description, String link, Page[] pages, String icon_url, long timestamp) {
        this.name = name;
        this.shortName = shortName;
        this.lang = lang;
        this.description = description;
        this.link = link;
        this.pages = pages;
        this.logoUrl = icon_url;
        this.timestamp = timestamp;
    }

    public Comic(String name, String shortName, String lang, String description, String link) {
        this.name = name;
        this.shortName = shortName;
        this.lang = lang;
        this.description = description;
        this.link = link;

        logoUrl = Main.server_url + "logo/" + shortName + ".png";
        timestamp = 0L;
        pages = null;
    }


    /**
     * Класс страницы комикса.
     * Подразумевается, что создаётся из
     * @see UniversalParser.ParsedPage
     */
    @SuppressWarnings("WeakerAccess")
    static class Page {
        String title, description, thisUrl, imgUrl, bonusUrl;

        Page(UniversalParser.ParsedPage page) {
            this.title       = page.title;
            this.description = page.description;
            this.thisUrl     = page.thisUrl;
            this.imgUrl      = page.imgUrl;
            this.bonusUrl    = page.bonusUrl;
        }
    }
}
