package persistence

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import fileIoComponent.fileIoJsonImpl.FileIO
import persistence.dbComponent.{MongoDB, Slick}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.util.{Failure, Success}

case object PersistenceService:
  def main(args: Array[String]): Unit =
    val persistence = Slick
    // val persistence = MongoDB

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val interface = "persistence-service"
    //val interface = "localhost"
    val port = 8084

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
            println("loading Cards")
            
            persistence.load() match
              case Success(cards) => complete(HttpEntity(ContentTypes.`application/json`, cards))
              case Failure(e) =>
                println(e.printStackTrace())
                complete("Failure")
          }
        },
        get {
          path("delete") {
            persistence.delete() match
              case Success(game) => complete("Success")
              case Failure(e) =>
                println(e.printStackTrace())
                complete("Failure")
          }
        },
        post {
          path("save") {
            println("saving Cards")
            
            entity(as[String]) { cards =>
              println(cards)
              persistence.save(cards) match
                case Success(s) => complete("Success")
                case Failure(e) => complete("Failure")
            }
          }
        }
      )

    Http().newServerAt(interface, port).bind(route)

    println(s"FileIO service started: http://$interface:$port")
    println("Press return to stop")
    StdIn.readLine()
