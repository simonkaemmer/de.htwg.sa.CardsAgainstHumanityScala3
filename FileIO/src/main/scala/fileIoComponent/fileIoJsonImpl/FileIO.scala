package fileIoComponent.fileIoJsonImpl

import fileIoComponent.FileIOInterface
import play.api.libs.json.*

import scala.io.Source
import scala.util.Try

class FileIO extends FileIOInterface:
  override def load(): Try[String] = Try { Source.fromFile("CardStack.json").getLines().mkString }
  
  override def save(cards: String): Try[Unit] = Try {
    import java.io._
    val pw = new PrintWriter(new File("CardStack.json"))
    pw.write(cards)
    pw.close()
  }



