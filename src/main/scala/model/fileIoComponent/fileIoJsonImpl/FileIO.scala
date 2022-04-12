package model.fileIoComponent.fileIoJsonImpl

import model.BaseImpl.{AnswerCard, Card, KompositumCard, QuestionCard}
import model.ModelInterface
import model.fileIoComponent.FileIOInterface
import play.api.libs.json.*

import scala.io.Source
import scala.util.Try

class FileIO extends FileIOInterface:

  override def load(gameMan: ModelInterface): Try[ModelInterface]  = Try {
    val source: String = Source.fromFile("CardStack.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val cards = (json \\ "card")
    var tempList = List[Card]()
    for(x <- cards){
      if(x.toString.contains("_")) {
        val tmpText = x.toString().replace("\"","")
        tempList = tempList :+ QuestionCard(tmpText)
      } else{
        val tmpText = x.toString().replace("\"","")
        tempList = tempList :+ AnswerCard(tmpText)
      }
    }
    val kompCards = KompositumCard(tempList)
    gameMan.gameManagerG().copy(kompositumCard = kompCards)
  }

  override def save(game: ModelInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("CardStack.json"))
    pw.write(Json.prettyPrint(cardsStackToJson(game)))
    pw.close()
  }

  def cardsStackToJson(game: ModelInterface): JsObject = {
    Json.obj(
      "cardList" -> Json.toJson(for{x <- game.kompositumCard.cardList} yield {
        Json.obj("card" -> JsString(x.toString))})
    )
  }

