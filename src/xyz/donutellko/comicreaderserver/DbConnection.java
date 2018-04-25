package xyz.donutellko.comicreaderserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для создания и инициализации БД.
 */
public class DbConnection {
	public static Connection conn;
	public static Statement statmt;

	/**
	 * Подключение к SQLite COMICS.db, находящейся в той же директории.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void connect() throws ClassNotFoundException, SQLException {
		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:COMICS.db");
		System.out.println("Database connected! ");
	}

	/**
	 * Создаёт в случае отсутствия таблицы COMIC, CATEGORY, COMIC_CATEGORY.
	 * Индивидуальные таблицы комиксов COMIC_* создаются в классе ComicDB,
	 * так как зависит от контента таблицы COMIC.
	 * @throws SQLException
	 */
	public static void initialise () throws SQLException {
		statmt = conn.createStatement();
		statmt.execute("create table if not exists COMIC (\n" +
				"COMIC_ID integer primary key autoincrement, " +
				"TITLE text not null,\n" +
				"LANG text(4),\n" +
				"SOURCE text, --name of parser--\n" +
				"DESCRIPTION text,\n" +
				"AUTHOR text,\n" +
				"MAIN_URL text,\n" +
				"INIT_URL text not null,\n" +
				"ORIG_URL text,\n" +
				"LAST_UPDATE DATE," +
				"unique (TITLE, LANG, SOURCE));");

		statmt.execute(
				"create table if not exists CATEGORY (\n" +
				"CATEGORY_ID integer primary key autoincrement,\n" +
				"NAME text not null,\n" +
				"TYPE text --FAVORITE/RECOMMENDATIONS/POPULAR/BY_TYPE/BY_COUNTRY/...\n" +
				");");

		statmt.execute("create table if not exists COMIC_CATEGORY (\n" +
				"COMIC_ID integer,\n" +
				"CATEGORY_ID integer,\n" +
				"primary key (COMIC_ID, CATEGORY_ID),\n" +
				"foreign key (COMIC_ID) references COMIC(COMIC_ID),\n" +
				"foreign key (CATEGORY_ID) references CATEGORY(CATEGORY_ID) );");

		System.out.println("Tables created or already exist! DB initialised. ");

	}


	public static void close() throws ClassNotFoundException, SQLException {
		conn.close();
//		if (!statmt.isClosed()) statmt.close();
		if (ComicDB.resSet != null && !ComicDB.resSet.isClosed())
			ComicDB.resSet.close();

		System.out.println("DB connections closed.");
	}
}
