package DbControllers;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для создания таблиц в базе данных комиксов
 */
public class DbContentCreator {
	
	/**
	 * Создает таблицу COMIC
	 */
	public static void createComicTable(Statement statement) throws SQLException {
		statement.execute(
				"create table if not exists COMIC (\n" +
						"COMIC_ID integer primary key autoincrement,\n" +
						"TITLE text not null,\n" +
						"LANG text(4),\n" +
						"SOURCE text,\n" + // name of parser
						"DESCRIPTION text,\n" +
						"AUTHOR text,\n" +
						"MAIN_URL text,\n" +
						"INIT_URL text not null,\n" +
						"ORIG_URL text,\n" +
						"LAST_UPDATE number(10),\n" +
						"IMAGE_URL text,\n" +
						"PAGES_COUNT integer,\n" +
						
						"unique (TITLE, LANG, SOURCE)\n" +
				");");	
	}
	
	/**
	 * Создает таблицу CATEGORY
	 */
	public static void createCategoryTable(Statement statement) throws SQLException {
		statement.execute(
				"create table if not exists CATEGORY (\n" +
						"CATEGORY_ID integer primary key autoincrement,\n" +
						"NAME text not null,\n" +
						"TYPE text --FAVORITE/RECOMMENDATIONS/POPULAR/BY_TYPE/BY_COUNTRY/...\n" +
				");");	
	}
	
	/**
	 * Создает таблицу COMIC_CATEGORY
	 */
	public static void createComicCategoryTable(Statement statement) throws SQLException {
		statement.execute(
				"create table if not exists COMIC_CATEGORY (\n" +
						"COMIC_ID integer,\n" +
						"CATEGORY_ID integer,\n" +
						
						"primary key (COMIC_ID, CATEGORY_ID),\n" +
						"foreign key (COMIC_ID) references COMIC(COMIC_ID),\n" +
						"foreign key (CATEGORY_ID) references CATEGORY(CATEGORY_ID)\n"+
				");");
	}
	
	/**
	 * Создает таблицу COMIC_N, где N = указанный comic_id
	 */
	public static void createComicNTable(Statement statement, int comicID) throws SQLException {
		statement.execute(
				"create table if not exists COMIC_" + comicID + " (\n" +
						"PAGE_NUMBER INTEGER PRIMARY KEY,\n" +
						"TITLE TEXT(50),\n" +
						"DESCRIPTION TEXT (100),\n" +
						"PAGE_URL TEXT NOT NULL unique,\n" +
						"IMAGE_URL TEXT NOT NULL,\n" +
						"BONUS_URL TEXT, \n" +
						"LAST_UPDATE number(10)\n" + 
				");");
	}
	
	public static void dropComicNTable(Statement statement, int comicID) throws SQLException {
		statement.execute("drop table COMIC_" + comicID + ";");
		System.out.println("Dropping COMIC_" + comicID);
	}
	
	public static void truncateComicNTable(Statement statement, int comicID) throws SQLException {
		statement.execute("delete from COMIC_" + comicID + "; VACUUM;");
		System.out.println("Deleting data of COMIC_" + comicID);
	}
	
}
