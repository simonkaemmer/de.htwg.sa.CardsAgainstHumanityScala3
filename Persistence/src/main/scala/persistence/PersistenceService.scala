package persistence

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import fileIoComponent.fileIoJsonImpl.FileIO
import persistence.dbComponent.{MongoDB, Slick}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.Await

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.util.{Failure, Success}

case object PersistenceService:
  def main(args: Array[String]): Unit =
   // val persistence = Slick
    val persistence = MongoDB

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
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
            complete(HttpEntity(ContentTypes.`application/json`, Await.result(persistence.load(), Duration.Inf)))
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

    val bindingFuture = Http().newServerAt(interface, port).bind(route)

    bindingFuture.onComplete {
      case Success(binding) => {
        val address = binding.localAddress
      }
      case Failure(exception) => {
        println("Persistence Service couldn't be started because of Error: " + exception + "\n")
      }
    }

    println(s"FileIO service started: http://$interface:$port")
    println("Press return to stop")
    StdIn.readLine()
