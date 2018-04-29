package ComicsDataLogic;

import Parsers.UniversalParser;

/**
 * Классы, используемые для создания JSON-файлов комиксов и страниц.
 * По сути контейнеры для данных о комиксе и странице комикса соответственно
 */
public class Comic {
	// + comicID, -initURL
	int    comic_id;
    String title, 	  // название комикса
    	   //short_name,  // краткое имя
    	   lang, 	  // язык
    	   author, 	  // автор
    	   description,// описание
    	   main_url, 	  // URL сайта всея комиксов
    	   init_url,    // URL комикса
    	   orig_url,	  // URL оригинала комикса
    	   img_url, 	  // URL логотипа комикса
    	   source;  // Имя источника
    long   last_update; // время последнего обновления
    int    pagescount;

	/**
	 *
	 * @param name 		  Название комикса
	 * @param shortName   Краткое имя (не более 10 символов без пробелов и левых символов), используется для создания файлов
	 * @param description Описание комикса
	 * @param lang 		  Язык комикса (два символа заглавными буквами)
	 * @param mainUrl 	  Ссылка на главную страницу официального сайта комикса
	 */
    public Comic(String title, 
    			 String lang,
    			 String author,
    			 String description,  
    			 String mainUrl,
    			 String initUrl,
    			 String origUrl,
    			 String imgUrl,
    			 String source,
    			 int 	pagescount) {
    	//this.comic_id    = comic_id;
        this.title 	  	 = title;
        //this.short_name	 = shortName;
        this.lang 		 = lang;
        this.author		 = author;
        this.description = description;
        this.main_url 	 = mainUrl;
        this.init_url	 = initUrl;
        this.orig_url	 = origUrl;
        this.img_url     = imgUrl;
        this.source		 = source;
        this.last_update = System.currentTimeMillis(); // last_update ?????
        this.pagescount  = pagescount; 
    }

    /*
    public Comic(int comicID, String title, String lang, String author, String mainUrl, String initUrl, int pagesCount) {
    	this(comicID, title, null, lang, author, null, mainUrl, initUrl, null, null, 0L, pagesCount);
    }
     */
    
    /**
     * Класс страницы комикса.
     * Подразумевается, что он создаётся исключительно во время парсинга из UniversalParser
     * @see UniversalParser.ParsedPage
     */
    public static class Page {
    	public int    number; 		// номер страницы
        public String title, 		// заголовок страницы 
        			  description, 	// описание
        			  this_url, 		// URL страницы
        			  img_url, 		// URL картинки
        			  bonus_url; 	// URL на бонус
        public long   last_update; 	// время последнего апдейта
        
        
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
            this.this_url     = thisUrl;
            this.img_url      = imgUrl;
            this.bonus_url    = bonusUrl;
            this.last_update  = timestamp;
        }
        
        public Page(int number, UniversalParser.ParsedPage page) {
        	this(number, page.title, page.description, page.thisUrl, page.imgUrl, page.bonusUrl, System.currentTimeMillis());
        }

        public Page() {
        	this(0,null,null,null,null,null,System.currentTimeMillis());
        }
	}
}
