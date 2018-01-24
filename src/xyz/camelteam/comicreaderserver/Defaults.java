package xyz.camelteam.comicreaderserver;

import com.google.gson.Gson;

class Defaults {

	/* Сайты-сборники: acomics, comicslate, furry.ru... */

	private static String[] descriptions = {
			/*  1 SMBC"       */ "by Zach Weinersmith. Recurring themes include atheism, God, superheroes, romance, dating, science, research, parenting and the meaning of life.",
			/*  2 XKCD"       */ "by Randall Munroe. A webcomic of romance, sarcasm, math, and language.",
			/*  3 XKCD"       */ "by Рэндел Манро. Это вебкомикс о любви, сарказме, математике и языке. ",
			/*  4 Freefall"   */ "by Mark Stanley. Set on a planet in the early stages of terraforming, the strip deals with the antics of alien spaceship \"captain\" Sam Starfall, his robot friend Helix, and their Bowman's Wolf engineer Florence Ambrose.", // Eng Freefall
			/*  5 Freefall"   */ "by Mark Stanley. Научно-фантастический веб-комикс о злоключениях экипажа космического корабля «Свирепая курица»",
			/*  6 GaMERCaT"   */ "He's a cat. He plays video games.",
			/*  7 GaMERCaT"   */ "He's a cat. He plays video games.",
			/*  8 LWHAG"      */ "by Jago Dibyja. Парень живёт с двумя девушками, и у каждой свои тараканы в голове...",
			/*  9 LWHAG"      */ "by Jago Dibyja. Парень живёт с двумя девушками, и у каждой свои тараканы в голове...",
			/* 10 Owlturd"    */ "Разнообразные (иногда абсурдные) уморительные комиксы без общей идеи.",
			/* 11 TwoKinds"   */ "by Tom Fishbach.",
			/* 12 Samy"       */ "Роботы, киборги и космос - всё, как положено.",
			/* 13 DoodleTime" */ "Жизнь и не только глазами почти обычной девушки.",

			/*  TAY"        */ "Webcomics about life, science and other stuff I guess.",
			/*  CAD"        */ "by Tim Buckley. A gaming-related webcomic and animated series.",
			/*  SeqArt"     */ "by Phillip M Jackson (aka JollyJack)",
			/*  Sabrina"    */ "by Eric W. Schwartz",
			/*  DioComics"  */ "Dinosaur Comics",
			/*  Oglaf"      */ "Oglaf (nsfw)",
			/*  SofterW"    */ "A Softer World",
			/*  Buttersafe" */ "Buttersafe",
			/*  PBF"        */ "Perry Bible Fellowship",
			/*  QC"         */ "Questionable Content",
			/*  BF"         */ "Buttercup Festival",
			/*  Homestuck"  */ "Homestuck",
			/*  JunScient"  */ "Junior Scientist",
			/*  PowerHour"  */ "Power Hour",
			/*  TWP"        */ "Three Word Phrase",
	};

	// TODO: Временный (или дефолтный, кто знает) набор комиков
	static Comic[] comicsList = {
			/*  1 */ new Comic("Saturday Morning Breakfast Cereal"     , "enSMBC"      , "EN", "smbc-comics.com"                       ),
			/*  2 */ new Comic("XKCD"                                  , "enXKCD"      , "EN", "xkcd.com"                              ),
			/*  3 */ new Comic("XKCD"                                  , "ruXKCD"      , "RU", "xkcd.ru"                               ),
			/*  4 */ new Comic("Freefall"                              , "enFreefall"  , "EN", "freefall.purrsia.com"                  ),
			/*  5 */ new Comic("Freefall"                              , "ruFreefall"  , "RU", "comicslate.org/sci-fi/freefall"        ),
			/*  6 */ new Comic("The GaMERCaT"                          , "ruGaMERCaT"  , "RU", "comicslate.org/gamer/gamercat"         ),
			/*  7 */ new Comic("The GaMERCaT"                          , "enGaMERCaT"  , "EN", "thegamercat.com/"                      ),
			/*  8 */ new Comic("Living with hipstergirl and gamergirl" , "ruLWHAG"     , "RU", "acomics.ru/~LwHG/"                     ),
			/*  9 */ new Comic("Living with hipstergirl and gamergirl" , "ruLWHAG"     , "EN", "comicslate.org/gamer/lwhag"            ),
			/* 10 */ new Comic("Owlturd"                               , "ruOwlturd"   , "RU", "acomics.ru/~owlturd"                   ),
			/* 11 */ new Comic("Two Kinds"                             , "enTwoKinds"  , "EN", "comicslate.org/furry/2kinds"           ),
			/* 12 */ new Comic("Sammy"                                 , "ruSammy"     , "RU", "comicslate.org/sci-fi/sammy/"          ),
			/* 13 */ new Comic("Время мазни!"                          , "ruDoodleTime", "RU", "acomics.ru/~doodle-time"               ),

			new Comic("Ctrl+Alt+Del"                          , "CAD"       , "EN", "cad-comic.com"                         ),
			new Comic("The Awkward Yeti"                      , "TAY"       , "EN", "theawkwardyeti.com/"                   ),
			new Comic("Sequential Art"                        , "SeqArt"    , "EN", "collectedcurios.com/sequentialart.php" ),
			new Comic("Sabrina Online"                        , "Sabrina"   , "EN", "sabrina-online.com"                    ),
			new Comic("Dinosaur Comics"                       , "DioComics" , "EN", ""),
			new Comic("Oglaf (nsfw)"                          , "Oglaf"     , "EN", ""),
			new Comic("A Softer World"                        , "SofterW"   , "EN", ""),
			new Comic("Buttersafe"                            , "Buttersafe", "EN", ""),
			new Comic("Perry Bible Fellowship"                , "PBF"       , "EN", ""),
			new Comic("Questionable Content"                  , "QC"        , "EN", ""),
			new Comic("Buttercup Festival"                    , "BF"        , "EN", ""),
			new Comic("Homestuck"                             , "Homestuck" , "EN", ""),
			new Comic("Junior Scientist"                      , "JunScient" , "EN", ""),
			new Comic("Power Hour"                            , "PowerHour" , "EN", ""),
			new Comic("Three Word Phrase"                     , "TWP"       , "EN", ""),
	};

	static String getComicsList() {
		for (int i = 0; i < comicsList.length; i++) comicsList[i].description = descriptions[i];
		return new Gson().toJson(comicsList);
	}
}
