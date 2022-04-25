package fileIoComponent.fileIoXmlImpl

import model.gameComponent.BaseImpl.*

import java.io.{File, PrintWriter}
import model.gameComponent.ModelInterface
import fileIoComponent.FileIOInterface

import scala.xml.*
import scala.util.Try

class FileIO extends FileIOInterface :

  override def load(gameManager: ModelInterface):  Try[ModelInterface]  = Try{
    val file = XML.loadFile("CardStack.xml")
    val nodeSeq = file \\ "text"
    var list = List[Card]()

    for(x <- nodeSeq){
      println("Test: " + x.text)
      if(x.text.contains("_")) {
        list = list :+ QuestionCard(x.text)
      } else {
        list = list :+ AnswerCard(x.text)
      }
    }

    val kompCards = KompositumCard(list)
    gameManager.gameManagerG().copy(kompositumCard = kompCards)
  }

  override def save(gameMan: ModelInterface): Try[Unit] = Try {
    val pw = new PrintWriter(new File("CardStack.xml"))
    val cards = <CardStack>{gameMan.kompositumCard.cardList.map(p => p.toXML)}</CardStack>
    pw.write(cards.toString())
    pw.close()
  }