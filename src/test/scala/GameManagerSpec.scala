import com.sun.org.apache.bcel.internal.generic.INSTANCEOF
import model.BaseImpl.{AnswerCard, Card, GameManager, KompositumCard, QuestionCard}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GameManagerSpec extends AnyWordSpec  with Matchers{

  var gm = GameManager()

  "A Gamemanager" should {

    "contain following values after creation" in {
      gm.numberOfPlayers shouldBe 0
      gm.numberOfRounds shouldBe 0
      gm.kompositumCard shouldNot be(0)
      gm.player shouldNot be(0)
      gm.answerList shouldBe Nil
      gm.questionList shouldBe Nil
      gm.roundAnswerCards shouldNot be(0)
      gm.roundQuestion shouldBe ""
    }
    "have players" in {
      gm = gm.roundStrat(2)
      gm = gm.addPlayer("Hugo")
      gm = gm.addPlayer("Heinz")

      gm.numberOfPlayers shouldBe 2
      gm.player.length shouldBe 2
      gm.player(1).toString shouldBe "Player: Heinz // State: true"
    }
    "create a Carddeck" in {
      gm.copy(kompositumCard = KompositumCard(List[Card](AnswerCard("hahah"), AnswerCard("Hihihihi"), QuestionCard("Wie bitte _ ?"))))
      gm = gm.createCardDeck()
      gm.player.length shouldBe 2
      gm.answerList shouldNot be(Nil)
      gm.questionList shouldNot be(Nil)
    }
    "handout cards to players" in {
      gm = gm.handOutCards()
      gm.player(0).getCards shouldNot be(empty)
    }
    "should place a question card" in {
      gm = gm.placeQuestionCard()
      gm.roundQuestion shouldNot be(Nil)
    }
    "should place a answer card" in {
      gm = gm.placeCard(0, AnswerCard("blah"))
      gm.roundAnswerCards shouldNot be (0)

      gm = gm.placeCard(0, AnswerCard("hihihi"))
      gm.roundAnswerCards shouldNot be (0)
    }
    "should return the active player" in {
      gm.activePlayer shouldBe 0
    }
    "should pick the next player" in {
      gm = gm.pickNextPlayer()
      gm.activePlayer shouldBe 0
    }
    "should give string-representation" in {
      gm.toString shouldNot be("")
    }
  }
}