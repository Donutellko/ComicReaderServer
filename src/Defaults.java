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
			/*10 */ "by Phillip M Jackson (aka JollyJack)", // SeqArt
			/*11 */ "by Eric W. Schwartz", // Sabrina

			/*12 */ "Three Word Phrase",      // TWP
			/*13 */ "Dinosaur Comics",        // DioComics
			/*14 */ "Oglaf (nsfw)",           // Oglaf
			/*15 */ "A Softer World",         // SofterW
			/*16 */ "Buttersafe",             // Buttersafe
			/*17 */ "Perry Bible Fellowship", // PBF
			/*18 */ "Questionable Content",   // QC
			/*19 */ "Buttercup Festival",     // BF
			/*20 */ "Homestuck",              // Homestuck
			/*21 */ "Junior Scientist",       // JunScientist
			/*22 */ "Power Hour",             // PowerHour
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
			new Comic("Three Word Phrase"                     ,"TWP"       , "EN", descriptions[12], ""),
			new Comic("Dinosaur Comics"                       ,"DioComics" , "EN", descriptions[13], ""),
			new Comic("Oglaf (nsfw)"                          ,"Oglaf"     , "EN", descriptions[14], ""),
			new Comic("A Softer World"                        ,"SofterW"   , "EN", descriptions[15], ""),
			new Comic("Buttersafe"                            ,"Buttersafe", "EN", descriptions[16], ""),
			new Comic("Perry Bible Fellowship"                ,"PBF"       , "EN", descriptions[17], ""),
			new Comic("Questionable Content"                  ,"QC"        , "EN", descriptions[18], ""),
			new Comic("Buttercup Festival"                    ,"BF"        , "EN", descriptions[19], ""),
			new Comic("Homestuck"                             ,"Homestuck" , "EN", descriptions[20], ""),
			new Comic("Junior Scientist"                      ,"JunScient" , "EN", descriptions[21], ""),
			new Comic("Power Hour"                            ,"PowerHour" , "EN", descriptions[22], "")

	};
}
