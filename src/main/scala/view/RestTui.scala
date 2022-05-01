package view

import control.{ControllerInterface, UpdateTuiEvent}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.swing.Publisher
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.server.Directives._

class RestTui(controller: ControllerInterface) {
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val interface = "localhost"
  val port = 8080

  def start(): Future[Http.ServerBinding] = {
    println(s"server started: http://$interface:$port")
    val route =
      concat(
        get {
          path("") {
            val apiInfo =
              """Available API Routes - Uno:
                |
                |GET    /new-game/<numOfPlayers>
                |GET    /set-card/<card>
                |GET    /get-card
                |GET    /redo
                |GET    /undo
                |GET    /save
                |GET    /load
                |GET    /do-step
                |""".stripMargin
            complete(HttpEntity(ContentTypes.`application/json`, apiInfo))
          }
        }
      )
    Http().newServerAt(interface, port).bind(route)
  }

  def stop(server: Future[Http.ServerBinding]): Unit = server.flatMap(_.unbind()).onComplete(_ => println(port + " released"))
}
