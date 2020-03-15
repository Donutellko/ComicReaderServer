package Collections;

import xyz.donutellko.comicreaderserver.Comic;

import java.util.ArrayList;

public class AcomicsListParser extends UniversalListParser {

	/**
	 * Определяет минимальное количество страниц в комиксе для добавления его в список
	 * (В случае с acomics происходит на стороне сервера (см. INITIAL_URL)
	 */
	final static int PAGES_COUNT_FILTER = 10;

	final static String BASE_URL = "https://acomics.ru"; // для относительных ссылок, начинающихся со слеша

	public final static String INITIAL_URL = "https://acomics.ru/comics" +
			"?categories=&ratings[]=1&ratings" + "[]=2&ratings[]=3&ratings[]=4&ratings[]=5&ratings[]=6" +
			"&type=0&updatable=0&issue_count=" + PAGES_COUNT_FILTER + "&sort=serial_name";

	public AcomicsListParser(String url, String html) {
		super(url, html);
	}

	@Override
	protected ArrayList<Comic> getComicList(String html) {
		ArrayList<Comic> list = new ArrayList <>();
		int a;
		html = html.substring(html.indexOf("\"catdata1\"") + 1);
		do {
			a = html.indexOf("\"catdata1\"");
			html = html.substring(a + 1);
			String tmp = html.substring(0, html.indexOf("</tr"));
			list.add(foobar(tmp));
		} while (a > 0);

		list.remove(list.size() - 1); // КОСТЫЛИЩЕ ДИЧАЙШИЙ, так как по какой-то
		// причине последний комикс на странице появляется дважды
		return list;
	}

	private Comic foobar(String tmp) {

		// title="Читать комикс ***">***</a>
		String title = getByBegin(tmp,"title=\"Читать комикс ", " онлайн\"");

		//
		String init_url = getByBegin(tmp, "href=\"", "\"") + "/1";

		//
		String logo_url = BASE_URL + getByBegin(tmp, "src=\"", "\'");

		// class="about">***</div
		String description = getByBegin(tmp, "class=\"about\">", "</div").trim();
		if (description.contains("Описание отсутствует")) description = null;

		// class="rating
		String rating;


		title = unescapeUtfAndHtml(title);
		description = unescapeUtfAndHtml(description);
		Comic result = new Comic(title, description, null, init_url,
				null, init_url, logo_url, "RU", "acomics");
		return result;
	}

	@Override
	protected String getNextUrl(String html) {
		String tmp = getByBegin(html, "pageNav", "</div");
		String[] a = tmp.split("</span");
		String b = null;
		for (int i = 0; i < a.length - 1; i++) {
			if (!a[i].contains("<a")) {
				b = a[i + 1];
				b = getByBegin(b, "href=\"", "\"");
				break;
			}
		}
		return BASE_URL + b;
	}
}
