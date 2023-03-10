package cz.cvut.fit.bioop.hackernewsclient.parser

/***
 * Render HTML text
 */
object HtmlParser {

  implicit class HtmlText(htmlText: String) {

    /***
     * Replaces special HTML characters with equivalent character in ASCII
     * @return
     */
    def replaceHtmlCharacters(): String = {
      var result = htmlText
      HtmlParser.specialCharacters.foreachEntry((symbol, character) =>
        result = result.replace(symbol, character.toString)
      )
      result
    }
  }

  /***
   * Parse HTML text to common one by replacement HTML tags and special characters
   *
   * @param text in HTML
   * @return common text
   */
  def toFormattedText(text: String): String = {
    val buffer = text.replaceHtmlCharacters().replaceAll("<p>", "\n")

    buffer.replaceAll("<i>|<em>", "\u001b[3m")
      .replaceAll("</i>|</em>", "\u001b[0m")
      .replaceAll("<strong>|<b>", "\u001b[1m")
      .replaceAll("</strong>|</b>", "\u001b[0m")
      .replaceAll("<[^<]*>", "")
  }

  val specialCharacters: Map[String, Char] = Map(
    "&copy;" -> 169,
    "&ordf;" -> 170,
    "&laquo;" -> 171,
    "&not;" -> 172,
    "&shy;" -> 173,
    "&reg;" -> 174,
    "&macr;" -> 175,
    "&deg;" -> 176,
    "&plusmn;" -> 177,
    "&sup2;" -> 178,
    "&sup3;" -> 179,
    "&acute;" -> 180,
    "&micro;" -> 181,
    "&quot;" -> 34,
    "&amp;" -> 38,
    "&lt;" -> 60,
    "&gt;" -> 62,
    "&nbsp;" -> 160,
    "&iexcl;" -> 161,
    "&cent;" -> 162,
    "&pound;" -> 163,
    "&curren;" -> 164,
    "&yen;" -> 165,
    "&brvbar;" -> 166,
    "&sect;" -> 167,
    "&uml;" -> 168,
    "&para;" -> 182,
    "&middot;" -> 183,
    "&Egrave;" -> 200,
    "&Eacute;" -> 201,
    "&Ecirc;" -> 202,
    "&cedil;" -> 184,
    "&sup1;" -> 185,
    "&ordm;" -> 186,
    "&raquo;" -> 187,
    "&frac14;" -> 188,
    "&frac12;" -> 189,
    "&frac34;" -> 190,
    "&iquest;" -> 191,
    "&times;" -> 215,
    "&divide;" -> 247,
    "&Agrave;" -> 192,
    "&Aacute;" -> 193,
    "&Acirc;" -> 194,
    "&Atilde;" -> 195,
    "&Auml;" -> 196,
    "&Aring;" -> 197,
    "&AElig;" -> 198,
    "&Ccedil;" -> 199,
    "&Euml;" -> 203,
    "&Igrave;" -> 204,
    "&Iacute;" -> 205,
    "&Icirc;" -> 206,
    "&Iuml;" -> 207,
    "&ETH;" -> 208,
    "&Ntilde;" -> 209,
    "&Ograve;" -> 210,
    "&Oacute;" -> 211,
    "&Ocirc;" -> 212,
    "&Otilde;" -> 213,
    "&Ouml;" -> 214,
    "&Oslash;" -> 216,
    "&Ugrave;" -> 217,
    "&Uacute;" -> 218,
    "&Ucirc;" -> 219,
    "&Uuml;" -> 220,
    "&Yacute;" -> 221,
    "&THORN;" -> 222,
    "&szlig;" -> 223,
    "&agrave;" -> 224,
    "&aacute;" -> 225,
    "&acirc;" -> 226,
    "&atilde;" -> 227,
    "&auml;" -> 228,
    "&aring;" -> 229,
    "&aelig;" -> 230,
    "&ccedil;" -> 231,
    "&egrave;" -> 232,
    "&eacute;" -> 233,
    "&ecirc;" -> 234,
    "&euml;" -> 235,
    "&igrave;" -> 236,
    "&iacute;" -> 237,
    "&icirc;" -> 238,
    "&iuml;" -> 239,
    "&eth;" -> 240,
    "&ucirc;" -> 251,
    "&uuml;" -> 252,
    "&ntilde;" -> 241,
    "&ograve;" -> 242,
    "&oacute;" -> 243,
    "&ocirc;" -> 244,
    "&otilde;" -> 245,
    "&ouml;" -> 246,
    "&oslash;" -> 248,
    "&ugrave;" -> 249,
    "&uacute;" -> 250,
    "&yacute;" -> 253,
    "&thorn;" -> 254,
    "&yuml;" -> 255,
    "&#x27;" -> 39,
    "&#x2F;" -> 47
  )
}
