package control.BaseImpl

import com.google.inject.{Guice, Inject, Injector}
import control.*
import model.BaseImpl.GameManager
import module.CardsAgainstHumanityModule
import utils.UndoManager

import scala.util.{Failure, Success, Try}
import scala.swing.Publisher
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}

import scala.concurrent.ExecutionContextExecutor
import scala.swing.{Color, Publisher}
import scala.util.{Failure, Success, Try}
import akka.http.scaladsl.unmarshalling.Unmarshaller
import model.ModelInterface
import play.api.libs.json.{JsValue, Json}

class Controller @Inject() (var gameManager: ModelInterface) extends ControllerInterface with Publisher {

  var state: ControllerState = PreSetupState(this)
  val undoManager = new UndoManager
  val injector: Injector = Guice.createInjector(new CardsAgainstHumanityModule)

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val modelHttpServer: String = sys.env.getOrElse("MODELHTTPSERVER", "localhost:8082")
  val fileIOHttpServer: String = sys.env.getOrElse("FILEHTTPSERVER", "localhost:8084")


  def nextState(): Unit = state = state.nextState

  def save(): Unit =
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://$fileIOHttpServer/save",
        entity = HttpEntity(ContentTypes.`application/json`, gameManager.kompCardToJson())
      )
    ).onComplete {
      case Success(value) =>
        Unmarshaller.stringUnmarshaller(value.entity).onComplete {
          case Success(value) =>
            if value.equals("Success") then
              println("success1: " + value)
            else
              state = state.failState
          case Failure(_) =>
              state = state.failState
        }
      case Failure(_) =>
              state = state.failState
    }

  def load(): Unit =
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"http://$fileIOHttpServer/load"
      )
    ).onComplete {
      case Success(value) =>
        Unmarshaller.stringUnmarshaller(value.entity).onComplete {
          case Success(value) =>
            if value.equals("Failure") then
              state = state.failState
            else{
              println("Cards in loading: " + value)
              gameManager = gameManager.kompCardFromJson(value)

            }
          case Failure(_) =>
              state = state.failState
        }
      case Failure(_) =>
              state = state.failState
    }

  def changePage(page: Int): Unit = {
    page match{
      case 1 => publish(new StartPageEvent)
      case 2 => publish(new SecondPageEvent)
      case 3 => publish(new ThirdPageEvent)
    }
  }

  def eval(input: String): Unit = {
    state.evaluate(input)
  }

  def stateAsString(): String = {
    state match {
      case _: PreSetupState => "PreSetupGame"
      case _: SetupState => "SetupGame"
      case _: AnswerState => "AnswerState"
      case _: FinishState => "FinishState"
      case _: FileFailState => "FailState"
    }
  }

  def getCurrentStateAsString: String = {state.stateString}


  def undo(): Unit = {
    val result = undoManager.undoStep()
    result match
      case Success(value) =>
        publish(new UndoEvent)
      case Failure(e) =>
        state = state.failState
  }

  def getGameManager: GameManager = gameManager.gameManagerG()

  def redo(): Unit = {
    val result = undoManager.redoStep()
    result match
      case Success(value) => publish(new UndoEvent)
      case Failure(e) => state = state.failState

  }
  }

trait ControllerState {

  def evaluate(input: String): Unit

  def stateString: String

  def nextState: ControllerState

  def failState: ControllerState
}

case class PreSetupState(controller: Controller) extends ControllerState {


  override def evaluate(input: String): Unit = {
      controller.load()
    println("HEEEEREEEE   "+ controller.gameManager.kompositumCard)
      controller.gameManager = controller.gameManager.roundStrat(input.toInt) //WebApi/roundStrat
      controller.changePage(2)
      controller.publish(new UpdateGuiEvent)
      //controller.publish(new UpdateTuiEvent)
      controller.nextState()
  }

/*  controller.gameManager = controller.fileMan.load(controller.gameManager).get*/

  override def stateString: String = "Willkommen bei Cards Against Humanity \n"

  override def nextState: ControllerState = AddCardsQuest(controller)

  override def equals(that: Any): Boolean = ???

  override def failState: ControllerState = FileFailState(controller)
}

