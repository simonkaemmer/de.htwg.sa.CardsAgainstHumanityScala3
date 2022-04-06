package model.fileIoComponent.fileIoXmlImpl

import java.io.{File, PrintWriter}

import model.BaseImpl.{AnswerCard, Card, KompositumCard, QuestionCard}
import model.ModelInterface
import model.fileIoComponent.FileIOInterface
import scala.xml._

class FileIO extends FileIOInterface {

  override def load(gameManager: ModelInterface): ModelInterface = {
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

  override def save(gameMan: ModelInterface): Unit = {
    val pw = new PrintWriter(new File("CardStack.xml"))
    val cards = <CardStack>{gameMan.kompositumCard.cardList.map(p => p.toXML)}</CardStack>
    pw.write(cards.toString())
    pw.close()
  }
}