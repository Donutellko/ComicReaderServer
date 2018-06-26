package xyz.donutellko.comicreaderserver;

import Parsers.UniversalParser;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static xyz.donutellko.comicreaderserver.DbConnection.conn;
import static xyz.donutellko.comicreaderserver.DbConnection.statmt;

/**
 * Класс для взаимодействия с SQLite.
 */
public class ComicDB {
	static ResultSet resSet;

	/**
	 * Получает объект последней страницы в таблице
	 *
	 * @param comicId COMIC_ID из таблицы COMIC
	 * @return объект DbComic
	 */
	static DbPage getLastPage(int comicId) {
		try {
			resSet = statmt.executeQuery("select * from COMIC_" + comicId +
					" where PAGE_NUMBER=(select max(PAGE_NUMBER) from COMIC_" + comicId + ");");
			DbPage p = new DbPage(resSet);

			return p;
		} catch (SQLException e) {
			if (e.getMessage().contains("No"))
				System.out.println("No such table COMIC_" + comicId);
			else
				System.out.println("Failed to get last page for COMIC_ID="
						+ comicId + "\n" + e.getMessage());
		}
		return null;
	}

	/**
	 * Получает список всех комиксов из таблицы COMIC в виде результата запроса
	 * В случае ошибки: Error while getting a comic set.
	 *
	 * @return ResultSet
	 */
	static ResultSet getComics() {
		try {
			return statmt.executeQuery("SELECT * FROM COMIC");
		} catch (SQLException e) {
			System.out.println("Error while getting a comic set.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Добавляет комикс в список и создаёт для него таблицу.
	 * см. addComic(String, String, ...)
	 *
	 * @param c
	 * @throws SQLException в случае неудачи при добавлении в список
	 */
	public static void addComic(Comic c) {
		try {
			addComic(c.title, c.lang, c.author, c.source, c.description,
					c.main_url, c.init_url, c.timestamp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Добавляет комикс в список и создаёт для него таблицу.
	 *
	 * @throws SQLException в случае неудачи при добавлении в список
    */
	/*
	static void addComic(String title, String lang, String author, String source, String description,
						 String main_url, String init_url, long timestamp) throws SQLException {
		String sql = "insert into COMIC (TITLE, DESCRIPTION, AUTHOR, LANG, SOURCE, " +
				"MAIN_URL, INIT_URL, LAST_UPDATE)";
		sql +=  " values ("
				+ "'" + escape(title) + "', '"
				+ escape(description) + "', '"
				+ escape(author) + "', '"
				+ lang + "', '"
				+ source + "', '"
				+ main_url + "', '"
				+ init_url + "', "
				+ timestamp
				+ ");";
		statmt.executeUpdate(sql);
	}
    */

	// Создаю новое подключение, тк с conn из метода initialise отказывается работать
    static void addComic(String title, String lang, String author, String source, String description,
                         String main_url, String init_url, long timestamp) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oraclebi.avalon.ru:1521:orcl12", "DANSHAF", "danshaf");
        Statement statmt = conn.createStatement();
        ResultSet rs = statmt.executeQuery("SELECT MAX(COMIC_ID) FROM COMIC");
        rs.next();
        int i = rs.getInt(1) + 1;
        String sql = "insert into COMIC(COMIC_ID, TITLE, DESCRIPTION, AUTHOR, LANG, SOURCE, " +
                "MAIN_URL, INIT_URL, LAST_UPDATE)";
        sql +=  " values ("
                + i + ", "
                + "'" + escape(title) + "', '"
                + escape(description) + "', '"
                + escape(author) + "', '"
                + lang + "', '"
                + source + "', '"
                + main_url + "', '"
                + init_url + "', '"
                + new SimpleDateFormat("dd.MM.YYYY").format(new Date(timestamp))
                + "')";

        statmt.executeUpdate(sql);
        conn.close();
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
		resSet = statmt.executeQuery("select COMIC_ID from COMIC where (" +
				"TITLE='" + title + "', LANG='" + lang + "', SOURCE='" + source + "');");
		return resSet.getInt("COMIC_ID");
	}

	/**
	 * Добавляет переданную страницу в таблицу соответствующего комикса
	 *
	 * @param comicId COMIC_ID нужного комикса
	 * @param p
	 * @throws SQLException в случае невозможности добавления
	 */
	static void addPage(int comicId, DbPage p) throws SQLException {
		addPage(comicId, p.title, p.description, p.imgUrl, p.thisUrl, p.bonusUrl);
	}

	/**
	 * Добавляет переданную информацию о странице в таблицу соответствующего комикса
	 *
	 * @throws SQLException
	 */
	static void addPage(int comicId, String title, String desription, String imageUrl,
						String pageUrl, String bonusUrl) throws SQLException {
		String sql = "insert into COMIC_" + comicId
				+ " (TITLE, DESCRIPTION, IMAGE_URL, PAGE_URL, BONUS_URL) values ('" +
				escape(title) + "', '" + escape(desription) + "', '"
				+ imageUrl + "', '" + pageUrl + "', '" + bonusUrl + "');";
		statmt.execute(sql);
	}

	/**
	 * Подготавливает строку для включения её в SQL-запрос.
	 * Заменяет ' на ''
	 */
	private static String escape(String s) {
		if (s == null) return "";
		s = s.replaceAll("\'", "\'\'");
		return s;
	}

	/**
	 * Создаёт в случае отсутствия таблицу для комикса с переданным COMIC_ID
	 *
	 * @param comicId
	 */
	static void createPagesTable(int comicId) {
		try {
			statmt.execute("CREATE TABLE IF NOT EXISTS COMIC_" + comicId + " (\n" +
					"PAGE_NUMBER INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
					"TITLE TEXT(50),\n" +
					"DESCRIPTION TEXT (100),\n" +
					"PAGE_URL TEXT NOT NULL unique,\n" +
					"IMAGE_URL TEXT NOT NULL,\n" +
					"BONUS_URL TEXT" +
					");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addComicList(List <Comic> list) {
		for (Comic c : list)
			addComic(c);
	}

	/**
	 * Класс с информацией о комиксе, соответствующей той, которая хранится в таблице COMIC
	 */
	static class DbComic {
		int comic_id;
		String title, description, author, main_url, init_url, orig_url, lang, source;
		long timestamp;

		DbComic(ResultSet resSet) throws SQLException {
			comic_id = resSet.getInt("COMIC_ID");
			title = resSet.getString("TITLE");
			description = resSet.getString("DESCRIPTION");
			author = resSet.getString("AUTHOR");
			main_url = resSet.getString("MAIN_URL");
			init_url = resSet.getString("INIT_URL");
			orig_url = resSet.getString("ORIG_URL");
			lang = resSet.getString("LANG");
			source = resSet.getString("SOURCE");
		}
	}

	/**
	 * Класс с информацией о странице, соответствующей той, которая хранится в таблице COMIC_*
	 */
	static class DbPage {
		String title, description, thisUrl, imgUrl, bonusUrl;
		int number;

		DbPage(ResultSet resSet) throws SQLException {
			this.number = resSet.getInt("PAGE_NUMBER");
			this.title = resSet.getString("TITLE");
			this.description = resSet.getString("DESCRIPTION");
			this.thisUrl = resSet.getString("PAGE_URL");
			this.imgUrl = resSet.getString("IMAGE_URL");
			this.bonusUrl = resSet.getString("BONUS_URL");
		}

		DbPage(UniversalParser.ParsedPage p) {
			title = p.title;
			description = p.description;
			thisUrl = p.thisUrl;
			imgUrl = p.imgUrl;
			bonusUrl = p.bonusUrl;
		}
	}
}
