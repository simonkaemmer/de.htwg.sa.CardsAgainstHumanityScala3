package persistence.dbComponent

import persistence.dbComponent.sqlTables.*
import persistence.PersistenceInterface

import scala.util.Try
import slick.dbio.{DBIO, Effect}
import play.api.libs.json.{JsArray, JsBoolean, JsNumber, JsString, JsValue, Json}
import slick.lifted.TableQuery
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.MySQLProfile.api.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

import scala.:+
import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.util.{Failure, Success}

import scala.concurrent.Await
import scala.util.Try

object Slick extends PersistenceInterface :
  val databaseUrl: String = "jdbc:mysql://" + sys.env.getOrElse("DATABASE_HOST", "localhost:3306") + "/" + sys.env.getOrElse("MYSQL_DATABASE", "cah") + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"
  val databaseUser: String = sys.env.getOrElse("MYSQL_USER", "root")
  val databasePassword: String = sys.env.getOrElse("MYSQL_PASSWORD", "CAH")
  println(databaseUrl)
  println(databaseUser)
  println(databasePassword)


  val database = Database.forURL(
    url = databaseUrl,
    driver = "com.mysql.cj.jdbc.Driver",
    user = databaseUser,
    password = databasePassword
  )

  val questionCardsTable = TableQuery[QuestionCardsTable]
  val answerCardsTable = TableQuery[AnswerCardsTable]
  database.run(questionCardsTable.schema.createIfNotExists)
  database.run(answerCardsTable.schema.createIfNotExists)

  override def save(json: String): Try[Unit] =
    println("Saving cards in MySQL")
    val cardsJson = Json.parse(json)
    val questCardsJson = (cardsJson \ "cardList").head
    val answerCardsJson = (cardsJson \ "cardList").last
    Try {
      database.run(questionCardsTable ++= (
        (questCardsJson \\ "card").map(s => s.toString.replace("\"", "")).toSeq
        ))

      database.run(answerCardsTable ++= (
        (answerCardsJson \\ "card").map(s => s.toString).toSeq
        ))

      println(cardsJson)
    }

  override def load(): Future[String] =

      val queryQ = (sql"""SELECT * FROM QUESTIONCARDS """).as[(String)]
      val resultQ = Await.result(database.run(queryQ), Duration.Inf)

      val queryA = (sql"""SELECT * FROM ANSWERCARDS """).as[(String)]
      val resultA = Await.result(database.run(queryA), Duration.Inf)

      val finalResult = Json.obj("cardList" -> Json.arr(Json.obj(
        "questionCards" -> JsArray(for card <- resultQ yield
          Json.obj("card" -> JsString(card.toString))
        )),
        Json.obj("answerCards" -> JsArray(for card <- resultA yield
          Json.obj("card" -> JsString(card.toString))
        ))
      )).toString

      Future(finalResult)

  override def delete(): Try[Unit] =
    println("Deleting game in MySQL")
    Try{
      val queryQ = (sql"""DELETE FROM QUESTIONCARDS""").as[Unit]
      val queryA = (sql"""DELETE FROM ANSWERCARDS""").as[Unit]
      database.run(queryQ)
      database.run(queryA)
    }