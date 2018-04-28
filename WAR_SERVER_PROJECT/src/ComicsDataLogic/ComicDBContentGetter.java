package ComicsDataLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ComicsDataLogic.ComicDB.*;


/**
 * Класс для получения реальных данных из результатов SQL запросов к базе
 * Преимущественно работает с ResultSet
 */
public class ComicDBContentGetter {
	
	/**
	 * Получить из ResultSet таблицы COMIC список всех комиксов
	 * @return List<Comic>
	 */
	public static List<Comic> getComics(ResultSet db_comics) throws SQLException {
		List<Comic> comics = new ArrayList<>();
		DbComic dbComic;
		while(db_comics.next()) {
			dbComic = new DbComic(db_comics);
			comics.add(dbComic.toComic());
		}
		return comics;
	}
	
	/**
	 * Получить из ResultSet таблицы COMIC_N список всех страниц конкретного комикса
	 * @return List<Comic>
	 */
	public static List<Comic.Page> getPages(ResultSet db_comic) throws SQLException {
		List<Comic.Page> pages = new ArrayList<>();
		DbPage dbPage;
		while(db_comic.next()) {
			dbPage = new DbPage(db_comic);
			pages.add(dbPage.toPage());
		}
		return pages;
	}
	
}
