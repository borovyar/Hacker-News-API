package cz.cvut.fit.bioop.hackernewsclient.repository.file
import cz.cvut.fit.bioop.hackernewsclient.Properties._
import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.model.cache.CacheData

import java.io.{File, FileNotFoundException, PrintWriter}
import scala.io.Source

class FileCacheSystemImpl extends FileSystem[CacheData] {


  override def loadData(): Option[CacheData] ={
    try{
      val buffer = Source.fromFile(CACHE_FIlE_NAME)
      val strings = buffer.getLines().mkString; buffer.close()

      Some(read[CacheData](strings))
    }catch {
      case _: FileNotFoundException => None
    }
  }

  override def saveData(cacheData: CacheData): Unit = {
    val printWriter = new PrintWriter(new File(CACHE_FIlE_NAME))

    printWriter.write(write(cacheData))
    printWriter.close()
  }

  override def clearData(): Unit = {
    val file = new File(CACHE_FIlE_NAME)

    if(file != null && file.exists())
      file.delete()
  }
}
