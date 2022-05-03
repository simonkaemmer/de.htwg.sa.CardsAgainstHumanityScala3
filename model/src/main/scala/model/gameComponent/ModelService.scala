package model.gameComponent
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.*
import com.fasterxml.jackson.annotation.JsonValue
import play.api.libs.json.{JsValue, Json}
import scala.concurrent.ExecutionContextExecutor
import model.gameComponent.BaseImpl.GameManager


case object ModelService {

  def main(args: Array[String]): Unit = {
    val game = GameManager()

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val interface = "localhost"
    val port = 8082

    val test = game.playerFromJson(game.gameToJson(), 2)

    println(s"Game services started @ http://$interface:$port")

    val route =
      concat(
        get {
          path("") {
            val apiInfo = Json.obj("ApiInfo" -> Json.obj(
              "Available GET-routes:" -> Json.obj(
                "/game" -> "Returns whole model-data"
              ), "Available POST-routes:" -> Json.obj(
                "/roundStrategy" -> "Args: amountOfPlayers: Int",
                "/createCardDeck" -> "Args: None",
                "/handOutCards" -> "Args: None",
                "/givePlayersCargs" -> "listOfPlayersCards: List[AnswerCard]",
                "/playerHand" -> "Args: listOfAnswerCards: List[AnswerCard]",
                "/choosePlayerStartCards" -> "Args: amountOfPlayers: Int",
                "/placeQuestionCard" -> "Args: None",
                "/placeCard" -> "Args: activePlayer: Int, card: AnswerCard",
                "/pickNextPlayer" -> "Args: None",
                "/drawCard" -> "Args: None",
                "/clearRoundAnswer" -> "Args: None"
              )
            )).toString

            complete(HttpEntity(ContentTypes.`application/json`, apiInfo))
          }
        },
        get {
          path("game") {
            complete(HttpEntity(ContentTypes.`application/json`, game.gameToJson()))
          }
        },
        post {
          path("roundStrategy") {
//            entity(as[String]) { request =>
//              val json: JsValue = Json.parse(request)
//              val amountOfPlayers = (json \ "amountOfPlayers").get.toString.toInt
//
//            }
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("createCardDeck") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("handOutCards") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("givePlayerCards") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("playerHand") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("choosePlayerStartCards") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("placeQuestionCard") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("placeCard") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("pickNextPlayer") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("drawCard") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        },
        post {
          path("clearRoundAnswers") {
            complete(HttpEntity(ContentTypes.`application/json`, "Working!"))
          }
        }
      )
    val bindingFuture = Http().newServerAt(interface, port).bind(route)
  }
}
