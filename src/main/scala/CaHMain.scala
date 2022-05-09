import module.CardsAgainstHumanityModule
import com.google.inject.{Guice, Injector}
import control.BaseImpl.Controller
import view.GUI.SwingGui
import view.RestTui

import scala.io.StdIn.readLine


object CaHMain {
  val injector: Injector = Guice.createInjector(new CardsAgainstHumanityModule)
  val controller: Controller = injector.getInstance(classOf[Controller])
  val gui = new SwingGui(controller)

  val restService = RestTui(controller)
  val server = restService.start()
  //val tui = new Tui(controller)

  def main(args: Array[String]): Unit = {
   //var input: String = "0"






    gui.open()

    var input: String = ""
    if Console.in.ready() then
      input = readLine()
    else
    input = ""
    while input != "q" do
      if Console.in.ready() then
        input = readLine()
      else
        input = ""
    restService.stop(server)



    /*
    if (args.length>0) input=args(0)
    else do{
      input = readLine()
      tui.processInputLine(input)
    } while(input != "q")
    */
  }
}
