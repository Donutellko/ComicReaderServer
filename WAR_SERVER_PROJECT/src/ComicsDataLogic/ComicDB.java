package ComicsDataLogic;

import Parsers.UniversalParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DbControllers.DbConnectionManager;
import DbControllers.DbContentCreator;

/**
 * Класс для взаимодействия с SQLite.
 * Инкапсулирует SQL запросы к БД
 */
public class ComicDB {
	
	// Таблица - результат при запросе к БД
	static ResultSet resSet;
	
	static Statement statmt = DbConnectionManager.getStatement();
	
	static Connection dbConn = DbConnectionManager.getConnection();
	
	//static Connection conn
	/**
	 * Получает объект последней страницы в таблице
	 *
	 * @param comicId COMIC_ID из таблицы COMIC
	 * @return объект DbComic
	 */
	public static DbPage getLastPage(int comicId) {
		try {
			// Строка таблицы с максимальным номером страницы
			resSet = statmt.executeQuery("select * from COMIC_" + comicId +
					" where PAGE_NUMBER=(select max(PAGE_NUMBER) from COMIC_" + comicId + ");");
			DbPage p = new DbPage(resSet);

			return p;
		} catch (SQLException e) {
			System.out.println("Failed to get last page for COMIC_ID=" + comicId + ". Hope it's because the table hasn't been created yet");
		}
		return null;
	}

