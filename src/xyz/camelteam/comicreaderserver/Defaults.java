package xyz.camelteam.comicreaderserver;

public class Defaults {

	/* Что ещё можно добавить:
	Three Word Phrase
	Dinosaur Comics
	Oglaf (nsfw)
	A Softer World
	Buttersafe
	Perry Bible Fellowship
	Questionable Content
	Buttercup Festival
	Homestuck
	Junior Scientist
	Power Hour
	 */

	final static String[] descriptions = {
			/*0  */ "by Zach Weinersmith. Recurring themes include atheism, God, superheroes, romance, dating, science, research, parenting and the meaning of life.", // SMBC
			/*1  */ "by Randall Munroe. A webcomic of romance, sarcasm, math, and language.", //XKCD
			/*2  */ "by Рэндел Манро. Это вебкомикс о любви, сарказме, математике и языке. ", //XKCD
			/*3  */ "by Mark Stanley. Научно-фантастический веб-комикс о злоключениях экипажа космического корабля «Свирепая курица»", // Freefall
			/*4  */ "by Mark Stanley.  Set on a planet in the early stages of terraforming, the strip deals with the antics of alien spaceship \"captain\" Sam Starfall, his robot friend Helix, and their Bowman's Wolf engineer Florence Ambrose.", // Eng Freefall
			/*5  */ "by Tim Buckley. A gaming-related webcomic and animated series.", //CAD
			/*6  */ "Webcomics about life, science and other stuff I guess.", //TAY
			/*7  */ "He's a cat. He plays video games.",//Gamercat
			/*8  */ "He's a cat. He plays video games.",//Gamercat
			/*9  */ "by Jago Dibuja.", // LWHAG
			/*10 */ "by Tom Fishbach.", // TwoKinds
			/*11 */ "by Phillip M Jackson (aka JollyJack)", // SeqArt
			/*12 */ "by Eric W. Schwartz", // Sabrina

			/*13 */ "Three Word Phrase",      // TWP
			/*14 */ "Dinosaur Comics",        // DioComics
			/*15 */ "Oglaf (nsfw)",           // Oglaf
			/*16 */ "A Softer World",         // SofterW
			/*17 */ "Buttersafe",             // Buttersafe
			/*18 */ "Perry Bible Fellowship", // PBF
			/*19 */ "Questionable Content",   // QC
			/*20 */ "Buttercup Festival",     // BF
			/*21 */ "Homestuck",              // Homestuck
			/*22 */ "Junior Scientist",       // JunScientist
			/*23 */ "Power Hour",             // PowerHour
			/*24 */ "Роботы, киборги и космос - всё, как положено.",             // Samy
	};

	// TODO: Временный (или дефолтный, кто знает) набор комиков
	static Comic[] comicsList = {
			new Comic("Saturday Morning Breakfast Cereal"     , "SMBC"     , descriptions[0 ], "EN", "smbc-comics.com"                       ),
			new Comic("XKCD"                                  , "XKCD"     , descriptions[1 ], "EN", "xkcd.com"                              ),
			new Comic("XKCD"                                  , "XKCD"     , descriptions[2 ], "RU", "xkcd.ru"                               ),
			new Comic("Freefall"                              , "Freefall" , descriptions[3 ], "RU", "comicslate.org/sci-fi/freefall"        ),
			new Comic("Freefall"                              , "Freefall" , descriptions[4 ], "EN", "http://freefall.purrsia.com"           ),
			new Comic("Ctrl+Alt+Del"                          , "CAD"      , descriptions[5 ], "EN", "cad-comic.com"                         ),
			new Comic("The Awkward Yeti"                      , "TAY"      , descriptions[6 ], "EN", "theawkwardyeti.com/"                   ),
			new Comic("The GaMERCaT"                          , "GaMERCaT" , descriptions[7 ], "RU", "comicslate.org/gamer/gamercat"         ),
			new Comic("The GaMERCaT"                          , "GaMERCaT" , descriptions[8 ], "EN", "thegamercat.com/"                      ),
			new Comic("Living with hipstergirl and gamergirl" , "LWHAG"    , descriptions[9 ], "RU", "comicslate.org/gamer/lwhag"            ),
			new Comic("Two Kinds"                             , "TwoKinds" , descriptions[10], "EN", "comicslate.org/furry/2kinds"           ),
			new Comic("Sequential Art"                        , "SeqArt"   , descriptions[11], "EN", "collectedcurios.com/sequentialart.php" ),
			new Comic("Sabrina Online"                        , "Sabrina"  , descriptions[12], "EN", "sabrina-online.com"                    ),
			new Comic("Three Word Phrase"                     ,"TWP"       , descriptions[13], "EN", ""),
			new Comic("Dinosaur Comics"                       ,"DioComics" , descriptions[14], "EN", ""),
			new Comic("Oglaf (nsfw)"                          ,"Oglaf"     , descriptions[15], "EN", ""),
			new Comic("A Softer World"                        ,"SofterW"   , descriptions[16], "EN", ""),
			new Comic("Buttersafe"                            ,"Buttersafe", descriptions[17], "EN", ""),
			new Comic("Perry Bible Fellowship"                ,"PBF"       , descriptions[18], "EN", ""),
			new Comic("Questionable Content"                  ,"QC"        , descriptions[19], "EN", ""),
			new Comic("Buttercup Festival"                    ,"BF"        , descriptions[20], "EN", ""),
			new Comic("Homestuck"                             ,"Homestuck" , descriptions[21], "EN", ""),
			new Comic("Junior Scientist"                      ,"JunScient" , descriptions[22], "EN", ""),
			new Comic("Power Hour"                            ,"PowerHour" , descriptions[23], "EN", ""),
			new Comic("Samy"                                  ,"Samy"      , descriptions[24], "RU", "comicslate.org/sci-fi/sammy/")

	};
}
