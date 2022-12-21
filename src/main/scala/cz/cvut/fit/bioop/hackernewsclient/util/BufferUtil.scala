package cz.cvut.fit.bioop.hackernewsclient.util

import scala.io.BufferedSource

/***
 *  Helper for BufferedSource parameter
 */
object BufferUtil {

  /***
   * Format buffer to string using simple concatenation
   *
   * @param buffer string which would be reduced
   */
  implicit class BufferFormat(buffer: BufferedSource) {
    def formattedString(): String = {
      buffer.getLines()
        .reduce(
          (first: String, second: String) => first + second
        )
    }
  }

}
