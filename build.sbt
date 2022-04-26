import sbt.Keys.libraryDependencies
name := "ScalaProject"

organization  := "de.htwg.se"


val scala3Version = "3.1.1"
val projectVersion = "3.1"

lazy val commonDependencies = Seq(
  dependencies.scalactic,
  dependencies.scalatest,
  dependencies.googleinject,
  dependencies.scalalangmodulesXml,
  dependencies.scalalangmodulesSwing,
  dependencies.typesafeplay
)

lazy val model = (project in file("model"))
  .settings(
    name := "CardsAgainstHumanity-Model",
    version := projectVersion,
    libraryDependencies ++= commonDependencies,
  )

lazy val FileIO = (project in file("FileIO"))
  .dependsOn(model)
  .settings(
    name := "CardsAgainstHumanity-Persistence",
    version := projectVersion,
    libraryDependencies ++= commonDependencies,
  )


lazy val root = project
  .in(file("."))
  .aggregate(FileIO)
  .dependsOn(FileIO, model)
  .settings(
    name := "CardsAgainstHumanity",
    version := projectVersion,
    commonSettings,
    libraryDependencies ++= commonDependencies,
  )

lazy val commonSettings = Seq(
  scalaVersion := scala3Version,
  organization := "de.htwg.sa",
)

val AkkaVersion = "2.6.18"
val AkkaHttpVersion = "10.2.9"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
)

libraryDependencies += ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13)

