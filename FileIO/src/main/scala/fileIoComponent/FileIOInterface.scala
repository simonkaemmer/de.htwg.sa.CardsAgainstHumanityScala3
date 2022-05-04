package fileIoComponent

import scala.util.Try
//import model.gameComponent.ModelInterface

trait FileIOInterface {
  def load(): Try[String]

  def save(game: String): Try[Unit]
}
