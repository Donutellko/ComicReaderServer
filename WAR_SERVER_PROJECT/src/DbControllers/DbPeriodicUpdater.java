package DbControllers;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import AppUtilities.HttpWorker;
import ComicsDataLogic.*;
import Parsers.*;

public class DbPeriodicUpdater extends TimerTask {
	
	private static boolean initDone;
	private static long currentTimestamp;
	private static final int DEBUG_MAX_PAGES = 10;
	
	public DbPeriodicUpdater(){
		DbPeriodicUpdater.initDone = true; //false
		currentTimestamp = new Date().getTime();
	}
	
	public void run() {
		currentTimestamp = new Date().getTime();
		try {
			if (initDone) {
				updatePossibleComics();
			} else {
				DbInitUtilities.truncateComicsTables();
				createPossibleComics();
				initDone = true;
			}
			System.out.println("All comics updated by plan. Time: " + currentTimestamp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	// Проходит все комиксы из COMIC и добавляет все доступне комииксы в таблицы COMIC_N, парсит доступные страницы
	public static void createPossibleComics() throws SQLException {
		processComics(false);
	}
	
	// Проходит все комиксы из COMIC и обновляет их в БД
	public static void updatePossibleComics() throws SQLException {
		processComics(true);
	}
	
	private static void processComics(boolean shallUpdate) throws SQLException {
		// Таблица всех комиксов из COMIC
		ResultSet rset = ComicDB.getComics();
		
		// Список всех комиксов
		List<ComicDB.DbComic> comiclist = new ArrayList<>();
		while (rset.next()) {
			comiclist.add(new ComicDB.DbComic(rset));
		}
		
		// Подгружаем и парсим комиксы, какие сумели достать
		for (ComicDB.DbComic c : comiclist) {
			parseComic(shallUpdate, c);
		}
	}

	private static void parseComic(boolean shallUpdate, ComicDB.DbComic c) { 
		if (c.init_url == null || c.init_url.length() == 0) {
			System.out.println("\nNo initial URL found for " + c.title);
			return;
		}

		System.out.println("\nUpdating " + c.title);
		Class parser = getParser(c.source);
		if (parser != null) {
			try {
				if (shallUpdate) {
					updatePages(parser, c.comic_id, c.init_url);
				} else {
					createAndAddPages(parser, c.comic_id, c.init_url);
				}
				
			} catch (Exception e) {
				System.out.println("Exception while getting " + c.title);
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Unable to find parser: " + c.source);
		}
	}

	
	
	/**
	 * Возвращает класс парсера по данному имени парсера
	 */
	private static Class getParser(String source) {
    	switch (source.toLowerCase()) {
			case "ensmbc" 		: return EnSmbcParser.class;
			case "enxkcd" 		: return EnXkcdParser.class;
			case "ruxkcd" 		: return RuXkcdParser.class;
			case "endilbert" 	: return EnDilbertParser.class;
			
			case "mangalib"     : return MangalibParser.class;
			case "comicslate" 	: return ComicslateParser.class;
			case "acomics" 		: return AcomicsParser.class;
			case "readmanga" 	: return ReadmangaParser.class;
			case "tf2old" 	    : return TeamfortressOldParser.class;
			//case "mangachan"    : return MangachanParser.class;
			default: return null;
		}
	}
	
	/**
	 * @param tClass какой парсер использовать
	 * @param comicId краткое имя
	 * @param initUrl Url начала по умолчанию (используется, если файл не существует), иначе продолжаем с последнего записанного
	 */
	private static <T extends UniversalParser> void createAndAddPages(Class<T> tClass, int comicId, String initUrl) throws SQLException {
		
		// Создаем таблицу, если не существует
		ComicDB.createPagesTable(comicId);
		
		//ComicDB.DbPage page = ComicDB.getLastPage(comicId);
		ComicDB.DbPage page;
		UniversalParser.ParsedPage tmppage = null;
		String nextUrl = initUrl;
		
		/*if (page != null && page.thisUrl != null) {
			//SinglePageParser.ParsedPage p = ;
			nextUrl = getPage(tClass, page.thisUrl).nextUrl;
		}*/

		int count = 1;
		
		//Command dbCommand;
		
		do {
			tmppage = getPage(tClass, nextUrl);
			if (tmppage == null) {
				System.out.println("Unable to generate parsed page!");
				return;
			}
			nextUrl = tmppage.nextUrl;
			page = new ComicDB.DbPage(tmppage);
			// adding or updating page in DB
			ComicDB.addPage(comicId, count, page, currentTimestamp);
			count++;
		} while (tmppage.nextUrl != null && count <= DEBUG_MAX_PAGES);
		System.out.println("Created new pages: " + count);
	}

	private static <T extends UniversalParser> void updatePages(Class<T> tClass, int comicId, String initUrl) throws SQLException {
		ComicDB.DbPage page;
		UniversalParser.ParsedPage tmppage = null;
		String nextUrl = initUrl;
		int count = 1;
		do {
			tmppage = getPage(tClass, nextUrl);
			if (tmppage == null) {
				System.out.println("Unable to generate parsed page!");
				return;
			}
			nextUrl = tmppage.nextUrl;
			page = new ComicDB.DbPage(tmppage);
			// adding or updating page in DB
			ComicDB.updatePage(comicId, count, page, currentTimestamp);
			count++;
		} while (tmppage.nextUrl != null && count <= DEBUG_MAX_PAGES);
		System.out.println("Updated pages: " + count);
	}
	
    private static <T extends UniversalParser> UniversalParser.ParsedPage getPage(Class<T> tClass, String url) {
    	String html = HttpWorker.getHtml(url);
		if (html != null && html.length() > 0) try {
			//SinglePageParser.ParsedPage parsedPage = tClass.getDeclaredConstructor(String.class).newInstance(html).getParsedPage();
			Constructor c = tClass.getDeclaredConstructor(String.class, String.class);
			c.setAccessible(true);
			UniversalParser.ParsedPage parsedPage = ((UniversalParser) c.newInstance(url, html)).getParsedPage();

			return parsedPage;
		} catch (Exception e) { 
			e.printStackTrace(); 
		}

		return null;
	}
    
    public static void updateComicsPages() {
    			ResultSet rset = ComicDB.getComics();
    			assert rset != null;
    	
    			for (ComicDB.DbComic c : getComicsFromDb(rset)) {
    				if (c.init_url == null || c.init_url.length() == 0) {
    					System.out.println("\nNo initial URL found for " + c.title);
    					continue;
    	 			}
    				parseComic(true,c);
    			} 
    }

    private static List <ComicDB.DbComic> getComicsFromDb(ResultSet rset) {
    			System.out.println("Getting comics from Db");
    			List <ComicDB.DbComic> result = new ArrayList <>();
    			try {
    				while (rset.next())
    					result.add(new ComicDB.DbComic(rset));
    	 		} catch (Exception e) {
    	 			e.printStackTrace();
    	 		}
    	
    			System.out.println("Got " + result.size() + " comics to update.");
    	
    			return result;
    }

    /*
    private static void parseArgs(String[] args) {
    			for (String c : args)
    				switch (c) {
    					case "-debug":
    						debug = true; // Включение дебага
    						System.out.println("Debug mode on. Only " + testN
    								+ " pages per comic will be parsed. ");
    						break;
    					case "-dac":
    						dont_add_comics = true; // Отключение добавления новых комиксов из каталогов
    						System.out.println("Adding new comics from catalogs disabled.");
    						break;
    					case "-h":
    						System.out.println("Available options: \n" +
    								"\t-debug   Add only 5 pages per comic\n" +
    								"\t-dac     Disable adding new comics from catalogs");
    					default:
    						System.out.println("Unknown option " + c + "\nUse -h to print help.");
    				}
    		}
    
    */
    /*
    private interface Command{
    	void execute();
    }
    private static class UpdateCommand implements Command {
    	
    	private int comicId;
    	private int pageNum;
    	private DbPage page;
    	private long lastUpdate;
    	
    	public UpdateCommand(int comicId, int pageNum, DbPage p, long lastUpdate) {
    		this.comicId = comicId;
    		this.pageNum = pageNum;
    		this.page = p;
    		this.lastUpdate = lastUpdate;
    		
    	}
    	
    	public void execute() {
    		try {
				ComicDB.updatePage(comicId, pageNum, page, lastUpdate);
			} catch (SQLException e) {
				System.out.println("Failed updating page " + pageNum + " of comic: " + page.title);
				e.printStackTrace();
			}
    	}
    }
    
    private static class AddCommand implements Command{
    	
    	private int comicId;
    	private DbPage page;
    	private long lastUpdate;
    	
    	public AddCommand(int comicId, DbPage p, long lastUpdate) {
    		this.comicId = comicId;
    		this.page = p;
    		this.lastUpdate = lastUpdate;
    		
    	}
    	
    	public void execute() {
    		try {
				ComicDB.addPage(comicId, page, lastUpdate);
			} catch (SQLException e) {
				System.out.println("Failed adding page of comic: " + page.title);
				e.printStackTrace();
			}
    	}
    }
    */
}
