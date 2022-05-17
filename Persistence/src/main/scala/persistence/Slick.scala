package persistence
import persistence.sqlTables.*

import scala.util.Try
import slick.dbio.{DBIO, Effect}
import play.api.libs.json.{JsArray, JsBoolean, JsNumber, JsString, JsValue, Json}
import slick.lifted.TableQuery
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.MySQLProfile.api.*

import scala.:+
import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.util.{Failure, Success}

object Slick extends PersistenceInterface:
  val databaseUrl: String = "jdbc:mysql://" + sys.env.getOrElse("DATABASE_HOST", "localhost:3306") + "/" + sys.env.getOrElse("MYSQL_DATABASE", "cah") + "?serverTimezone=UTC&useSSL=false"
  val databaseUser: String = sys.env.getOrElse("MYSQL_USER", "root")
  val databasePassword: String = sys.env.getOrElse("MYSQL_PASSWORD", "CAH")

  val database = Database.forURL(
    url = databaseUrl,
    driver = "com.mysql.cj.jdbc.Driver",
    user = databaseUser,
    password = databasePassword
  )

  val questionCardsTable = TableQuery.apply[QuestionCardsTable]
  val answerCardsTable = TableQuery.apply[AnswerCardsTable]

  val questionCardsSetup: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(questionCardsTable.schema.createIfNotExists)
  val answerCardsSetup: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(questionCardsTable.schema.createIfNotExists)

  database.run(questionCardsSetup)
  database.run(answerCardsSetup)

  override def save(json: String): Try[Unit] =
    println("Saving cards in MySQL")
    val cardsJson = Json.parse(json)
    val questCardsJson= (cardsJson \ "cardList").head
    val answerCardJson = (cardsJson \ "cardList").last
    Try{
      database.run(questionCardsTable ++= (
        (questCardsJson \\ "card").map(s => s.toString).toSeq
        ))

      database.run(answerCardsTable ++= (
        (answerCardJson \\ "card").map(s => s.toString).toSeq
        ))

      println(cardsJson)
    }

  override def load(): Try[String] =
    Try {
      database.run(questionCardsTable.result).map(_.foreach {
        case (questionCards) =>
          println("Name: " + questionCards)


      })
      ""
    }


