package persistence

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import fileIoComponent.fileIoJsonImpl.FileIO


import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.util.{Failure, Success}

case object PersistenceService {

  def main(args: Array[String]): Unit = {
    val persistence = Slick

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val interface = "persistence-service"
    //val interface = "localhost"
    val port = 8084

    println(s"Game services started @ http://$interface:$port")

    val route =
      concat(
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
            persistence.load() match
              case Success(cards) => {
                println("FIO-Service" + cards)
                complete(HttpEntity(ContentTypes.`application/json`, cards))
              }
              case Failure(e) => complete("Failure")
          }
        },
        post {
          path("save") {
            entity(as[String]) { cards =>
              persistence.save(cards) match
                case Success(s) => complete("Success")
                case Failure(e) => complete("Failure")
            }
          }
        }
      )
    val bindingFuture = Http().newServerAt(interface, port).bind(route)

    val card = persistence.load()


    println("Press return to stop")
    StdIn.readLine()
  }
}
