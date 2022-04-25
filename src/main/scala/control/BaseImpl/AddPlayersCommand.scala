package control.BaseImpl

import control.ControllerInterface
import model.gameComponent.BaseImpl.AnswerCard
import model.gameComponent.BaseImpl.Player
import utils.Command

class AddPlayersCommand(name: String, controller: ControllerInterface) extends Command{

  override def doStep: Unit = {

    var playerTmp = controller.getGameManager.player
    playerTmp = playerTmp :+ Player(name, true, List[AnswerCard]())
    //controller.getGameManager.player = playerTmp

    controller.getGameManager.gameManagerG().copy(player = playerTmp)
  }

  override def undoStep: Unit = {

    var playerTmp = controller.getGameManager.player;
    playerTmp = playerTmp.filterNot(_==playerTmp.last)
    //controller.getGameManager.player = playerTmp

    controller.getGameManager.copy(player = playerTmp)
  }

  override def redoStep: Unit = doStep
}
