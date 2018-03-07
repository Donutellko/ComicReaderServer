package Parsers;

public class SmbcParser extends UniversalParser {

/*
title: <title>Saturday Morning Breakfast Cereal - Know Your Linguistic Philosophies</title>
next:  <a href="https://www.smbc-comics.com/comic/saving-myself" class="next" rel="next"></a>
img:   <img title="Description" src="/comics/image-link.png" id="cc-comic" border="0">
bonus: <div id="aftercomic" onclick="toggleBlock(&quot;aftercomic&quot;)" style="" class="mobilehide"> \n <img src="//smbc-comics.com/comics/bonus-image0after.png">\n</div>
*/

	private final static String BASE_URL = "https://www.smbc-comics.com";

	/** Вспомогательные переменные */
	private final static String
			NEXT_BEGIN = "<a href=\"",
			NEXT_END = "\" class=\"next\"",
			IMG_BEGIN = "<img title=\"",
			IMG_END = "\" id=\"cc-comic\"",
			BONUS_BEGIN = "<div id=\"aftercomic\" onclick=\"toggleBlock(&quot;aftercomic&quot;)\" style=\"\" class=\"mobilehide\">\n   <img src=\"//smbc-comics.com",
			BONUS_END = "\">\n</div>";

	SmbcParser(String url, String html) {
		super(url, html);
	}

	@Override
	public String getTitle(String html) {
		return getByBegin(html, "<title>Saturday Morning Breakfast Cereal - ", "</title>");
	}

	@Override
	public String getDescription(String html) {
		String img_tag = getByEnd(html, IMG_BEGIN, IMG_END);
		return img_tag.substring(0, img_tag.indexOf('"'));
	}


	@Override
	public String getImgUrl(String html) {
		String img_tag = getByEnd(html, IMG_BEGIN, IMG_END);
		String img_tmp = img_tag.substring(img_tag.lastIndexOf('"') + 1);
		return BASE_URL + img_tmp;
	}

	@Override
	public String getBonusUrl(String html) {
		String bonus_tmp = getByBegin(html, BONUS_BEGIN, BONUS_END);
		return bonus_tmp == null ? null : BASE_URL + bonus_tmp;
	}

	@Override
	public String getNextUrl(String html) {
		return getByEnd(html, NEXT_BEGIN, NEXT_END);
	}
}
