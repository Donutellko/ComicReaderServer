package Collections;

import xyz.donutellko.comicreaderserver.Comic;

import java.util.ArrayList;

public class AcomicsListParser extends UniversalCollectionParser {

	final static String base_url = "https://acomics.ru"; // для относительных ссылок, начинающихся со слеша
	public static String initial_url = "https://acomics.ru/comics?categories=&ratings[]=1&ratings[]=2&ratings[]" +
			"=3&ratings[]=4&ratings[]=5&ratings[]=6&type=0&updatable=0&issue_count=10&sort=serial_name";

	public AcomicsListParser(String url, String html) {
		super(url, html);
	}

	@Override
	ArrayList<Comic> getComicList(String html) {
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
		String logo_url = base_url + getByBegin(tmp, "src=\"", "\'");

		// class="about">***</div
		String description = getByBegin(tmp, "class=\"about\">", "</div").trim();

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
		return base_url + b;
	}
}
