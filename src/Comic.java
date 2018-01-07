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
     * Содержит информацию о себе, ссылку на её страницу, url изображения и локальный путь к изображению
     */
    public static class Page {
        String name, description, link, image_link, image_path;

        // example: new Comic.Page(245, "Dad jokes", "I feel like we shouldn't consider bonobos as sapient until they can write something about human life as a sunset or the end of a long road or something.", "https://www.smbc-comics.com/comic/dad-jokes", "https://www.smbc-comics.com/comics/1450366623-20151217.png");
        public Page(String name, String description, String link, String image_link) {
            this.name = name;
            this.description = description;
            this.link = link;
            this.image_link = image_link;
        }
    }
}
