package fileIoComponent.fileIoXmlImpl

import java.io.{File, PrintWriter}
import fileIoComponent.FileIOInterface

import scala.xml.*
import scala.util.Try

class FileIO extends FileIOInterface:
  override def load(): Try[String] = Try { scala.xml.XML.loadFile("CardStack.xml").toString() }

  override def save(game: String): Try[Unit] = Try {
    import java.io._
    val pw = new PrintWriter(new File("CardStack.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    pw.write(game)
    pw.close()
  }





















//  override def load(gameManager: ModelInterface):  Try[ModelInterface]  = Try{
//    val file = XML.loadFile("CardStack.xml")
//    val nodeSeq = file \\ "text"
//    var list = List[Card]()
//
//    for(x <- nodeSeq){
//      println("Test: " + x.text)
//      if(x.text.contains("_")) {
//        list = list :+ QuestionCard(x.text)
//      } else {
//        list = list :+ AnswerCard(x.text)
//      }
//    }
//
//    val kompCards = KompositumCard(list)
//    gameManager.gameManagerG().copy(kompositumCard = kompCards)
//  }
//
//  override def save(game: String): Try[Unit] = Try {
//    import java.io._
//    val pw = new PrintWriter(new File("game.xml"))
//    val prettyPrinter = new PrettyPrinter(120, 4)
//    pw.write(game)
//    pw.close()
//  }