public class SmbcParser extends UniversalParser {

	// title: <title>Saturday Morning Breakfast Cereal - Know Your Linguistic Philosophies</title>
	// next:  <a href="https://www.smbc-comics.com/comic/saving-myself" class="next" rel="next"></a>
	// img:   <img title="Description" src="/comics/image-link.png" id="cc-comic" border="0">
	// bonus: <div id="aftercomic" onclick="toggleBlock(&quot;aftercomic&quot;)" style="" class="mobilehide"> \n <img src="//smbc-comics.com/comics/bonus-image0after.png">\n</div>

	// Вспомогательные переменные, являющиеся рамками для поиска начал и концов нужных тегов
	static String next_begin = "<a href=\"";
	static String next_end = "\" class=\"next\"";

	static String img_begin = "<img title=\"";
	static String img_end = "\" id=\"cc-comic\"";

	static String bonus_begin = "<div id=\"aftercomic\" onclick=\"toggleBlock(&quot;aftercomic&quot;)\" style=\"\" class=\"mobilehide\">\n   <img src=\"//smbc-comics.com";
	static String bonus_end = "\">\n</div>";

	public SmbcParser(String html) {
		super(html); // does nothing
		title = getByBegin(html, "<title>", "</title>");

		nextUrl = getByEnd(html, next_begin, next_end);

		String img_tag = getByEnd(html, img_begin, img_end);
		description = img_tag.substring(0, img_tag.indexOf('"'));
		String img_tmp = img_tag.substring(img_tag.lastIndexOf('"') + 1);
		imgUrl = img_tmp == null ? null : "https://www.smbc-comics.com" + img_tmp;

		String bonus_tmp = getByBegin(html, bonus_begin, bonus_end);
		bonusUrl = bonus_tmp == null ? null : "https://www.smbc-comics.com" + bonus_tmp;
	}
}
