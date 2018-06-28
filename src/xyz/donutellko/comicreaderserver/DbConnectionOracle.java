package xyz.donutellko.comicreaderserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionOracle {
    public static Connection conn;
    public static Statement statmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@185.203.241.180:1521:xe", "PICSTORIES_TEST", "anime_sila");
        System.out.println("Database connected! ");
    }
    /**
     * Если таблица уже создана, перехватывается исключение ошибки в синтаксисе
     * , ну и выводится инфа об ошибке на всякий случай
    */
    private static void initialise() throws SQLException {
        statmt = conn.createStatement();
        try {
            statmt.executeUpdate(
                    "CREATE TABLE COMIC (\n" +
                            "COMIC_ID INT PRIMARY KEY,\n" +
                            "TITLE varchar2(200) NOT NULL,\n" +
                            "LANG varchar2(4),\n" +
                            "SOURCE varchar2(20) NOT NULL,\n" +
                            "DESCRIPTION varchar2(3000),\n" +
                            "AUTHOR varchar2(50),\n" +
                            "MAIN_URL varchar2(200),\n" +
                            "INIT_URL varchar2(200) NOT NULL,\n" +
                            "ORIG_URL varchar2(200),\n" +
                            "LAST_UPDATE DATE,\n" +
                            "UNIQUE (TITLE, LANG, SOURCE))");
        }
        catch (Exception e){
            if (e.getMessage().contains("existing"))
                System.out.println("Table COMIC already exists");
            else
                e.printStackTrace();
        }

        try {
            statmt.executeUpdate(
                    "CREATE TABLE CATEGORY (\n" +
                            "CATEGORY_ID INT PRIMARY KEY,\n" +
                            "NAME varchar2(50) NOT NULL,\n" +
                            "TYPE varchar2(100) --FAVORITE/RECOMMENDATIONS/POPULAR/BY_TYPE/BY_COUNTRY/...\n" +
                            ")");
        }
        catch (Exception e){
            if (e.getMessage().contains("existing"))
                System.out.println("Table CATEGORY already exists");
            else
                e.printStackTrace();
        }

        try {
            statmt.executeUpdate(
                    "CREATE TABLE COMIC_CATEGORY (\n" +
                            "COMIC_ID INT,\n" +
                            "CATEGORY_ID INT,\n" +
                            "PRIMARY KEY (COMIC_ID, CATEGORY_ID),\n" +
                            "FOREIGN KEY (COMIC_ID) REFERENCES COMIC(COMIC_ID),\n" +
                            "FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID) )");
        }
        catch (Exception e){
            if (e.getMessage().contains("existing"))
                System.out.println("Table COMIC_CATEGORY already exists");
            else
                e.printStackTrace();
        }

        System.out.println("Tables created or already exist! DB initialised. ");

    }

    public static void close() throws ClassNotFoundException, SQLException {
        conn.close();
//		if (!statmt.isClosed()) statmt.close();
        if (ComicDB.resSet != null && !ComicDB.resSet.isClosed())
            ComicDB.resSet.close();

        System.out.println("DB connections closed.");
    }

    /**
     * initDb и closeDb перенесены в этот класс, чтобы не засорять main и легче было восстановить прошлую версию
    */
    public static void initDb() {
        try {
            DbConnectionOracle.connect();
            DbConnectionOracle.initialise();
        } catch (Exception e) { // SQLException, ClassNotFoundException
            e.printStackTrace();
        }
    }

    public static void closeDb() {
        try {
            DbConnectionOracle.close();
        } catch (Exception e) { // SQLException, ClassNotFoundException
            e.printStackTrace();
        }
    }
}