case class AddCardsQuest(controller: Controller) extends ControllerState {

  //controller.publish(new UpdateTuiEvent)
  controller.publish(new UpdateGuiEvent)
  override def evaluate(input: String): Unit = {
    if (input.equals("Weiter") || input.equals("weiter")) {
      controller.nextState()
      controller.publish(new ThirdPageEvent)
    } else {
      controller.undoManager.doStep(new AddCardsCommand(input, this.controller))
      controller.publish(new UpdateInfoBarEvent)
      controller.publish(new UpdateGuiEvent)
    }
  }

  override def stateString: String = "AddCardState"

  override def nextState: ControllerState = SetupState(controller)

  override def failState: ControllerState = FileFailState(controller)
}

case class SetupState(controller: Controller) extends ControllerState {

  override def evaluate(input: String): Unit = {

    if (input.isEmpty) return

    controller.undoManager.doStep(new AddPlayersCommand(input, controller))
    controller.publish(new UpdateGuiEvent)
    controller.publish(new UpdateTuiEvent)

    controller.gameManager = controller.gameManager.addPlayer(input)
    if (controller.getGameManager.player.length == controller.getGameManager.numberOfPlayers) {
      controller.gameManager = controller.gameManager.createCardDeck()
      controller.gameManager = controller.gameManager.handOutCards()
      controller.nextState()
      controller.publish(new UpdateGuiEvent)
      controller.publish(new NextStateEvent)
    }
  }

  override def stateString: String = "SetupState"

  override def nextState: ControllerState = AnswerState(controller)

  override def failState: ControllerState = FileFailState(controller)
}

case class AnswerState(controller: Controller) extends ControllerState {

  override def evaluate(input: String): Unit = {
    if(input== "" || controller.getGameManager.roundAnswerCards.size == controller.getGameManager.player.length) {
      controller.gameManager = controller.gameManager.clearRoundAnswers()
      controller.gameManager = controller.gameManager.placeQuestionCard()
      controller.publish(new UpdateInfoBarEvent)
      controller.publish(new UpdateGuiEvent)
      controller.publish(new UpdateTuiEvent)
    } else {
      if (controller.getGameManager.roundAnswerCards.size == controller.getGameManager.player.size) {
        controller.gameManager = controller.gameManager.drawCard()
        controller.publish(new UpdateGuiEvent)
        controller.publish(new UpdateTuiEvent)
        controller.nextState()
      }
      val activePlayer = controller.gameManager.activePlayer
      if (input.toInt >= 0 && input.toInt < controller.getGameManager.player(activePlayer).playerCards.length) {
        controller.gameManager = controller.gameManager.placeCard(activePlayer, controller.getGameManager.player(activePlayer).playerCards(input.toInt))
        controller.gameManager = controller.gameManager.pickNextPlayer()
        controller.publish(new UpdateGuiEvent)
        controller.publish(new UpdateTuiEvent)
      }
    }

    if(controller.getGameManager.numberOfRounds >= controller.getGameManager.numberOfPlayableRounds)
      controller.nextState()
  }

  override def stateString: String = controller.getGameManager.roundQuestion

  override def failState: ControllerState = FileFailState(controller)

  override def nextState: ControllerState = {
    if(controller.getGameManager.numberOfRounds > controller.getGameManager.numberOfPlayableRounds) {
      FinishState(controller)
    } else {
      controller.publish(new UpdateGuiEvent)
      this
    }
  }
}

case class FinishState(controller: Controller) extends ControllerState {
  override def evaluate(input: String): Unit = ()

  override def stateString: String = "Please write q to exit the game"

  override def nextState: ControllerState = this

  override def equals(that: Any): Boolean = ???
  override def failState: ControllerState = FileFailState(controller)
}

case class FileFailState(controller: Controller) extends ControllerState {

  override def evaluate(input: String): Unit = ()

  override def stateString: String = "Can´t find or read card-file. Please make it is called \"CardStack\"!"

  override def nextState: ControllerState = this

  override def equals(that: Any): Boolean = ???

  override def failState: ControllerState = {


    this
  }

}
