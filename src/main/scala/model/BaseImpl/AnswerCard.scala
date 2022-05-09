package model.BaseImpl

import scala.xml.Node

case class AnswerCard(antwort: String) extends Card :

  override def printCard() = println("Meine Antwort ist: " + antwort)

  override def toString: String = antwort

  override def toXML: Node = <card>
    <text>
      {antwort}
    </text>
  </card>
