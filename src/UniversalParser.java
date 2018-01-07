public class UniversalParser {
	String title, nextUrl, description, imgUrl, bonusUrl, thisUrl;

	public UniversalParser(String html) {

	}

	public static String getName(){ return "UnviversalParser"; }

	TmpPage getTmpPage() {
		return new TmpPage(title, nextUrl, description, imgUrl, bonusUrl, thisUrl);
	}

	/** Возвращает первый найденный фрагмент, предполагая переменную to уникальной
	 * То есть например если в коде имеется фрагмент «title="Text." id=unique_id » и нужно найти строку "Text.",
	 * а id уникален, то имеет смысл искать, опираясь на id.
	 * */
	static String getByEnd(String html, String from, String to) {
		int end = html.indexOf(to);
		int begin = end == -1 ? -1 : html.substring(0, end - 1).lastIndexOf(from);

		return end == -1 || begin == -1 ? null : html.substring(begin + from.length(), end);
	}

	/** Возвращает первый найденный фрагмент, предполагая переменную to уникальной
	 * То есть например если в коде имеется фрагмент «id=unique_id title="Text."» и нужно найти строку "Text.",
	 * а id уникален, то имеет смысл искать, опираясь на id.
	 * */
	static String getByBegin(String where, String from, String to) {
		int begin = where.indexOf(from);
		int end = where.indexOf(to, begin);

		return end == -1 || begin == -1 ? null :  where.substring(begin + from.length(), end);
	}

}

/*
	String name;
	String image_url;
	String page_url;
	String nextUrl;
	String description;


	public Comic.Page getPage() {
		return new Comic.Page(name, description, page_url, image_url);
	}

	String findContains(String[] sources, String... strs) {
		for (String s : sources) {
			if (contains(s, strs)) {
				return s;
			}
		}
		return null;
	}

	boolean contains(String source, String... str) {
		for (String s : str)
			if (!source.contains(s)) return false;

		return true;
	}


	static String getHead(String s) {
		return s.substring(0, s.indexOf("</head>"));
	}

	static String getTitle(String s) {
		return findFirstBetween(s.toLowerCase(), "<title>", "</title>");
	}

	public static void ignorer(List<String> source, List<String> ignore) {
		for (String s : source)
			for (String i : ignore)
				if (s.contains(i)) {
					source.remove(s);
					break;
				}
	}

	/**
	 * Убирает лишние символы (повторяющиеся пробелы, табуляции...), заменяя их на одинарный пробел
	 *
	public static String clean(String sourceStr) {
		char[] source = sourceStr.toCharArray();
		StringBuilder result = new StringBuilder(source.length / 2);
		boolean prevIsWhite = false;
		for (char c : source) {
			boolean curIsWhite = Character.isWhitespace(c);
			if (!(prevIsWhite && curIsWhite)) {
				result.append(curIsWhite ? ' ' : c);
			}
			prevIsWhite = curIsWhite;
		}
		return result.toString();
	}

	public static List<String> findLinks(String source) {
		List<String> result = findBetween(source, "src=\"", "\"");
		result.addAll(findBetween(source, "href=\"", "\""));

		return result;
	}

	public static List<Link> findImages(String source) {
		List<String> tags = findBetween(source, "<img", "</img>");
		List<Link> result = new ArrayList<>(tags.size());

		for (String tag : tags) {
			String id = findFirstBetween(source, "src=\"", "\"");
			String link = findFirstBetween(source, "id=\"", "\"");
			String title = findFirstBetween(source, "title=\"", "\"");
			result.add(new Link(id, link, title));
		}

		return result;
	}

	public static String[] findImageTags(String source) {
		List<String> tags = findBetween(source, "<img", "</img>");
		String[] result = new String[tags.size()];
		tags.toArray(result);
		return result;
	}

	// Возращает список всех фрагментов между указанными подстроками
	public static String findFirstBetween(String source, String from, String to) {
		int begin = source.indexOf(from);
		int end = source.indexOf(to, begin);
		if (begin == 1 || end == 1) return null;
		return source.substring(begin + from.length(), end).trim().toLowerCase();
	}

	// Возращает список всех фрагментов между указанными подстроками
	public static List<String> findBetween(String source, String from, String to) {
		List<String> result = new ArrayList<>();
		int begin = 1;
		while (true) {
			begin = source.indexOf(from, begin + 1);
			int end = source.indexOf(to, begin);
			if (begin == -1 || end == -1) break;
			result.add(source.substring(begin + from.length(), end).trim().toLowerCase());
		}
		return result;
	}

	static List<String> findQuotes(String source) {
		List<String> result = new ArrayList<>();
		int begin = 0;
		while (true) {
			begin = source.indexOf('"', begin);
			int end = source.indexOf('"', begin);
			if (begin == -1 || end == -1) break;
			result.add(source.substring(begin + 1, end));
		}
		return result;
	}

	static class Link {
		String id;
		String link;
		String title;

		public Link(String id, String link, String title) {
			this.id = id;
			this.link = link;
			this.title = title;
		}

		public String getExtention() {
			int begin = link.lastIndexOf('.');
			int tmp = link.lastIndexOf('/');
			if (begin != 0 && begin > tmp && link.length() - begin <= 4)
				return link.substring(begin);
			else return "";
		}
	}

	// некоторые ссылки бывают вообще ненужны (во всяких рекламах и прочем), это дефолтный список их вхождений
	public static List<String> defaultIgnoreList() {
		String[] result = {"google", "patreon", "amazon", "facebook", "tumblr", "twitter", "random", ".css", ".js"};
		return Arrays.asList(result);
	}
}

*/