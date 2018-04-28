package ComicsDataLogic;

import Parsers.UniversalParser;

/**
 * Классы, используемые для создания JSON-файлов комиксов и страниц.
 * По сути контейнеры для данных о комиксе и странице комикса соответственно
 */
public class Comic {
	// + comicID, -initURL
	public int comicID;
    public String title, 	  // название комикса
    			  shortName,  // краткое имя
    			  lang, 	  // язык
    			  author, 	  // автор
    			  description,// описание
    			  mainUrl, 	  // URL сайта всея комиксов
    			  //initUrl,    // URL комикса
    			  origUrl,	  // URL оригинала комикса
    			  imgUrl; 	  // URL логотипа комикса
    public long   lastUpdate; // время последнего обновления
    public int 	  pagesCount;

	/**
	 *
	 * @param name 		  Название комикса
	 * @param shortName   Краткое имя (не более 10 символов без пробелов и левых символов), используется для создания файлов
	 * @param description Описание комикса
	 * @param lang 		  Язык комикса (два символа заглавными буквами)
	 * @param mainUrl 	  Ссылка на главную страницу официального сайта комикса
	 */
    public Comic(int    comicID,
    			 String title, 
    			 String shortName,
    			 String lang,
    			 String author,
    			 String description,  
    			 String mainUrl,
    			 String initUrl,
    			 String origUrl,
    			 String imgUrl,
    			 long lastUpdate,
    			 int 	pagesCount) {
    	this.comicID     = comicID;
        this.title 	  	 = title;
        this.shortName	 = shortName;
        this.lang 		 = lang;
        this.author		 = author;
        this.description = description;
        this.mainUrl 	 = mainUrl;
        //this.initUrl	 = initUrl;
        this.origUrl	 = origUrl;
        this.imgUrl		 = imgUrl;
        this.lastUpdate  = lastUpdate;
        this.pagesCount  = pagesCount; 
    }

    public Comic(int comicID, String title, String lang, String author, String mainUrl, String initUrl, int pagesCount) {
    	this(comicID, title, null, lang, author, null, mainUrl, initUrl, null, null, 0L, pagesCount);
    }

    /**
     * Класс страницы комикса.
     * Подразумевается, что он создаётся исключительно во время парсинга из UniversalParser
     * @see UniversalParser.ParsedPage
     */
    public static class Page {
    	public int    number; 		// номер страницы
        public String title, 		// заголовок страницы 
        			  description, 	// описание
        			  thisUrl, 		// URL страницы
        			  imgUrl, 		// URL картинки
        			  bonusUrl; 	// URL на бонус
        public long   lastUpdate; 	// время последнего апдейта
        
        
        public Page(int 	number, 
	        		String 	title, 
	        		String 	description, 
	        		String 	thisUrl, 
	        		String 	imgUrl, 
	        		String 	bonusUrl, 
	        		long	timestamp) {
        	this.number 	 = number;
            this.title       = title;
            this.description = description;
            this.thisUrl     = thisUrl;
            this.imgUrl      = imgUrl;
            this.bonusUrl    = bonusUrl;
            this.lastUpdate  = timestamp;
        }
        
        public Page(int number, UniversalParser.ParsedPage page, long timestamp) {
        	this(number, page.title, page.description, page.thisUrl, page.imgUrl, page.bonusUrl, timestamp);
        }

	}
}
