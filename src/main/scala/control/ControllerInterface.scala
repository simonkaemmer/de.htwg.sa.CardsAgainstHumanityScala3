package control
import com.google.inject.Injector
import control.BaseImpl.ControllerState
import model.BaseImpl.GameManager
import fileIoComponent.fileIoJsonImpl.FileIO
import model.ModelInterface
import utils.UndoManager

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def nextState(): Unit
  def changePage(page: Int): Unit
  def eval(input: String): Unit
  def stateAsString(): String
  def undo(): Unit
  def redo(): Unit
  def load(): Unit
  def save(): Unit
  def getCurrentStateAsString: String
  def getGameManager: GameManager
}

import scala.swing.event.Event

class StartPageEvent extends Event
class SecondPageEvent extends Event
class ThirdPageEvent extends Event
class UpdateGuiEvent extends Event
class UpdateTuiEvent extends Event
class UndoEvent extends Event
class NextStateEvent extends Event
class UpdateInfoBarEvent extends Event

