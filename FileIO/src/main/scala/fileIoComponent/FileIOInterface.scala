package fileIoComponent

import scala.util.Try
import model.gameComponent.ModelInterface

trait FileIOInterface {
  def load(gameMan: ModelInterface): Try[ModelInterface]

  def save(game: ModelInterface): Try[Unit]
}
