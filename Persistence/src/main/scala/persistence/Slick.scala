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
  val databasePassword: String = sys.env.getOrElse("MYSQL_PASSWORD", "cah")

  val database = Database.forURL(
    url = databaseUrl,
    driver = "com.mysql.cj.jdbc.Driver",
    user = databaseUser,
    password = databasePassword
  )

  //???????????????????????????????????????
  val cardsTable = TableQuery.apply[CardsTable]


  val setup: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(cardsTable.schema.createIfNotExists)
  database.run(setup)

  override def save(json: String): Try[Unit] =
    println("Saving game in MySQL")
    val cardsJson = Json.parse(json)
    Try{
      database.run(cardsTable += (
        (cardsJson \ "cards" \ "questionCards").get.toString,
        (cardsJson \ "cards" \ "answerCards").get.toString
      ))
    }

  override def load(): Try[String] =
    Try {
//      val questionCards = sql"""SELECT * FROM questionCards""".as[(String)]
//      val answerCards = sql"""SELECT * FROM answerCards""".as[(String)]

      "test"


//      val result = Await.result(database.run(actionQuery), 2.second)


//      Json.obj("cardList" -> Json.obj(
//        "questionCards" -> JsArray(for element <- questionCards yield
//          JsString(element.toString)
//        ),
//        "answerCards" -> JsArray(for element <- answerCards yield
//          JsString(card.toString)
//        )
//      )).toString
    }