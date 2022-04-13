package model.BaseImpl

import scala.xml.Node


case class QuestionCard(question:String) extends Card:

  override def printCard(): Unit = {println("Meine Frage ist: " + question)}
  override def toString: String = question
  override def toXML: Node = <card><text>{question}</text></card>

