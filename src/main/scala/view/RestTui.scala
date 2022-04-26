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
          placeCardCommand,

        )
      }
    )
}
