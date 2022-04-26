package view

import control.{ControllerInterface, UpdateTuiEvent}

import scala.concurrent.ExecutionContextExecutor
import scala.swing.Publisher
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors


class RestTui extends Publisher {

  val appPort: Int = sys.env.getOrElse("APP_PORT", 8080).toString.toInt
  val connectionInterface = "0.0.0.0"

  def run(): Unit:
    println("Started Rest-API")
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val route = concat(
      pathPrefix("tui") {
        concat(
          newCommand,
          quitCommand,
          evalCommand
        )
      }
    )
    val bindingFuture = Http().newServerAt(connectionInterface, appPort).bind(route)

    println(s"Server online at http://$connectionInterface:$appPort/\n.....")

  def newCommand: Route = pathPrefix("new") {
    post {
      parameter("") {

      }
    }
  }
  def quitCommand: Route = pathPrefix("quit") {
    post {
      parameter("") {

      }
    }
  }
  def evalCommand: Route = pathPrefix("eval") {
    post {
      parameter("") {

      }
    }
  }
}
