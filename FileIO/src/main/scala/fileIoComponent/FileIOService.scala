package fileIoComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.*
import fileIoComponent.fileIoJsonImpl.FileIO

case object FileIOService {

  val connectionInterface = "0.0.0.0"
  val connectionPort: Int = sys.env.getOrElse("FILE_IO_PORT", 8081).toString.toInt

  def main(args: Array[String]): Unit = {

    val fileIO = new FileIO
    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext = system.executionContext
/*
    val route =
      concat (
        get {
          path("load") {
            complete(HttpEntity(ContentTypes.`application/json`, fileIO.load))
          }
        },
        post {
          path("save") {
            /*
            entity(as [String]) { game =>
              fileIO.save(game)
              println("GAME SAVED")
              complete("game saved")
            */
            }
          }
      )

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
*/

  }
}
