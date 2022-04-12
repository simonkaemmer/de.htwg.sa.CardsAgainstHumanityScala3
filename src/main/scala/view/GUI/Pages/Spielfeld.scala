package view.GUI.Pages

import java.awt.{Color, Font}

import control._
import model.BaseImpl.AnswerCard
import view.GUI.InfoBar

import scala.swing.event.ButtonClicked
import scala.swing.{BorderPanel, BoxPanel, Button, Dimension, Label, ListView, Orientation}

class Spielfeld(controller: ControllerInterface, infoBar: InfoBar) extends BorderPanel{

  preferredSize = new Dimension(790, 500)
  background = Color.GREEN


  val submitBtn = new Button("Submit / Next Question")
  val nextQuestBtn = new Button("Next Question")
  val playerInfoLbl = new Label("")
  val endString = new Label("Spiel zu Ende")
  endString.foreground = Color.WHITE
  endString.visible = false

  var antworten = new ListView[AnswerCard]()
  var beantwortete: ListView[String] = new ListView[String]() {
  }

  val panelRundenInfo: BoxPanel = new BoxPanel(Orientation.Vertical){
    background = Color.BLACK
    foreground = Color.WHITE
    font = new Font("System", Font.BOLD, 18)

    contents += playerInfoLbl
  }
  val panelControllers: BoxPanel = new BoxPanel(Orientation.Vertical){
    background = Color.BLACK
    foreground = Color.WHITE
    font = new Font("System", Font.BOLD, 18)

    contents += submitBtn
  }
  val panelLinks: BoxPanel = new BoxPanel(Orientation.Vertical){
    background = Color.BLACK;
    contents += antworten
  }
  val panelRechts: BoxPanel = new BoxPanel(Orientation.Vertical){
    background = Color.BLACK
    contents += beantwortete
  }
  val panelKartenauswahl: BoxPanel = new BoxPanel(Orientation.Vertical){
    contents += endString
    background = Color.BLACK
  }

  add(panelRundenInfo, BorderPanel.Position.North)
  add(panelControllers, BorderPanel.Position.South)
  add(panelLinks, BorderPanel.Position.West)
  add(panelRechts, BorderPanel.Position.East)
  add(panelKartenauswahl, BorderPanel.Position.Center)

  listenTo(controller)
  listenTo(submitBtn)
  listenTo(nextQuestBtn)

  reactions += {
    case event: UpdateInfoBarEvent => {
      infoBar.text = controller.getCurrentStateAsString
      infoBar.background = Color.BLACK
      infoBar.foreground = Color.WHITE
      infoBar.font = new Font("System", Font.BOLD, 18)
      print("InfoBarEvent")
    }

    case event: UpdateGuiEvent => {
      print("UpdateGuiEvent")

      var tmpList = List[String]()
      controller.getGameManager.roundAnswerCards.foreach(x => tmpList = tmpList :+ "Spieler " + x._1.name + " hat " + x._2)
      beantwortete = new ListView[String](tmpList)
      beantwortete.background = Color.GREEN
      beantwortete.foreground = Color.WHITE
      panelRechts.revalidate()
      panelRechts.repaint()

      antworten = new ListView(controller.getGameManager.player(controller.getGameManager.activePlayer).playerCards.toSeq)
      antworten.background = Color.BLACK
      antworten.foreground = Color.WHITE
      panelLinks.contents.update(0, antworten)
      panelLinks.revalidate()
      panelLinks.repaint()

      playerInfoLbl.text = "Aktiver Spieler: " + controller.getGameManager.player(controller.getGameManager.activePlayer).name

      panelRechts.contents.update(0, beantwortete)

    }
    case ButtonClicked(b) if b == submitBtn => {
      if(controller.getGameManager.numberOfRounds > controller.getGameManager.numberOfPlayableRounds)
        endString.visible = true

      val index = antworten.selection.anchorIndex
      controller.eval(index.toString)

    }
    case ButtonClicked(b) if b == nextQuestBtn => {
      controller.eval("")
    }
  }
}