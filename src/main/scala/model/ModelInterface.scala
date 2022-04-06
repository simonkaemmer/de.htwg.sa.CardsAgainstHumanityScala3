package model

import model.BaseImpl.{AnswerCard, Card, GameManager, KompositumCard, Player, QuestionCard, RoundStrategy}


trait ModelInterface(val numberOfPlayers: Int,
                     val numberOfPlayableRounds: Int,
                     val numberOfRounds: Int,
                     val activePlayer: Int,
                     val kompositumCard: KompositumCard,
                     val player: Vector[Player],
                     val answerList: List[AnswerCard],
                     val questionList: List[QuestionCard],
                     val roundAnswerCards: Map[Player, String],
                     val roundQuestion: String) {

  def roundStrat(numberPlayer: Int): GameManager = RoundStrategy.execute(numberPlayer)

  def createCardDeck(): ModelInterface

  def handOutCards(): ModelInterface

  def givePlayerCards(listOfPlayerCards: List[AnswerCard]): Vector[Player]

  def playerHand(value: List[AnswerCard]): List[AnswerCard]

  def choosePlayerStartCards(playerCount:Int): List[AnswerCard]

  def placeQuestionCard(): ModelInterface

  def placeCard(activePlayer: Int, card: AnswerCard): ModelInterface

  def pickNextPlayer(): ModelInterface

  def drawCard(): ModelInterface

  def clearRoundAnswers(): ModelInterface

  def gameManagerG() : GameManager

  def toString: String

  //def getActivePlayer():Int
  //def getKompositum(): KompositumCard
  //def setKompositum(komp: KompositumCard) : ModelInterface

  def addPlayer(name: String): ModelInterface
}