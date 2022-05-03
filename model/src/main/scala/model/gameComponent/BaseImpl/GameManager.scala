package model.gameComponent.BaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import model.gameComponent.ModelInterface
import play.api.libs.json.{JsValue, Json, JsNumber, JsArray, JsString, JsBoolean}
import com.fasterxml.jackson.annotation.JsonValue

import scala.language.postfixOps
import scala.util.Random

case class GameManager @Inject() (@Named("Def") override val numberOfPlayers: Int = 0,
                                  override val numberOfPlayableRounds: Int = 10,
                                  override val numberOfRounds: Int = 0,
                                  override val activePlayer: Int = 0,
                                  override val kompositumCard: KompositumCard = KompositumCard(List[Card]()),
                                  override val player: Vector[Player] = Vector(),
                                  override val answerList: List[AnswerCard] = List(),
                                  override val questionList: List[QuestionCard] = List(),
                                  override val roundAnswerCards: Map[Player, String] = Map[Player, String](),
                                  override val roundQuestion: String = "") extends ModelInterface(numberOfPlayers, numberOfPlayableRounds, numberOfRounds, activePlayer, kompositumCard, player, answerList, questionList, roundAnswerCards, roundQuestion):

  override def roundStrat(numberPlayer: Int): GameManager = RoundStrategy.execute(numberPlayer)

  def addPlayer(name: String): GameManager = {
    val playerTmp = player :+ Player(name, true, List[AnswerCard]())
    copy(player = playerTmp)
  }

  def createCardDeck(): GameManager = {
    var tmpAnswerList = answerList
    var tmpQuestionList = questionList
    for (x <- kompositumCard.cardList) {
      x match {
        case _: QuestionCard => tmpQuestionList = tmpQuestionList :+ (x.asInstanceOf[QuestionCard])
        case _: AnswerCard => tmpAnswerList = tmpAnswerList :+ (x.asInstanceOf[AnswerCard])
      }
    }
    copy(answerList = tmpAnswerList, questionList = tmpQuestionList)
  }

  def handOutCards(): GameManager = {
    val playerCard = choosePlayerStartCards(numberOfPlayers)
    var remainingCards = answerList
    playerCard foreach (remove => remainingCards = answerList.filterNot(_ == remove))
    val tmpPlayerVecList = givePlayerCards(playerCard)

    copy(player = tmpPlayerVecList, answerList = remainingCards)
  }

  def givePlayerCards(listOfPlayerCards: List[AnswerCard]): Vector[Player] = {

    var tmpPlayerCards = listOfPlayerCards
    var tmpPlayer = Vector[Player]()
    for (x <- player; if tmpPlayerCards.nonEmpty) {
      val playersHand = playerHand(tmpPlayerCards)
      for (removeCards <- playersHand.indices) yield tmpPlayerCards = tmpPlayerCards.filterNot(_ == playersHand(removeCards))
      tmpPlayer = tmpPlayer :+ Player(x.name, x.isAnswering, playersHand)
    }
    tmpPlayer
  }

  def playerHand(value: List[AnswerCard]): List[AnswerCard] = {
    var cardcount = 0
    var remCard = List[AnswerCard]()
    for (x <- value; if cardcount < 7) yield {
      remCard = remCard :+ x
      cardcount += 1
    }
    remCard
  }

  def choosePlayerStartCards(playerCount: Int): List[AnswerCard] = {
    val tmpAnswerList = Random.shuffle(answerList)
    var givenCards = List[AnswerCard]()

    var count = 0

    for (answer <- tmpAnswerList if tmpAnswerList.nonEmpty; if count < 7 * playerCount) {
      givenCards = givenCards :+ answer
      count += 1
    }
    givenCards
  }

  def placeQuestionCard(): GameManager = {
    val removedQuestList = Random.shuffle(questionList)
    val quest = removedQuestList.head
    copy(questionList = removedQuestList.filterNot(_== quest), roundQuestion = quest.question, numberOfRounds = incr(numberOfRounds))
  }

  def placeCard(activePlayer: Int, card: AnswerCard): GameManager = {

    var tmpPlacedCardMap = Map[Player, String]()
    if (!roundAnswerCards.isEmpty)
      tmpPlacedCardMap = roundAnswerCards

    var newPlayerHand = player(activePlayer).playerCards

    val questTmp = roundQuestion
    tmpPlacedCardMap += (player(activePlayer) -> questTmp.replace("_", card.antwort))
    newPlayerHand = newPlayerHand.filterNot(_ == card)

    var tmpPlayerVecList = player
    tmpPlayerVecList = tmpPlayerVecList.updated(activePlayer, Player(player(activePlayer).name, true, newPlayerHand))

    copy(player = tmpPlayerVecList, roundAnswerCards = tmpPlacedCardMap)
  }

  def pickNextPlayer(): GameManager = {
    val tmpActivePlayer = (activePlayer + 1) % player.length
    copy(activePlayer = tmpActivePlayer)
  }

  def drawCard(): GameManager = {
    var answerTmp = answerList
    answerTmp = Random.shuffle(answerTmp)
    var tmpPlayerVec = Vector[Player]()

    for (x <- player) {
      val r = Random.nextInt(answerTmp.length)
      val result = answerTmp(r)
      answerTmp = answerTmp.filterNot(_ == result)
      var playerHand = x.playerCards
      playerHand = playerHand :+ result
      tmpPlayerVec = tmpPlayerVec :+ Player(x.name, x.isAnswering, playerHand)
    }

    copy(answerList = answerTmp, player = tmpPlayerVec)
  }

  def clearRoundAnswers(): GameManager = {
    copy(roundAnswerCards = Map[Player, String]())
  }

  def gameManagerG(): GameManager = copy()

  override def toString: String = {
    val sb = new StringBuilder
    sb ++= "Aktive Antwort Karten: " + answerList.toString() + "\n"
    sb ++= "Aktive Frage Karten: " + questionList.toString() + "\n"
    sb ++= "Aktuelle Frage Karte: " + roundQuestion + "\n"
    sb ++= "Gelegten Antwort Karten: " + roundAnswerCards.toString() + "\n"

    player.indices foreach( i => sb ++= "Die Karten des Spielers: " + player(i).name + " Seine Karten:" + player(i).playerCards + "\n")
    sb.toString()
  }


  def incr(x: Int): Int = x + 1

  def gameToJson(): String =

    Json.obj("game" -> Json.obj(
      "numberOfPlayers" -> JsNumber(numberOfPlayers),
      "numberOfPlayableRounds" -> JsNumber(numberOfPlayableRounds),
      "numberOfRounds" -> JsNumber(numberOfRounds),
      "activePlayer" -> JsNumber(activePlayer),
      "kompositumCard" -> JsArray(for card <- kompositumCard.cardList yield JsString(card.toString)),
      "player" -> JsArray(for dude <- player yield Json.obj(
        "name" -> dude.name,
        "state" -> JsBoolean(dude.isAnswering),
        "playerCards" -> JsArray(for card <- dude.playerCards yield JsString(card.toString))
      )),
      "answerList" -> JsArray(for card <- answerList yield JsString(card.toString)),
      "questionList" -> JsArray(for card <- questionList yield JsString(card.toString)),
      "roundAnswerCards" -> JsArray(for card <- roundAnswerCards.toArray yield JsString(card.toString())),
      "roundQuestion" -> JsString(roundQuestion)
    )).toString()


  def gameFromJson(input: String): ModelInterface = {
    val json: JsValue = Json.parse(input)

    val playersNum = (json \ "game" \ "numberOfPlayers").get.toString.toInt
    val playableRoundsNum = (json \ "game" \ "numberOfPlayableRounds").get.toString.toInt
    val roundsNum = (json \ "game" \ "numberOfRounds").get.toString.toInt
    val playerActive = (json \ "game" \ "activePlayer").get.toString.toInt

    val tempCards: List[String] = (json \ "game" \ "kompositumCard").as[List[String]]
    val kompositumCard: KompositumCard = kompositumCard.addNewCards((json \ "game" \ "kompusitumCard").as[List[String]])


    // FIXME: Player Json representation not correct:
    // - name
    // - state
    // - cardList
    // TODO: Implement Json conversion in both directions

  }

  // Tooling

  def playerFromJson(input: String, playerCount: Int): Vector[Player] =

    val json: JsValue = Json.parse(input)
    val player: List[Player] = (json \ "game" \ "player").get.

    playerCount match {
      case 2 =>

        return Vector[Player]()
      case 3 =>
      case 4 =>
    }

object GameManager{

  case class Builder(){
    var numberOfPlayer: Int = 0
    var numberOfPlayableRounds: Int = 0

    def withNumberOfPlayer(players: Int): Builder = {
      numberOfPlayer = players
      this
    }

    def withNumberOfRounds(rounds: Int): Builder = {
      numberOfPlayableRounds = rounds
      this
    }

    def build(): GameManager = {
     GameManager(numberOfPlayer)
    }
  }
}