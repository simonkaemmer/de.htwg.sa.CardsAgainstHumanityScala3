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

    println(s"Game services started @ http://$interface:$port")

    val route =
      concat(
        get {
          path("") {
            val apiInfo =
              """Available API Routes - Model:
                |
                |POST    TEST TEST
                |""".stripMargin
            complete(HttpEntity(ContentTypes.`application/json`, apiInfo))
          }
        }
      )
  }
}
