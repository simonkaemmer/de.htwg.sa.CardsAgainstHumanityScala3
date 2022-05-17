package persistence.sqlTables

import slick.jdbc.MySQLProfile.api._

class CardsTable(tag: Tag) extends Table[(Int, String, String)](tag, "CARDS"):
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def questionCards = column[String]("QUESTION_CARDS")
  def answerCards = column[String]("ANSWER_CARDS")

  override def * = (id, questionCards, answerCards)