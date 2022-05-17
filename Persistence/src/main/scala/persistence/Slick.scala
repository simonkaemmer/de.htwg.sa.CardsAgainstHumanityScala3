package persistence
import persistence.sqlTables.CardsTable

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

  val gameTable = TableQuery.apply[CardsTable]

  val setup: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(gameTable.schema.createIfNotExists)
  database.run(setup)

  override def save(json: String): Try[Unit] =
    println("Saving cards in MySQL")
    val gameJson = Json.parse(json)
    Try{
      database.run(gameTable += (
        0,
        (gameJson \ "game" \ "questionCards").as[List[String]].mkString(","),
        (gameJson \ "game" \ "answerCards").as[List[String]].mkString(",")
      ))
      println(gameJson)
    }

  override def load(): Try[String] =
    Try {
      val actionQuery = sql"""SELECT * FROM CARDS ORDER BY ID DESC LIMIT 1""".as[(Int,String, String)]
      val result = Await.result(database.run(actionQuery), 2.second)
      println(result.toString)
      Json.obj("cardList" -> Json.obj(
        "questionCards" -> JsArray(for card <- result(0)(1) yield
          JsString(card.toString)
        ),
        "answerCards" -> JsArray(for card <- result(0)(2) yield
          JsString(card.toString)
        )
      )).toString
    }