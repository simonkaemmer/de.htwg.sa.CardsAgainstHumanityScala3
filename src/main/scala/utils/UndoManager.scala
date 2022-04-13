package utils

import scala.util.Try

class UndoManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil

  def doStep(command: Command) = {
    undoStack = command::undoStack
    command.doStep
  }

  def undoStep(): Try[Unit] = Try {
    undoStack match {
      case Nil =>
      case head::stack => {
        head.undoStep
        undoStack=stack
        redoStack= head::redoStack
      }
    }
  }

  def redoStep(): Try[Unit] = Try {
    redoStack match {
      case Nil =>
      case head::stack => {
        head.redoStep
        redoStack=stack
        undoStack=head::undoStack
      }
    }
  }
}
