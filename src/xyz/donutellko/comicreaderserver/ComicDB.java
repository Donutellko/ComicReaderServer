package xyz.donutellko.comicreaderserver;

import Parsers.UniversalParser;

import java.sql.ResultSet;
import java.sql.SQLException;

import static xyz.donutellko.comicreaderserver.DbConnection.statmt;

public class ComicDB {
	static ResultSet resSet;

	static DbPage getLastPage(int comicId) {
		try {
			resSet = statmt.executeQuery("select * from COMIC_" + comicId +
					" where PAGE_NUMBER=(select max(PAGE_NUMBER) from COMIC_" + comicId + ");");
			DbPage p = new DbPage(resSet);

			return p;
		} catch (SQLException e) {
			System.out.println("Failed to get last page for COMIC_ID=" + comicId);
		}
		return null;
	}

	static ResultSet getComics() {
		try {
			return statmt.executeQuery("select * from COMIC");
		} catch (SQLException e) {
			System.out.println("Error while getting a comic set");
			e.printStackTrace();
		}
		return null;
	}

	public static void addComic(Comic c) throws SQLException {
		addComic(c.name, c.lang, c.author, null, c.description, c.mainUrl, c.initUrl);
	}

	static void addComic(String title, String lang, String author, String source, String description, String mainUrl, String initUrl) throws SQLException {
		statmt.execute("insert into COMIC (NAME, LANG, AUTHOR, SOURCE, DESCRIPTION, MAIN_URL, INIT_URL) values (" +
				escape(title) + "', '" +  lang + "', '" + escape(author) + "', '" + source + "', '" + escape(description) + "', '" + mainUrl + "', '" + initUrl + ");");

		int comicId = getComicId(title, lang, source);
		createPagesTable(comicId);
	}

	public static int getComicId(String title, String lang, String source) throws SQLException {
		resSet = statmt.executeQuery("select COMIC_ID from COMIC where (TITLE='"+ title +"', LANG='" + lang + "', SOURCE='" + source + "');");
		return resSet.getInt("COMIC_ID");
	}

	static void addPage(int comicId, DbPage p) throws SQLException {
		addPage(comicId, p.title, p.description, p.imgUrl, p.thisUrl, p.bonusUrl);
	}

	static void addPage(int comicId, String title, String desription, String imageUrl, String pageUrl, String bonusUrl) throws SQLException {
		String sql = "insert into COMIC_" + comicId + " (TITLE, DESCRIPTION, IMAGE_URL, PAGE_URL, BONUS_URL) values ('" +
				escape(title) + "', '" + escape(desription) + "', '" + imageUrl + "', '" + pageUrl + "', '" + bonusUrl + "');";
		statmt.execute(sql);
	}

	private static String escape(String s) {
		s = s.replaceAll("\'","\'\'");
		return s;
	}

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

	static class DbComic {
		int comicId;
		String title, description, author, mainUrl, initUrl, origUrl, lang, source;
		long lastUpdate;

		DbComic (ResultSet resSet) throws SQLException {
			comicId = resSet.getInt("COMIC_ID");
			title = resSet.getString("TITLE");
			description = resSet.getString("DESCRIPTION");
			author = resSet.getString("AUTHOR");
			mainUrl = resSet.getString("MAIN_URL");
			initUrl = resSet.getString("INIT_URL");
			origUrl = resSet.getString("ORIG_URL");
			lang = resSet.getString("LANG");
			source = resSet.getString("SOURCE");
		}
	}

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
