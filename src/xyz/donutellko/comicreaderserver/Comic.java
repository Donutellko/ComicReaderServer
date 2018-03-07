package xyz.donutellko.comicreaderserver;

import Parsers.UniversalParser;

@SuppressWarnings("WeakerAccess")

/**
 * Классы, используемые для создания JSON-файлов комиксов и страниц.
 */
public class Comic {
    public String name, shortName, description, lang, link;
    long timestamp;

	/**
	 *
	 * @param name Название комикса
	 * @param shortName Краткое имя (не более 10 символов без пробелов и левых символов), используется для создания файлов
	 * @param description Описание комикса
	 * @param lang Язык комикса (два символа заглавными буквами)
	 * @param link Ссылка на главную страницу официального сайта комикса
	 */
    public Comic(String name, String shortName, String description, String lang, String link) {
        this.name = name;
        this.shortName = shortName;
        this.lang = lang;
        this.description = description;
        this.link = link;
        timestamp = 0L;
    }

    public Comic(String name, String shortName, String lang, String link) {
        this.name = name;
        this.shortName = shortName;
        this.lang = lang;
        this.link = link;
        timestamp = 0L;
    }

    /**
     * Класс страницы комикса.
     * Подразумевается, что он создаётся исключительно во время парсинга из из
     * @see UniversalParser.ParsedPage
     */
    public static class Page {
        public String title, description, thisUrl, imgUrl, bonusUrl;

        public Page(UniversalParser.ParsedPage page) {
            this.title       = page.title;
            this.description = page.description;
            this.thisUrl     = page.thisUrl;
            this.imgUrl      = page.imgUrl;
            this.bonusUrl    = page.bonusUrl;
        }

		public Page() {}
	}
}
