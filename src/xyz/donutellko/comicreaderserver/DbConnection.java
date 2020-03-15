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
 *
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
 *
 * @throws SQLException
 */
public static void initialise() throws SQLException {
    statmt = conn.createStatement();
    statmt.execute("CREATE TABLE IF NOT EXISTS COMIC (\n" +
            "COMIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "TITLE TEXT NOT NULL,\n" +
            "LANG TEXT(4),\n" +
            "SOURCE TEXT, --name of parser--\n" +
            "DESCRIPTION TEXT,\n" +
            "AUTHOR TEXT,\n" +
            "MAIN_URL TEXT,\n" +
            "INIT_URL TEXT NOT NULL,\n" +
            "ORIG_URL TEXT,\n" +
            "LAST_UPDATE DATE," +
            "UNIQUE (TITLE, LANG, SOURCE));");
    
    statmt.execute(
            "CREATE TABLE IF NOT EXISTS CATEGORY (\n" +
                    "CATEGORY_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "NAME TEXT NOT NULL,\n" +
                    "TYPE TEXT --FAVORITE/RECOMMENDATIONS/POPULAR/BY_TYPE/BY_COUNTRY/...\n" +
                    ");");
    
    statmt.execute("CREATE TABLE IF NOT EXISTS COMIC_CATEGORY (\n" +
            "COMIC_ID INTEGER,\n" +
            "CATEGORY_ID INTEGER,\n" +
            "PRIMARY KEY (COMIC_ID, CATEGORY_ID),\n" +
            "FOREIGN KEY (COMIC_ID) REFERENCES COMIC(COMIC_ID),\n" +
            "FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID) );");
    
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
