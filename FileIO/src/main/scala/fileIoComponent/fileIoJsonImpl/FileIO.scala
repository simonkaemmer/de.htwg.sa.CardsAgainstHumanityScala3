package fileIoComponent.fileIoJsonImpl

import model.gameComponent.BaseImpl.*
import model.gameComponent.ModelInterface
import fileIoComponent.FileIOInterface
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

  override def save(game: String): Try[Unit] = Try {
//    import java.io._
//    val pw = new PrintWriter(new File("CardStack.json"))
//    pw.write(Json.prettyPrint(cardsStackToJson(game)))
//    pw.close()
  import java.io._
  val print_writer = new PrintWriter(new File("matchField.json"))
  print_writer.write(game)
  print_writer.close()


  }

//  def cardsStackToJson(game: ModelInterface): JsObject = {
//    Json.obj(
//      "cardList" -> Json.toJson(for{x <- game.kompositumCard.cardList} yield {
//        Json.obj("card" -> JsString(x.toString))})
//    )
//  }

