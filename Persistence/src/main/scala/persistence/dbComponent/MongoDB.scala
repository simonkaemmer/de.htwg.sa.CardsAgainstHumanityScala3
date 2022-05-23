package persistence.dbComponent

import persistence.PersistenceInterface
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.model.Projections.excludeId
import org.mongodb.scala.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.model.Sorts.descending
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}

import scala.concurrent.ExecutionContextExecutor
import scala.util.Try
import scala.concurrent.Await
import concurrent.duration.DurationInt

object MongoDB extends PersistenceInterface:
  val uri: String = "mongodb://root:CAH@" + sys.env.getOrElse("MONGODB_HOST", "localhost:27017")
  val client: MongoClient = MongoClient(uri)
  val database: MongoDatabase = client.getDatabase("cah")
  val cardsCollection: MongoCollection[Document] = database.getCollection("cards")

  override def save(json: String): Try[Unit] =
    println("Saving cards in MongoDB")
    val cards: Document = Document.apply(json)
    Try {
      val insertObservable: SingleObservable[InsertOneResult] = cardsCollection.insertOne(cards)
      insertObservable.subscribe(new Observer[InsertOneResult] {
        override def onNext(result: InsertOneResult): Unit = printf(s"inserted: $result\n")

        override def onError(e: Throwable): Unit = printf(s"failed: $e\n")

        override def onComplete(): Unit = printf(s"completed\n")
      })
    }

  override def load(): Try[String] =
    println("Loading cards in MongoDB")
    Try{
      implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SingleRequest")
      implicit val executionContext: ExecutionContextExecutor = system.executionContext
        Await.result(cardsCollection.find().sort(descending("_id")).limit(1).projection(excludeId()).head().map(_.toJson), 2.second)
    }

  override def delete(): Try[Unit] =
    println(s"Deleting cards in MongoDB")
    Try{
        cardsCollection.deleteMany(notEqual("_id", new ObjectId("000000000000000000000000"))).subscribe(
          (_: DeleteResult) => print(s"Deleted all documents\n"),
          (e: Throwable) => print(s"Error when deleting all documents: $e\n")
        )
    }