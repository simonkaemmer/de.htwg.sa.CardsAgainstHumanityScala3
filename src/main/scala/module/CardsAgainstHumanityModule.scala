package module

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import control.BaseImpl.Controller
import control.ControllerInterface
import model.BaseImpl.GameManager
import model.ModelInterface
import persistence.fileIoComponent.FileIOInterface
import persistence.fileIoComponent.fileIoJsonImpl.FileIO

class CardsAgainstHumanityModule extends AbstractModule {
  val defPlayers: Int = 2

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("Def")).to(defPlayers)
    bind(classOf[ModelInterface]).toInstance(GameManager())

    bind(classOf[ControllerInterface]).to(classOf[control.BaseImpl.Controller])
    bind(classOf[FileIOInterface]).to(classOf[FileIO])
  }
}
