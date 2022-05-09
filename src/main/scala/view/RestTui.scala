package view

import control.{ControllerInterface, UpdateTuiEvent}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.swing.Publisher
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.server.Directives.*

import scala.util.{Failure, Success}

class RestTui(controller: ControllerInterface) {
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val interface = "localhost"
  val port = 8080

  def start(): Future[Http.ServerBinding] = {
    println(s"server started: http://$interface:$port")
    val route =
      concat(
        post {
          path("eval") {
            entity(as[String]) { input =>
              input match{
                case "quit" =>
                case "undo" => controller.undo()
                case "redo" => controller.redo()
                case _ => controller.eval(input)
              }
              complete(HttpEntity(ContentTypes.`application/json`, "working"))
            }
          }
        }
      )
    Http().newServerAt(interface, port).bind(route)
  }

  def stop(server: Future[Http.ServerBinding]): Unit = server.flatMap(_.unbind()).onComplete(_ => println(port + " released"))
}
