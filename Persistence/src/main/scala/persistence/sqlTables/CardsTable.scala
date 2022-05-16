
package persistence.sqlTables

import slick.jdbc.MySQLProfile.api._

class CardsTable(tag: Tag) extends Table[(String, String)](tag, "CARDS"):
//  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def questionCards = column[String](
    "Siskel and Ebert have panned _ as 'poorly conceived', and 'sloppily executed.'"
//    "Up next on Nickelodeon: 'Clarissa Explains _.'",
//    "Believe it or not, Jim Carrey can do a dead-on impression of _.",
//    "It's Morphin' Time! Mastadon! Pterodactyl! Triceratops! Sabertooth Tiger! _!",
//    "I'm a bitch, I'm a lover, I'm a child, I'm _."



  )
  def answerCards = column[String](
    "A bus that will explode if it goes under 50 miles per hour."
//    "Sucking the President's dick.",
//    "Sunny D! Alright!",
//    "A mulatto, an albino, a mosquito, and my libido.",
//    "Jerking off to a 10-second RealMedia clip."





  )


  override def * = (questionCards,answerCards)