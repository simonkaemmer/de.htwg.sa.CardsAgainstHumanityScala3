package fileIoComponent

import fileIoComponent.fileIoJsonImpl.FileIO
import model.gameComponent.BaseImpl.GameManager
import fileIoComponent.fileIoJsonImpl.FileIO
import model.gameComponent.ModelInterface

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.*
import fileIoComponent.fileIoJsonImpl.FileIO

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

case object FileIOService:
  def main(args: Array[String]): Unit =
    val fileIO = FileIO()
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val interface = "localhost"
    val port = 8081

    println(s"FileIO service started: http://$interface:$port")

    val route =
      concat (
        get {
          path("") {
            val apiInfo =
              """Available API Routes - Persistence:
                |
                |GET     /load
                |POST    /save    -> required arguments: gameJson
                |""".stripMargin
            complete(HttpEntity(ContentTypes.`application/json`, apiInfo))
          }
        },
        get {
          path("load") {
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
            }
        },
        post {
          path("save") {
            entity(as[String]) { game =>
              fileIO.save(game) match
                case Success(s) => complete("Success")
                case Failure(e) => complete("Failure")
            }
          }
        }
      )

    Http().newServerAt(interface, port).bind(route)
