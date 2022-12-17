package cz.cvut.fit.bioop.hackernewsclient.util

import scala.io.BufferedSource

object BufferUtil {

  implicit class BufferFormat(buffer: BufferedSource) {

    def formattedString(): String = {
      buffer.getLines()
        .reduce(
          (first: String, second: String) => first + second
        )
    }

  }

}
