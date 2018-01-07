public class Defaults {
	final static String[] descriptions = {
			"by Zach Weinersmith. Recurring themes include atheism, God, superheroes, romance, dating, science, research, parenting and the meaning of life.", // SMBC
			"by Randall Munroe. A webcomic of romance, sarcasm, math, and language.", //XKCD
			"by Рэндел Манро. Это вебкомикс о любви, сарказме, математике и языке. ", //XKCD
			"by Mark Stanley. Научно-фантастический веб-комикс о злоключениях экипажа космического корабля «Свирепая курица»", // Freefall
			"by Mark Stanley.  Set on a planet in the early stages of terraforming, the strip deals with the antics of alien spaceship \"captain\" Sam Starfall, his robot friend Helix, and their Bowman's Wolf engineer Florence Ambrose.", // Eng Freefall
			"by Tim Buckley. A gaming-related webcomic and animated series.", //CAD
			"Webcomics about life, science and other stuff I guess.", //TAY
			"He's a cat. He plays video games.",//Gamercat
			"He's a cat. He plays video games.",//Gamercat
			"by Jago Dibuja.", // LWHAG
			"page 271 out of 271", // SeqArt
			"page 271 out of 271", // Sabrina
	};

	// TODO: Временный (или дефолтный, кто знает) набор комиков
	static Comic[] comicsList = {
			new Comic("Saturday Morning Breakfast Cereal"     , "SMBC"     , "EN", descriptions[0 ], "smbc-comics.com"                       ),
			new Comic("XKCD"                                  , "XKCD"     , "EN", descriptions[1 ], "xkcd.com"                              ),
			new Comic("XKCD"                                  , "XKCD"     , "RU", descriptions[2 ], "xkcd.ru"                               ),
			new Comic("Freefall"                              , "Freefall" , "RU", descriptions[3 ], "comicslate.org/sci-fi/freefall"        ),
			new Comic("Freefall"                              , "Freefall" , "EN", descriptions[4 ], "http://freefall.purrsia.com"           ),
			new Comic("Ctrl+Alt+Del"                          , "CAD"      , "EN", descriptions[5 ], "cad-comic.com"                         ),
			new Comic("The Awkward Yeti"                      , "TAY"      , "EN", descriptions[6 ], "theawkwardyeti.com/"                   ),
			new Comic("The GaMERCaT"                          , "GaMERCaT" , "RU", descriptions[7 ], "comicslate.org/gamer/gamercat"         ),
			new Comic("The GaMERCaT"                          , "GaMERCaT" , "EN", descriptions[8 ], "thegamercat.com/"                      ),
			new Comic("Living with hipstergirl and gamergirl" , "LWHAG"    , "RU", descriptions[9 ], "comicslate.org/gamer/lwhag"            ),
			new Comic("Sequential Art"                        , "SeqArt"   , "EN", descriptions[10], "collectedcurios.com/sequentialart.php" ),
			new Comic("Sabrina Online"                        , "Sabrina"  , "EN", descriptions[11], "sabrina-online.com"                    ),

	};

	static Comic.Page[] pages = new Comic.Page[]{
		new Comic.Page("Licorice", "Not to mention the difficulty of synchronizing your efforts with my once-per-solstice state of carnal arousal!", "https://www.smbc-comics.com/comic/licorice", "https://www.smbc-comics.com/comics/1450454206-20151218.png"),
				new Comic.Page("Quantum mechanics is weird", "And lo, The Lord spake, saying, Let the fundamental equations contain an imaginary component.", "https://www.smbc-comics.com/comic/quantum-mechanics-is-weird", "https://www.smbc-comics.com/comics/1450539983-20151219.png"),
				new Comic.Page("Dad jokes", "I feel like we shouldn't consider bonobos as sapient until they can write something about human life as a sunset or the end of a long road or something.", "https://www.smbc-comics.com/comic/dad-jokes", "https://www.smbc-comics.com/comics/1450366623-20151217.png"),
				new Comic.Page("Thank you for the sex", "PS: Make America Great Again", "https://www.smbc-comics.com/comic/thank-you-for-the-sex", "https://www.smbc-comics.com/comics/1450624616-20151220.png"),
				new Comic.Page("E-stalking", "All old ladies wear pink Mother Hubbard dresses and sit in rocking chairs all the time.", "https://www.smbc-comics.com/comic/e-stalking", "https://www.smbc-comics.com/comics/1450711961-20151221.png"),
				new Comic.Page("God", "Hallowed be thy name, Steve.", "https://www.smbc-comics.com/comic/god", "https://www.smbc-comics.com/comics/1450799655-20151222.png"),
				new Comic.Page("Other riddles of sphinx", "With apologies to anyone of good taste.", "https://www.smbc-comics.com/comic/other-riddles-of-the-sphinx", "https://www.smbc-comics.com/comics/1450886738-20151223.png"),
	};

	public static String smbcSample = "<!DOCTYPE html>\n" +
			"<html>\n" +
			"<head>\n" +
			"\n" +
			"<link href=\"/comiccontrol/defaultstyles.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
			"<link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\" />\n" +
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
			"<link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\">\n" +
			"<link rel=\"icon\" href=\"/favicon.ico\" type=\"image/x-icon\">\n" +
			"\n" +
			"<title>Saturday Morning Breakfast Cereal - 2008-05-21</title>\n" +
			"\n" +
			"<script>\n" +
			"  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n" +
			"  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
			"  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
			"  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n" +
			"\n" +
			"  ga('create', 'UA-3727700-1', 'auto');\n" +
			"  ga('send', 'pageview');\n" +
			"/*\n" +
			"    var _paq = _paq || [];\n" +
			"    _paq.push(['trackPageView']);\n" +
			"    _paq.push(['enableLinkTracking']);\n" +
			"    (function() \n" +
			"    {\n" +
			"        var u=\"//buzz.thehiveworks.com/\";\n" +
			"        _paq.push(['setTrackerUrl', u+'piwik.php']);\n" +
			"        _paq.push(['setSiteId', 7]);\n" +
			"        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];\n" +
			"        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);\n" +
			"    })();\n" +
			"*/\n" +
			"function getBlock(name) {\n" +
			"  if (document.getElementById(name)) {\n" +
			"\treturn document.getElementById(name);\n" +
			"  } else if (document.all) {\n" +
			"\treturn document.all[name];\n" +
			"  } else if (document.layers) {\n" +
			"\treturn document.layers[name];\n" +
			"  }\n" +
			"}\n" +
			"function getStyle(name) {\n" +
			"  return getBlock(name).style;\n" +
			"}\n" +
			"\n" +
			"function hideBlock(name) {\n" +
			"  getStyle(name).display=\"none\";\n" +
			"}\n" +
			"\n" +
			"function showBlock(name) {\n" +
			"  getStyle(name).display=\"\";\n" +
			"}\n" +
			"function toggleBlock(name){\n" +
			"\tif(getStyle(name).display == \"none\"){\n" +
			"\t\tshowBlock(name);\n" +
			"\t}else{\n" +
			"\t\thideBlock(name);\n" +
			"\t}\n" +
			"}\n" +
			"</script>\n" +
			"<script type = \"text/javascript\" src = \"//b.thehiveworks.com/optional/pb-smbc-comics.js\" async></script>\n" +
			"\n" +
			"<link href=\"/smbc.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /> \n" +
			"<link rel=\"alternate\" type=\"application/rss+xml\" \n" +
			"  title=\"RSS Feed for smbc-comics.com\" \n" +
			"  href=\"//www.smbc-comics.com/rss.php\" />\n" +
			"<script>\n" +
			"function getLink(matchClass) {\n" +
			"    var elems = document.getElementsByTagName('a'), i;\n" +
			"\tvar linkname = \"\";\n" +
			"    for (i in elems) {\n" +
			"        if(((' ' + elems[i].className + ' ').indexOf(' ' + matchClass + ' ')\n" +
			"                > -1) && (linkname == \"\")) {\n" +
			"            linkname = elems[i].href;\n" +
			"        }\n" +
			"    }\n" +
			"\treturn linkname;\n" +
			"}\n" +
			"function leftArrowPressed() {\n" +
			"   var prev = getLink(\"prev\");\n" +
			"   window.location = prev;\n" +
			"}\n" +
			"\n" +
			"function rightArrowPressed() {\n" +
			"   var next = getLink(\"next\");\n" +
			"   window.location = next;\n" +
			"}\n" +
			"\n" +
			"function randomPressed() {\n" +
			"   window.location = \"//smbc-comics.com/index.php?id=4136\";\n" +
			"}\n" +
			"\n" +
			"document.onkeydown = function(evt) {\n" +
			"    evt = evt || window.event;\n" +
			"\t\n" +
			"    if (evt.altKey || evt.shiftKey || evt.ctrlKey || evt.metaKey){\n" +
			"\t\treturn;\n" +
			"\t}else{\n" +
			"    switch (evt.keyCode) {\n" +
			"        case 37:\n" +
			"            leftArrowPressed();\n" +
			"            break;\n" +
			"        case 89:\n" +
			"            leftArrowPressed();\n" +
			"            break;\n" +
			"        case 90:\n" +
			"            leftArrowPressed();\n" +
			"            break;\n" +
			"        case 39:\n" +
			"            rightArrowPressed();\n" +
			"            break;\n" +
			"        case 67:\n" +
			"            rightArrowPressed();\n" +
			"            break;\n" +
			"        case 86:\n" +
			"            toggleBlock('aftercomic');\n" +
			"            break;\n" +
			"        case 88:\n" +
			"            randomPressed();\n" +
			"            break;\n" +
			"    }\n" +
			"\t}\n" +
			"};\n" +
			"\n" +
			"</script>\n" +
			"<meta property=\"og:image\" content=\"https://www.smbc-comics.com/comics/20080521.gif\" />\n" +
			"<script src=\"/comiccontrol/includes/ccscripts.js\"></script></head>\n" +
			"<body>\n" +
			"<!-- OnScroll tag: smbc-comics -->\n" +
			"<!--<script src=\"//tags.onscroll.com/385625a0-6ab8-4c1b-8a1d-e31e57b835dc/tag.min.js\" async defer></script>\n" +
			"<script src=\"//ap.lijit.com/www/sovrn_beacon_standalone/sovrn_standalone_beacon.js?iid=8862344&uid=antares\" id=\"sBeacon\"></script>\n" +
			"-->\n" +
			"<div id=\"wrapper\">\n" +
			"\t<div id=\"header\">\n" +
			"    \t<a id=\"logo\" href=\"/index.php\"><img src=\"/images/moblogo.png\" class=\"desktophide\" alt=\"Logo\" /></a>\n" +
			"        <a id=\"mobfacebook\" href=\"https://www.facebook.com/smbccomics\"> <img src=\"/images/mobfacebook.png\" alt=\"FaceBook\" /></a><a id=\"mobtwitter\" href=\"https://twitter.com/ZachWeiner\"><img src=\"/images/mobtwitter.png\" alt=\"Twitter\" /></a><a id=\"mobtumblr\" href=\"//smbc-comics.tumblr.com/\"><img src=\"/images/mobtumblr.png\" alt=\"Tumblr\" /></a>\n" +
			"        <div style=\"clear:both;\"></div>\n" +
			"        <div id=\"mobad1\" class=\"desktophide\">\n" +
			"\t    </div>\n" +
			"\t    \n" +
			"        <div id=\"mobheaderclr\" class=\"desktophide\">&nbsp;</div>\n" +
			"        <div id=\"menu\" class=\"mobilehide\"><a id=\"archive\" href=\"/comic/archive\"></a><a id=\"forum\" href=\"//www.smbc-comics.com/smbcforum\">\n" +
			"        </a>\n" +
			"        <a id=\"store\" href=\"//hivemill.com/collections/smbc\"></a>\n" +
			"        <a id=\"facebook\" href=\"https://www.facebook.com/smbccomics\"></a><a id=\"rss\" href=\"/rss.php\"></a></div>\n" +
			"        \n" +
			"                <div id=\"boardleader\" class=\"mobilehide\">\n" +
			"          <ins data-revive-zoneid=\"142\" data-revive-id=\"c1983f6607c69b6f23252f0cbc167ac1\"></ins>\n" +
			"\t    </div>\n" +
			"\t    \t    \n" +
			"        <a id=\"patreon\" href=\"https://www.patreon.com/ZachWeinersmith?ty=h\" class=\"mobilehide\"></a>\n" +
			"    </div>\n" +
			"    \n" +
			"    <div id=\"mainwrap\">\n" +
			"    \t<div id=\"comicleft\"><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js\"></script>\n" +
			"<script src=\"/clipboard/clipboard.min.js\"></script>\n" +
			"\n" +
			"<div id=\"navtop\">\n" +
			"<div class=\"nav\"><a href=\"https://www.smbc-comics.com/comic/2002-09-05\" class=\"first\" rel=\"start\"></a><a href=\"https://www.smbc-comics.com/comic/2008-05-20\" class=\"prev\" rel=\"prev\"></a><a href=\"https://www.smbc-comics.com/random.php\" class=\"navaux\" rel=\"rss\"></a><a href=\"https://www.smbc-comics.com/comic/2008-05-22\" class=\"next\" rel=\"next\"></a><a href=\"https://www.smbc-comics.com/comic/browser-history\" class=\"last\" rel=\"index\"></a></div></div>\n" +
			"<script>\n" +
			"function getRandomInt(min, max) {\n" +
			"    return Math.floor(Math.random() * (max - min + 1)) + min;\n" +
			"}\n" +
			"var rand = getRandomInt(0,5);\n" +
			"document\n" +
			"if(rand == 0){\n" +
			"\tdocument.write('<a href=\"https://hivemill.com/products/pocket-bible\" style=\"width:684px; margin: 0 auto; display:block; max-width:100%;\"><img src=\"/images/minibible.jpg\" /></a>');\n" +
			"}else if(rand == 1){\n" +
			"\tdocument.write('<a href=\"https://hivemill.com/products/gentlemans-monocle\" style=\"width:684px; margin: 0 auto; display:block; max-width:100%;\"><img src=\"/images/monoclead.jpg\" /></a>');\n" +
			"}else if(rand == 2){\n" +
			"\tdocument.write('<a href=\"https://hivemill.com/collections/smbc/books\" style=\"width:684px; margin: 0 auto; display:block; max-width:100%;\"><img src=\"/images/smbcbooks.jpg\" /></a>');\n" +
			"}else{\n" +
			"\tdocument.write('<a href=\"http://www.soonishbook.com/\" style=\"width:684px; margin: 0 auto; display:block; max-width:100%;\"><img src=\"/images/soonishcomicad.png\" /></a>');\n" +
			"}\n" +
			"</script>\n" +
			"<div id=\"cc-comicbody\"><a href=\"https://www.smbc-comics.com/comic/2008-05-22\">" +

			//image:                                            "\" id=\"cc-comic\;
			"<img title=\"2008-05-21\" src=\"/comics/20080521.gif\" id=\"cc-comic\" border=\"0\" />" +


			"<br /></a></div>\t    <script type=\"text/javascript\" src=\"/showkickstarter.js\" /></script>\n" +
			"\t\t<div id=\"navbottom\"><div class=\"nav\"><a href=\"https://www.smbc-comics.com/comic/2002-09-05\" class=\"first\" rel=\"start\"></a><a href=\"https://www.smbc-comics.com/comic/2008-05-20\" class=\"prev\" rel=\"prev\"></a><a href=\"https://www.smbc-comics.com/random.php\" class=\"navaux\" rel=\"rss\"></a><a href=\"https://www.smbc-comics.com/comic/2008-05-22\" class=\"next\" rel=\"next\"></a><a href=\"https://www.smbc-comics.com/comic/browser-history\" class=\"last\" rel=\"index\"></a></div>\n" +
			"<a id=\"extracomic\" onclick='toggleBlock(\"aftercomic\")' class=\"mobilehide\"></a>\n" +
			"<div id=\"aftercomic\" onclick='toggleBlock(\"aftercomic\")' style=\"display:none;\" class=\"mobilehide\">\n" +
			"   <img src='//smbc-comics.com/comics/20080521after.gif'>\n" +
			"</div>\n" +
			"</div>\n" +
			"<div id=\"buythis\" style=\"\">\n" +
			"        <a href=\"//hivemill.com/products/smbc-print-pages?&podurl=%2F%2Fwww.smbc-comics.com%2Findex.php%3Fid%3D1184\"><img id=\"buythisimg\" src=\"/images/printme.png\" /></a>\n" +
			"    </div>\n" +
			"\n" +
			"\n" +
			"    <button id=\"mobilepermalink\" class=\"desktophide\" data-clipboard-text=\"http://smbc-comics.com/comic/2008-05-21\">Tap to copy a permalink!</button>\n" +
			"\t<div id=\"permalink\" class=\"mobilehide\"><label>Permalink for sharing!</label><input id=\"permalinktext\" type=\"text\" value=\"http://smbc-comics.com/comic/2008-05-21\" /></div>\n" +
			"<div id=\"ibar\"></div>\n" +
			"            <div id=\"sharemob\" class=\"desktophide\"><div id=\"share\"><img src=\"/images/mobshare.png\" /></div><a id=\"facebookshare\" onclick=\"window.open('https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fwww.smbc-comics.com%2Fcomic%2F2008-05-21&t=Saturday+Morning+Breakfast+Cereal','name','width=600,height=400')\"><img src=\"/images/mobfacebookshare.png\" /></a><a id=\"twittershare\"  onclick=\"window.open('https://twitter.com/share?url=http://smbc-comics.com/comic/2008-05-21&text=Saturday Morning Breakfast Cereal%20%23smbc%20%23hiveworks','name','width=600,height=400')\"><img src=\"/images/mobtwittershare.png\" /></a><a id=\"redditshare\" onclick=\"window.location = 'http://www.reddit.com/submit?url=www.smbc-comics.com%2Fcomic%2F2008-05-21'; return false\"><img src=\"/images/mobredditshare.png\" /></a><a id=\"pinterestshare\" onclick=\"window.open('http://www.pinterest.com/pin/create/button/?url=http%3A%2F%2Fwww.smbc-comics.com%2Fcomic%2F2008-05-21&media=http%3A%2F%2Fwww.smbc-comics.com%2Fcomics%2F20080521.gif&description=Saturday%20Morning%20Breakfast%20Cereal','name','width=600,height=400')\"><img src=\"/images/mobpinterestshare.png\" /></a><a id=\"stumbleuponshare\" onclick=\"window.open('http://www.stumbleupon.com/badge/?url=http%3A%2F%2Fwww.smbc-comics.com%2Fcomic%2F2008-05-21%23comic','name','width=600,height=400')\"><img src=\"/images/mobstumbleuponshare.png\" /></a><a id=\"extracomic\" onclick='toggleBlock(\"mobaftercomic\")'><img src=\"/images/mobbutton.png\" /></a> </div>\n" +
			"<div id=\"mobaftercomic\" onclick='toggleBlock(\"mobaftercomic\")' style=\"display:none;\">\n" +
			"   <img src='//smbc-comics.com/comics/20080521after.gif'>\n" +
			"</div>\n" +
			"\n" +
			" <div id=\"sharebar\" class=\"mobilehide\"><div id=\"sharebaricons\"><a id=\"facebookshare\" onclick=\"window.open('https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fwww.smbc-comics.com%2Fcomic%2F2008-05-21&t=Saturday+Morning+Breakfast+Cereal','name','width=600,height=400')\"></a><a id=\"twittershare\"  onclick=\"window.open('https://twitter.com/share?url=http://smbc-comics.com/comic/2008-05-21&text=Saturday Morning Breakfast Cereal%20%23smbc%20%23hiveworks','name','width=600,height=400')\"></a><a id=\"redditshare\" onclick=\"window.location = 'http://www.reddit.com/submit?url=www.smbc-comics.com%2Fcomic%2F2008-05-21'; return false\"></a><a id=\"pinterestshare\" onclick=\"window.open('http://www.pinterest.com/pin/create/button/?url=http%3A%2F%2Fwww.smbc-comics.com%2Fcomic%2F2008-05-21&media=http%3A%2F%2Fwww.smbc-comics.com%2Fcomics%2F20080521.gif&description=Saturday%20Morning%20Breakfast%20Cereal','name','width=600,height=400')\"></a><a id=\"stumbleuponshare\" onclick=\"window.open('http://www.stumbleupon.com/badge/?url=http%3A%2F%2Fwww.smbc-comics.com%2Fcomic%2F2008-05-21%23comic','name','width=600,height=400')\"></a></div></div>\n" +
			" <div id=\"blogheader\" class=\"mobilehide\"></div>\n" +
			"            <div id=\"blogarea\" class=\"mobilehide\">\n" +
			"<div class=\"cc-newsarea\"><div class=\"cc-publishtime\">May 21, 2008<br /></div><div class=\"cc-newsbody\">  <div style=\"padding:10px;clear:both;\"><a href=\"http://www.smbc-comics.com/smbcforum/viewforum.php?f=40\">Discuss this comic in the forum</a></div></div><div class=\"cc-publishtime\">May 20, 2008<br /></div><div class=\"cc-newsbody\">It&#39;s all relative, baby.<div style=\"padding:10px;clear:both;\"><a href=\"http://www.smbc-comics.com/smbcforum/viewforum.php?f=40\">Discuss this comic in the forum</a></div></div><div class=\"cc-publishtime\">May 19, 2008<br /></div><div class=\"cc-newsbody\">Four updates today, and I&#39;m still way behind. Man, I didn&#39;t realize how many days I&#39;d missed.<br>\n" +
			"<br>\n" +
			"PS: Anyone knowledgeable about physics and astronomy who feels like answering some questions for me can feel free to IM me at WizToast.<br><div style=\"padding:10px;clear:both;\"><a href=\"http://www.smbc-comics.com/smbcforum/viewforum.php?f=40\">Discuss this comic in the forum</a></div></div><div class=\"cc-publishtime\">May 18, 2008<br /></div><div class=\"cc-newsbody\">Okay, THIS is the geekiest comic. More updates shortly.<div style=\"padding:10px;clear:both;\"><a href=\"http://www.smbc-comics.com/smbcforum/viewforum.php?f=40\">Discuss this comic in the forum</a></div></div><div class=\"cc-publishtime\">May 17, 2008<br /></div><div class=\"cc-newsbody\">BAM. The time warp between my blog posts and reality is shrinking.<br>\n" +
			"<br>\n" +
			"I&#39;m off to the library. More comics this evening!<div style=\"padding:10px;clear:both;\"><a href=\"http://www.smbc-comics.com/smbcforum/viewforum.php?f=40\">Discuss this comic in the forum</a></div></div></div></div>\n" +
			"\n" +
			"<a id=\"extracomic\" onclick='toggleBlock(\"aftercomic\")' class=\"desktophide\"></a>\n" +
			"<script>\n" +
			"new Clipboard('#mobilepermalink');\n" +
			"\n" +
			"$('#mobilepermalink').click(function(){\n" +
			"\t$(this).html('Permalink copied to clipboard!');\n" +
			"});\n" +
			"</script>\n" +
			"\n" +
			"\n" +
			"\t    <div id=\"mobilemenu\">\n" +
			"            <div style=\"clear:both;\"></div>\n" +
			"            \t    \n" +
			"            <div style=\"clear:both;\"></div>\n" +
			"\n" +
			"        \t<a href=\"//www.thehiveworks.com/\" id=\"mobhiveworks\"><img src=\"/images/mobhiveworks.png\" /></a>\n" +
			"        \t<a href=\"https://www.patreon.com/ZachWeinersmith?ty=h\" id=\"mobpatreon\"><img src=\"/images/mobpatreon.png\" /></a>\n" +
			"            <a href=\"https://www.smbc-comics.com/comic/archive\" id=\"mobarchive\"><img src=\"/images/mobarchive.png\" /></a><a href=\"https://www.smbc-comics.com/smbcforum\" id=\"mobforum\"><img src=\"/images/mobforum.png\" /></a>\n" +
			"            <a href=\"//hivemill.com/collections/smbc\" id=\"mobstore\"><img src=\"/images/mobstore.png\" /></a>\n" +
			"            <div style=\"clear:both; height:10px;\"></div>\n" +
			"        </div>\n" +
			"\n" +
			"\t<script src=\"//cdn.thehiveworks.com/jumpbar.js\"></script>\n" +
			"\t</div>\n" +
			"        \n" +
			"        <div id=\"comicright\" class=\"mobilehide\">\n" +
			"        \t\n" +
			"        \t        \t<div id=\"boxad1\">\n" +
			"          <ins data-revive-zoneid=\"143\" data-revive-id=\"c1983f6607c69b6f23252f0cbc167ac1\"></ins>\n" +
			"\t\t</div>\n" +
			"\t\t\n" +
			"            <div id=\"linkarea\">\n" +
			"            \t<div id=\"linkarealeft\">\n" +
			"                \t<a id=\"weinerworks\" href=\"//www.theweinerworks.com/\"></a>\n" +
			"                    <div id=\"sociallinks\">\n" +
			"                    \t<a href=\"https://twitter.com/ZachWeiner\"></a>\n" +
			"                        <a href=\"//smbc-comics.tumblr.com/\"></a>\n" +
			"                    </div>\n" +
			"        \t\t<p><a href=\"http://smbc-comics.com/soonish/\">SOONISH</a></p>\n" +
			"<p><a href=\"https://www.amazon.co.uk/Soonish-Emerging-Technologies-Improve-Everything/dp/1846148995/ref=sr_1_1?ie=UTF8&amp;qid=1489151548&amp;sr=8-1&amp;keywords=soonish\">SOONISH (UK)</a></p>\n" +
			"<p><a href=\"https://www.reddit.com/r/SMBCComics/\">SMBC on Reddit</a></p>\n" +
			"<p><a href=\"http://cereales.lapin.org/\">SMBC en Fran&ccedil;ais</a></p>\n" +
			"<p><a href=\"http://www.bahfest.com/\">BAHfest</a></p>\n" +
			"<p><a href=\"http://www.weinersmith.com/\">Weinersmith</a></p>\n" +
			"<p><a href=\"http://www.weeklyweinersmith.com/\">Weekly Weinersmith</a></p>\n" +
			"<p><a href=\"http://www.theweinerworks.com\">The Weinerworks</a><br /><a href=\"http://www.theweinerworks.com\">(Book reviews)</a></p>                    \n" +
			"                </div>\n" +
			"                <div id=\"linkarearight\">\n" +
			"                \t<div id=\"contact\"></div>\n" +
			"                    <p><a href=\"mailto:zach@smbc-comics.com\">Email</a></p>\n" +
			"                    <p><a href=\"https://www.facebook.com/smbccomics\">Facebook Fan Club</a></p>\n" +
			"                    <div id=\"appearances\"></div>\n" +
			"                <p style=\"text-align: center;\"><a href=\"http://smbc-comics.com/soonish/uk/tour/\">Check out</a><br /><a href=\"http://smbc-comics.com/soonish/uk/tour/\">the&nbsp;Soonish</a><br /><a href=\"http://smbc-comics.com/soonish/uk/tour/\">book tour!</a></p>\n" +
			"<p style=\"text-align: center;\"><a href=\"http://smbc-comics.com/soonish/uk/tour/\"><img src=\"/uploads/Screenshot_2017-09-19_at_8.42_.01_PM_.png\" alt=\"\" /></a></p>                </div>\n" +
			"            </div>\n" +
			"            <div style=\"clear:both;\"></div>\n" +
			"<!--<div style=\"width:339px;height:50px;margin-left:10px;margin-bottom:5px;\"><a href=\"//www.marecomic.com/comic/intro-page-1/?r=smbc\"><img src=\"/images/mareinternvm-339x50.png\"></a></div>-->\n" +
			"            <a href=\"//www.thehiveworks.com\" id=\"hiveworks\"></a>\n" +
			"        \t        \t    <div id=\"boxad2\">\n" +
			"          <ins data-revive-zoneid=\"240\" data-revive-id=\"c1983f6607c69b6f23252f0cbc167ac1\"></ins>\n" +
			"\t    </div>\n" +
			"\n" +
			"\t\t            <div id=\"newinstoreheader\"></div>\n" +
			"            <div id=\"newinstore\"><a href=\"http://hivemill.com/collections/smbc/products/christmachrist-holiday-card-set\"><img src=\"https://www.smbc-comics.com/images/SMBC-hivemill-christmachrist.png\" /></a></div>\n" +
			"            <div id=\"hobbits\">\n" +
			"            <div class=\"tower\">\n" +
			"          <ins data-revive-zoneid=\"145\" data-revive-id=\"c1983f6607c69b6f23252f0cbc167ac1\"></ins>\n" +
			"\t    </div>\n" +
			"\t    <div class=\"tower\">\n" +
			"          <ins data-revive-zoneid=\"144\" data-revive-id=\"c1983f6607c69b6f23252f0cbc167ac1\"></ins>\n" +
			"\t    </div>\n" +
			"\t    </div>\n" +
			"\t                </div>\n" +
			"        <div style=\"clear:both;\"></div>\n" +
			"\n" +
			"    \t    <div id=\"mobilefooter\">\n" +
			"    \t    <a id=\"mobweinerworks\" href=\"//www.theweinerworks.com/\"><img src=\"/images/mobweinerworks.png\" /></a>\n" +
			"            <a href=\"mailto:zach@smbc-comics.com\" id=\"mobcontact\"><img src=\"/images/mobcontact.png\" /></a>\n" +
			"            <div id=\"mobcopyright\"><img src=\"/images/mobcopyright.png\" />\n" +
			"\n" +
			"            </div>\n" +
			"            <div style=\"clear:both;\"></div>\n" +
			"        </div>\n" +
			"    </div>\n" +
			"    <div id=\"footer\" class=\"mobilehide\">\n" +
			"    \t<a id=\"footerarchive\" href=\"https://www.smbc-comics.com/comic/archive\"><a id=\"footerforum\" href=\"//www.smbc-comics.com/smbcforum\"></a>\n" +
			"    \t<a id=\"footerstore\" href=\"//hivemill.com/collections/smbc\"></a>\n" +
			"    \t<a id=\"footerfacebook\" href=\"https://www.facebook.com/smbccomics\"></a><a id=\"footerrss\" href=\"https://www.smbc-comics.com/rss.php\"></a><a id=\"privacy\" href=\"https://www.smbc-comics.com/privacy.php\"></a>\n" +
			"    \t<a href=\"javascript:buildreportform();\">Report an AD problem</a>\n" +
			"    </div>\n" +
			"\n" +
			"</div>\n" +
			"<!-- Quantcast Tag -->\n" +
			"<script type=\"text/javascript\">\n" +
			"var _qevents = _qevents || [];\n" +
			"\n" +
			"(function() {\n" +
			"var elem = document.createElement('script');\n" +
			"elem.src = (document.location.protocol == \"https:\" ? \"https://secure\" : \"http://edge\") + \".quantserve.com/quant.js\";\n" +
			"elem.async = true;\n" +
			"elem.type = \"text/javascript\";\n" +
			"var scpt = document.getElementsByTagName('script')[0];\n" +
			"scpt.parentNode.insertBefore(elem, scpt);\n" +
			"})();\n" +
			"\n" +
			"_qevents.push({\n" +
			"qacct:\"p-q7HpATVu6wS12\"\n" +
			"});\n" +
			"</script>\n" +
			"\n" +
			"<noscript>\n" +
			"<div style=\"display:none;\">\n" +
			"<img src=\"//pixel.quantserve.com/pixel/p-q7HpATVu6wS12.gif\" border=\"0\" height=\"1\" width=\"1\" alt=\"Quantcast\"/>\n" +
			"\n" +
			"</div>\n" +
			"</noscript>\n" +
			"<!-- End Quantcast tag -->\n" +
			"\n" +
			"</body>\n" +
			"</html>"
			;
}
