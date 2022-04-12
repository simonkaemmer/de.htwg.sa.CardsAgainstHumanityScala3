package model.fileIoComponent

import model.ModelInterface

import scala.util.Try

trait FileIOInterface {
  def load(gameMan: ModelInterface): Try[ModelInterface]
  def save(game: ModelInterface): Unit
}
