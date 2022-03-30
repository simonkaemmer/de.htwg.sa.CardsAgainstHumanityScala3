package module


import com.google.inject.AbstractModule
import com.google.inject.name.Names
import model.BaseImpl.GameManager
import model.ModelInterface
import model.fileIoComponent.FileIOInterface
import model.fileIoComponent.fileIoJsonImpl.FileIO

class CardsAgainstHumanityModule extends AbstractModule {
  val defPlayers: Int = 2;


  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("Default")).to(defPlayers)
    bind(classOf[ModelInterface]).to(classOf[GameManager])
    bind(classOf[FileIOInterface]).to(classOf[FileIO])
    ()
  }
}
