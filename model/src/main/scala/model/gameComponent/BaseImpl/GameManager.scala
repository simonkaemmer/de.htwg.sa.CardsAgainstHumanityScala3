package model.gameComponent.BaseImpl

import model.gameComponent.BaseImpl.Card
import com.google.inject.Inject
import com.google.inject.name.Named
import model.gameComponent.{BaseImpl, ModelInterface}
import play.api.libs.json.{JsArray, JsBoolean, JsNumber, JsString, JsValue, Json}
import com.fasterxml.jackson.annotation.JsonValue

import scala.collection.IterableOnce
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
                                  override val roundAnswerCards: Map[Player, String] = Map(),
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
    println("ActivePlayer: " + tmpActivePlayer + " PlayerLen: " + player.length + " Real active player: " + activePlayer)
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

  override def gameToJson(): String =

    Json.obj("game" -> Json.obj(
      "numberOfPlayers" -> JsNumber(numberOfPlayers),
      "numberOfPlayableRounds" -> JsNumber(numberOfPlayableRounds),
      "numberOfRounds" -> JsNumber(numberOfRounds),
      "activePlayer" -> JsNumber(activePlayer),
      "kompositumCard" -> JsArray(for card <- kompositumCard.cardList yield {
        if(card.isInstanceOf[QuestionCard]) {
          Json.obj(
            "text" -> JsString(card.toString),
            "type" -> JsString("Q")
          )
        } else {
          Json.obj(
            "text" -> JsString(card.toString),
            "type" -> JsString("A")
          )
        }}),
      "player" -> JsArray(for dude <- player yield Json.obj(
        "name" -> dude.name,
        "state" -> JsBoolean(dude.isAnswering),
        "playerCards" -> JsArray(for card <- dude.playerCards yield JsString(card.toString))
      )),
      "answerList" -> JsArray(for card <- answerList yield JsString(card.toString)),
      "questionList" -> JsArray(for card <- questionList yield JsString(card.toString)),
      "roundAnswerCards" ->  JsArray(for mapping <- roundAnswerCards.toList yield Json.obj(
        "name" -> mapping._1.name.toString,
        "placedCard" -> mapping._2.toString)),
      "roundQuestion" -> JsString(roundQuestion)
    )).toString()

  override def gameFromJson(input: String): ModelInterface = {
    val json: JsValue = Json.parse(input)

    val playersNum = (json \ "game" \ "numberOfPlayers").get.toString.toInt
    val playableRoundsNum = (json \ "game" \ "numberOfPlayableRounds").get.toString.toInt
    val roundsNum = (json \ "game" \ "numberOfRounds").get.toString.toInt
    val playerActive = (json \ "game" \ "activePlayer").get.toString.toInt

    val kompCardList: List[Card] = (json \ "game" \\ "kompositumCard").map(f =>
      val count = (f \\ "type").toList.length

      for a <- 0 to (count-1) yield {
        if((f(a) \ "type").as[String] == "Q") {
          QuestionCard((f(a) \ "text").as[String])
        } else {
          AnswerCard((f(a) \ "text").as[String])
        }
      }
    ).head.toList
    val kompositumCard = KompositumCard(kompCardList)

    val player: Vector[Player] = playerFromJson(gameToJson(), playersNum)
    val answerList: List[AnswerCard] = (json \ "game" \\ "answerList").map(s => AnswerCard(s.toString)).toList
    val questionList: List[QuestionCard] = (json \ "game" \\ "questionList").map(s => QuestionCard(s.toString)).toList

    val roundAnswerCardsCollection = (json \ "game" \\ "roundAnswerCards").map(s =>
      val playerNames = (s \\ "name").toList
      val players = for p <- playerNames yield player.find(_.name == p.toString) match {
        case None => Player("Dummy", false, List[AnswerCard](AnswerCard("Dummy"))) // playerLists filtered later to throw out Dummy players (staying in typesystem)
        case Some(player) => player
      }

      for p <- 0 to players.length-1 yield (players(p) -> (s(p) \ "placedCard").as[String])
    ).head.toMap

    val roundQuestion: String = (json \ "game" \ "roundQuestion").get.toString

    copy(numberOfPlayers, numberOfPlayableRounds, numberOfRounds, activePlayer, kompositumCard, player, answerList, questionList, roundAnswerCards, roundQuestion) // TODO: Change to copy constructor

  }

  // Tooling

  def playerFromJson(input: String, playerCount: Int): Vector[Player] =

    val json: JsValue = Json.parse(input)
    val playerNames = (json \\ "name").map(_.toString)
    val playerStates = (json \\ "state")
    val playerCards = (json \\ "playerCards").map(_.as[List[String]])

    playerCount match {
      case 2 =>

        val playerOneCards = playerCards(0).map(s => AnswerCard(s.toString)).toList
        val playerTwoCards = playerCards(1).map(s => AnswerCard(s.toString)).toList

        return Vector[Player](
          Player(playerNames.head, playerStates.head.as[Boolean], playerOneCards),
          Player(playerNames.last, playerStates.last.as[Boolean], playerTwoCards)
        )
      case 3 =>

        val playerOneCards = playerCards(0).map(s => AnswerCard(s.toString)).toList
        val playerTwoCards = playerCards(1).map(s => AnswerCard(s.toString)).toList
        val playerThreeCards = playerCards(2).map(s => AnswerCard(s.toString)).toList

        return Vector[Player](
          Player(playerNames.head, playerStates.head.as[Boolean], playerOneCards),
          Player(playerNames(1), playerStates(1).as[Boolean], playerTwoCards),
          Player(playerNames.last, playerStates.last.as[Boolean], playerThreeCards)
        )

      case 4 =>

        val playerOneCards = playerCards(0).map(s => AnswerCard(s.toString)).toList
        val playerTwoCards = playerCards(1).map(s => AnswerCard(s.toString)).toList
        val playerThreeCards = playerCards(2).map(s => AnswerCard(s.toString)).toList
        val playerFourCards = playerCards(3).map(s => AnswerCard(s.toString)).toList

        return Vector[Player](
          Player(playerNames.head, playerStates.head.as[Boolean], playerOneCards),
          Player(playerNames(1), playerStates(1).as[Boolean], playerTwoCards),
          Player(playerNames(2), playerStates(2).as[Boolean], playerThreeCards),
          Player(playerNames.last, playerStates.last.as[Boolean], playerFourCards)
        )
    }

  def dummyData(): GameManager = {
    val numberOfPlayersD: Int = 2
    val numberOfPlayableRoundsD: Int = 10
    val numberOfRoundsD: Int = 3
    val activePlayerD: Int = 1
    val kompositumCardD: KompositumCard = KompositumCard(List[Card](
      AnswerCard("Test1"),
      QuestionCard("Test2"),
      AnswerCard("TEst4")
    ))
    val playerD: Vector[Player] = Vector(
      Player("Hugo", true, List[AnswerCard](
        AnswerCard("Drei"),
        AnswerCard("Vier")
      )),
      Player("Egon", false, List[AnswerCard](
        AnswerCard("Fünf"),
        AnswerCard("Sechs")
      ))
    )
    val answerListD: List[AnswerCard] = List(
      AnswerCard("Hihihihi"),
      AnswerCard("huhuhuhuh")
    )
    val questionListD: List[QuestionCard] = List(
      QuestionCard("Wat?!"),
      QuestionCard("Warruuuum?")
    )
    val roundAnswerCardsD: Map[Player, String] = Map[Player, String](
      Player("Hugo", true, List[AnswerCard](AnswerCard("Sieben"))) ->  "DIngs",
      Player("Egon", false, List[AnswerCard](AnswerCard("Acht"))) -> "Bums",
      Player("Hugo", true, List[AnswerCard](AnswerCard("neun"))) -> "düdüd")
    val roundQuestionD: String = "Was geht?"

    copy(numberOfPlayersD, numberOfPlayableRoundsD, numberOfRoundsD, activePlayerD, kompositumCardD, playerD, answerListD, questionListD, roundAnswerCardsD, roundQuestionD)
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