package xyz.donutellko.comicreaderserver;

import Parsers.SinglePageParser;

@SuppressWarnings("WeakerAccess")

/**
 * Классы, используемые для создания JSON-файлов комиксов и страниц.
 */
public class Comic {
	// package-private, not available for parsers
	int comic_id, pagescount;
	String title, description, author, main_url, orig_url, init_url, logo_url, lang, source;
	long timestamp;

	/**
	 *
	 * @param description Описание комикса
	 * @param lang Язык комикса (два символа заглавными буквами)
	 * @param mainUrl Ссылка на главную страницу официального сайта комикса
	 */
	public Comic(String title, String description, String author, String mainUrl,
				 String orig_url, String init_url, String logo_url, String lang, String source) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.main_url = mainUrl;
		this.orig_url = orig_url;
		this.init_url = init_url;
		this.logo_url = logo_url;
		this.lang = lang;
		this.source = source;
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * Класс страницы комикса.
	 * Подразумевается, что он создаётся исключительно во время парсинга из SinglePageParser
	 * @see SinglePageParser.ParsedPage
	 */
	/*
	public static class Page {
		public String title, description, this_url, img_url, bonus_url;
		public int number;
		public long timestamp;

		public Page(int number, SinglePageParser.ParsedPage page) {
			this.number = number;
			this.title	   = page.title;
			this.description = page.description;
			this.this_url = page.thisUrl;
			this.img_url = page.imgUrl;
			this.bonus_url = page.bonusUrl;
			this.timestamp = System.currentTimeMillis();
		}

		public Page() {
			timestamp = System.currentTimeMillis();
		}
	}
	*/
}

/*
NUMBER
TITLE
DESCRIPTION
IMAGE_URL
PAGE_URL
BONUS_URL
TIMESTAMP
 */