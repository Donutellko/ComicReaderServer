package DbControllers;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import DbControllers.*;
import Parsers.AcomicsParser;
import Parsers.ComicslateParser;
import Parsers.EnDilbertParser;
import Parsers.EnSmbcParser;
import Parsers.EnXkcdParser;
import Parsers.ReadmangaParser;
import Parsers.RuXkcdParser;
import Parsers.UniversalParser;
import AppUtilities.*;
import ComicsDataLogic.ComicDB;

public class DbInitUtilities {

	/**
	 * Создаёт в случае отсутствия таблицы COMIC, CATEGORY, COMIC_CATEGORY.
	 * Индивидуальные таблицы комиксов COMIC_* создаются в классе ComicDB, так как зависит от контента таблицы COMIC.
	 * @throws SQLException
	 */
	public static void initMainTables () throws SQLException {	
		// Получаем соединение и стейтмент к БД
		Connection conn = DbConnectionManager.getConnection();
		Statement statmt = conn.createStatement();
		
		// Создаем основные таблицы
		DbContentCreator.createComicTable(statmt);
		DbContentCreator.createCategoryTable(statmt);
		DbContentCreator.createComicCategoryTable(statmt);

		System.out.println("Main tables created or already existed. DB initialised...");
	}
	
	public static void dropComicsTables() throws SQLException {
		int count = 1, comicsCount = ComicDB.getMaxComicID();
		
		// Получаем соединение и стейтмент к БД
		Connection conn = DbConnectionManager.getConnection();
		Statement statmt = conn.createStatement();
				
		while(count < comicsCount) {
			try {
				DbContentCreator.dropComicNTable(statmt, count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			count++;
		}
		
	}
	
	public static void truncateComicsTables() throws SQLException {
		int count = 1, comicsCount = ComicDB.getMaxComicID();
		
		// Получаем соединение и стейтмент к БД
		Connection conn = DbConnectionManager.getConnection();
		Statement statmt = conn.createStatement();
				
		while(count < comicsCount) {
			try {
				DbContentCreator.truncateComicNTable(statmt, count);
			} catch (SQLException e) {
				
			}
			count++;
		}
		
	}
	
    
}