	/**
	 * Получает список всех комиксов из таблицы COMIC в виде результата запроса
	 * В случае ошибки: Error while getting a comic set.
	 *
	 * @return ResultSet
	 */
	public static ResultSet getComics() {
		try {
			return statmt.executeQuery("select * from COMIC;");
		} catch (SQLException e) {
			System.out.println("Error while getting a comic set.");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Возвращает таблицу всех комиксов из таблицы COMIC, обновленные позже, чем указанный таймстемп
	 * В случае ошибки: Error while getting a comic set.
	 *
	 * @return ResultSet
	 */
	public static ResultSet getComicsAfter(long after_timestamp) {
		try {
			return statmt.executeQuery("select * from COMIC where LAST_UPDATE > " + after_timestamp + " or LAST_UPDATE is null;");
		} catch (SQLException e) {
			System.out.println("Error while getting a comic set.");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Возвращает PreparedStatement для выполнения поиска всех комиксов, обновленных позже чем таймстемп
	 * В случае ошибки Error while getting a comics list PreparedStatement.
	 * @return PreparedStatement
	 */
	public static PreparedStatement getPSComicsAfter() {
		try {
			return dbConn.prepareStatement("select * from COMIC where LAST_UPDATE>? or LAST_UPDATE is null;");
		} catch (SQLException e) {
			System.out.println("Error while getting a comics list PreparedStatement.");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Получает список всех страниц из таблицы COMIC_comicid после указанной страницы и после указанного таймстемпа
	 * В случае ошибки: Error while getting a comic's pages set.
	 * 
	 * @return ResultSet
	 */
	public static ResultSet getPagesAfter(int comic_id, int after_page, long after_timestamp) {
		try {
			return statmt.executeQuery("select * from COMIC_" + comic_id + 
									   " where PAGE_NUMBER > " + after_page + " and (LAST_UPDATE > " + after_timestamp + " or LAST_UPDATE is null);");
		} catch (SQLException e) {
			System.out.println("Error while getting a comic's pages set.");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Возвращает PreparedStatement для выполнения поиска всех страниц указанного комикса, обновленных позже чем таймстемп и имееющих номер больше указанного
	 * В случае ошибки Error while getting a comics list PreparedStatement.
	 * @return PreparedStatement
	 */
	/*public static PreparedStatement getPSPagesAfter() {
		try {
			return dbConn.prepareStatement("select * from COMIC_? where (LAST_UPDATE>? or LAST_UPDATE is null), (PAGE_NUMBER > ? or PAGE_NUMBER is null));");
		} catch (SQLException e) {
			System.out.println("Error while getting a comic's pages PreparedStatement");
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * Добавляет комикс в список и создаёт для него таблицу.
	 * см. addComic(String, String, ...)
	 *
	 * @param c
	 * @throws SQLException в случае неудачи при добавлении в список
	 */
	public static void addComic(Comic c) throws SQLException {
		//addComic(c.title, c.lang, c.author, c.description, c.mainUrl, c.initUrl, null);
		addComic(c.title, c.lang, c.author, c.description, c.mainUrl, null, null, c.pagesCount);
	}

	/**
	 * Добавляет комикс в список и создаёт для него таблицу.
	 *
	 * @throws SQLException в случае неудачи при добавлении в список
	 */
	static void addComic(String title, String lang, String author, String description, String mainUrl, String initUrl, String source, int pagesCount) throws SQLException {
		statmt.execute("insert into COMIC (NAME, LANG, AUTHOR, SOURCE, DESCRIPTION, MAIN_URL, INIT_URL, PAGES_COUNT) values (" +
				escape(title) + "', '" + lang + "', '" + escape(author) + "', '" + source + "', '" + escape(description) + "', '" + mainUrl + "', '" + initUrl + "', " + pagesCount + ");");
		int comicId = getComicId(title, lang, source);
		createPagesTable(comicId);
	}

	/**
	 * Ищет в БД комикс с переданными параметрами
	 *
	 * @param title  название
	 * @param lang   язык
	 * @param source источник
	 * @return COMIC_ID искомого комикса
	 * @throws SQLException
	 */
	public static int getComicId(String title, String lang, String source) throws SQLException {
		resSet = statmt.executeQuery("select COMIC_ID from COMIC where (TITLE='" + title + "', LANG='" + lang + "', SOURCE='" + source + "');");
		return resSet.getInt("COMIC_ID");
	}

	/**
	 * Добавляет переданную страницу в таблицу соответствующего комикса
	 *
	 * @param comicId COMIC_ID нужного комикса
	 * @param p
	 * @throws SQLException в случае невозможности добавления
	 */
	public static void addPage(int comicId, int pageNum, DbPage p, long lastUpdate) throws SQLException {
		addPage(comicId, pageNum, p.title, p.description, p.imgUrl, p.thisUrl, p.bonusUrl, lastUpdate);
	}

	/**
	 * Добавляет переданную информацию о странице в таблицу соответствующего комикса
	 *
	 * @throws SQLException
	 */
	static void addPage(int comicId, int pageNum, String title, String desription, String imageUrl, String pageUrl, String bonusUrl, long lastUpdate) throws SQLException {
		String sql = "insert into COMIC_" + comicId + " (PAGE_NUMBER, TITLE, DESCRIPTION, IMAGE_URL, PAGE_URL, BONUS_URL, LAST_UPDATE) values (" +
				pageNum + ", '" + escape(title) + "', '" + escape(desription) + "', '" + imageUrl + "', '" + pageUrl + "', '" + bonusUrl + "', " + lastUpdate + ");";
		statmt.execute(sql);
	}
	
	public static void updatePage(int comicId, int pageNum, DbPage p, long lastUpdate) throws SQLException  {
		updatePage(comicId, pageNum, p.title, p.description, p.imgUrl, p.thisUrl, p.bonusUrl, lastUpdate);
	}
	
	private static void updatePage(int comicId, int pageNum, String title, String desription, String imageUrl, String pageUrl, String bonusUrl, long lastUpdate) throws SQLException {
		String sql = "update COMIC_" + comicId + 
				" set TITLE='"+ escape(title) + 
				"', DESCRIPTION='" + escape(desription) + 
				"', IMAGE_URL='" + imageUrl + 
				"', PAGE_URL='" + pageUrl + 
				"', BONUS_URL='" + bonusUrl + 
				"', LAST_UPDATE=" + lastUpdate +
				" where PAGE_NUMBER=" + pageNum + ";";
		statmt.execute(sql);
	}
	
	private static void updatePage(int comicId, int pageNum, long lastUpdate) throws SQLException {
		String sql = "update COMIC_" + comicId + 
				" set LAST_UPDATE=" + + lastUpdate +
				" where PAGE_NUMBER=" + pageNum + ";";
		statmt.execute(sql);
	}

	/**
	 * Подготавливает строку для включения её в SQL-запрос.
	 * Заменяет ' на ''
	 */
	private static String escape(String s) {
		if (s == null) return null;
		s = s.replaceAll("\'", "\'\'");
		return s;
	}

	/**
	 * Создаёт в случае отсутствия таблицу для комикса с переданным COMIC_ID
	 *
	 * @param comicId
	 */
	public static void createPagesTable(int comicID) {
		try {
			DbContentCreator.createComicNTable(statmt, comicID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getComicsCount() throws SQLException {
		ResultSet rs = statmt.executeQuery("SELECT COUNT(*) FROM COMIC;");
		return rs.getInt(1);
	}

	public static int getMaxComicID() throws SQLException {
		ResultSet rs = statmt.executeQuery("SELECT MAX(COMIC_ID) FROM COMIC;");
		return rs.getInt(1);
	}
	
	/**
	 * Класс с информацией о комиксе, соответствующей той, которая хранится в таблице COMIC
	 */
	public static class DbComic {
		public int 		comicId;
		public String 	title, 
						lang, 
						author, 
						description, 
						mainUrl, 
						initUrl, 
						origUrl, 
						imgUrl, 
						source;
		public long 	lastUpdate;
		public int 	    pagesCount;

		public DbComic(ResultSet resSet) throws SQLException {
			comicId 	= resSet.getInt("COMIC_ID");
			title	    = resSet.getString("TITLE");
			lang 		= resSet.getString("LANG");
			author 		= resSet.getString("AUTHOR");
			description = resSet.getString("DESCRIPTION");
			mainUrl 	= resSet.getString("MAIN_URL");
			initUrl 	= resSet.getString("INIT_URL");
			origUrl 	= resSet.getString("ORIG_URL");
			imgUrl		= resSet.getString("IMAGE_URL");
			source 		= resSet.getString("SOURCE");
			lastUpdate  = resSet.getLong("LAST_UPDATE");
			pagesCount  = resSet.getInt("PAGES_COUNT");
		}
		
		public Comic toComic() {
			return new Comic(comicId, title, null, lang, author, description, mainUrl, initUrl, origUrl, imgUrl, lastUpdate, pagesCount);
		}
	}

	/**
	 * Класс с информацией о странице, соответствующей той, которая хранится в таблице COMIC_*
	 */
	public static class DbPage {
		public int 		number;
		public String 	title, 
						description,
						thisUrl, 
						imgUrl, 
						bonusUrl;
		public long 	lastUpdate;

		public DbPage(ResultSet resSet) throws SQLException {
			this.number 	 = resSet.getInt("PAGE_NUMBER");
			this.title		 = resSet.getString("TITLE");
			this.description = resSet.getString("DESCRIPTION");
			this.thisUrl 	 = resSet.getString("PAGE_URL");
			this.imgUrl 	 = resSet.getString("IMAGE_URL");
			this.bonusUrl 	 = resSet.getString("BONUS_URL");
			this.lastUpdate  = resSet.getLong("LAST_UPDATE");
		}

		
		public DbPage(UniversalParser.ParsedPage p) {
			title 		= p.title;
			description = p.description;
			thisUrl 	= p.thisUrl;
			imgUrl 		= p.imgUrl;
			bonusUrl 	= p.bonusUrl;
		}
		
		
		public Comic.Page toPage(){
			return new Comic.Page(number, title, description, thisUrl, imgUrl, bonusUrl, lastUpdate);
		}
	}

	
}
