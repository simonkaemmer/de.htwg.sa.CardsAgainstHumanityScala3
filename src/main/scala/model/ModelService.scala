package model

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import play.api.libs.json.{JsValue, Json}

import model.BaseImpl._

import scala.concurrent.ExecutionContextExecutor

case object ModelService {

  def main(args: Array[String]): Unit = {
    var game = GameManager().dummyData()
    println(game.gameToJson())
    game.pickNextPlayer()
    println(game.gameToJson())

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val interface = "localhost"
    val port = 8082


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
                "/placeQuestionCard" -> "Args: None",
                "/placeCard" -> "Args: activePlayer: Int, card: AnswerCard",
                "/pickNextPlayer" -> "Args: None",
                "/drawCard" -> "Args: None",
                "/clearRoundAnswer" -> "Args: None",
                "/setKompCards" -> "Args: ???"
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
        get {
          path("testKompToJson") {
            complete(HttpEntity(ContentTypes.`application/json`, game.kompCardToJson()))
          }
        },
        post {
          path("testKompFromJson") {
            entity(as[String]) { request =>
              game = game.kompCardFromJson(request)
              complete(HttpEntity(ContentTypes.`application/json`, game.kompCardToJson()))
            }

          }
        },
        post {
          path("roundStrategy") {
            entity(as[String]) { request =>
              val json: JsValue = Json.parse(request)
              val amountOfPlayers = (json \ "amountOfPlayers").get.toString.toInt
              val gameString = (json \ "game").get.toString
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(gameString).roundStrat(amountOfPlayers).gameToJson()))
            }
          }
        },
        post {
          path("createCardDeck") {
            entity(as[String]) { request =>
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).createCardDeck().gameToJson()))
            }
          }
        },
        post {
          path("handOutCards") {
            entity(as[String]) { request =>
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).handOutCards().gameToJson()))
            }
          }
        },
        post {
          path("placeQuestionCard") {
            entity(as[String]) { request =>
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).placeQuestionCard().gameToJson()))
            }
          }
        },
        post {
          path("placeCard") {
            entity(as[String]) { request =>
              val json: JsValue = Json.parse(request)
              val activePlayer: Int = (json \ "game" \ "activePlayer").get.toString.toInt
              val card: AnswerCard = AnswerCard((json \ "card").get.toString)
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).placeCard(activePlayer, card).gameToJson()))
            }
          }
        },
        post {
          path("pickNextPlayer") {
            entity(as[String]) { request =>
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).pickNextPlayer().gameToJson()))
            }
          }
        },
        post {
          path("drawCard") {
            entity(as[String]) { request =>
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).drawCard().gameToJson()))
            }
          }
        },
        post {
          path("clearRoundAnswers") {
            entity(as[String]) { request =>
              complete(HttpEntity(ContentTypes.`application/json`, game.gameFromJson(request).clearRoundAnswers().gameToJson()))
            }
          }
        }
      )
    val bindingFuture = Http().newServerAt(interface, port).bind(route)
  }
}
