package model.gameComponent.BaseImpl

import com.google.inject.Inject

import scala.xml.Node

trait  Card {
  def printCard(): Unit
  def toXML: Node
}

case class KompositumCard @Inject()(cardList:List[Card]) extends Card:

  override def printCard(): Unit = {
    cardList.foreach((c:Card)=>{
      c.printCard()
    })
    print(")\n")
  }

  def addNewCards(cards : List[String]) : KompositumCard = {

    var list: List[Card] = this.cardList
    for(x <- cards){

      if(x.contains("_")) {
        list = list :+ QuestionCard(x)
      } else {
        list = list :+ AnswerCard(x)
      }
    }
      copy(cardList = list)
  }

  def addNewCard(card: Card): KompositumCard = {
    val immutableList = cardList :+ card
    copy(immutableList)
  }

  def removeCard(card:Card): KompositumCard = {copy(cardList.filterNot(_ == card))}

  override def toXML: Node = {<ERROR></ERROR>}

