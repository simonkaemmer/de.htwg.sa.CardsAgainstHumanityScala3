
package persistence

import scala.concurrent.Future
import scala.util.Try

trait PersistenceInterface:
  def load(): Future[String]
  def save(json: String): Try[Unit]
  def delete(): Try[Unit]