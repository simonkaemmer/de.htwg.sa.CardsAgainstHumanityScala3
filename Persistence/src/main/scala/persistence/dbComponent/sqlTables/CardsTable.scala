package persistence.dbComponent.sqlTables

import slick.jdbc.MySQLProfile.api._

class QuestionCardsTable(tag: Tag) extends Table[(String)](tag, "QUESTIONCARDS"):
  def questionCards = column[String]("QUESTION_CARDS")

  override def * = questionCards



class AnswerCardsTable(tag: Tag) extends Table[(String)](tag, "ANSWERCARDS"):
  def answerCards = column[String]("ANSWER_CARDS")

  override def * = answerCards