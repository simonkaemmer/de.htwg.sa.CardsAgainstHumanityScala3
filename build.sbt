import sbt.Keys.libraryDependencies
name := "ScalaProject"

organization  := "de.htwg.se"

version := "2.0"

scalaVersion := "3.1.1"

lazy val commonDependencies = Seq(
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",
  libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.13" % "3.0.0",
  libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
  libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
  libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"

)

lazy val model = (project in file("model"))
  .settings(
    name := "CardsAgainstHumanity-Model",
    version := "2.0",
    libraryDependencies ++= commonDependencies,
  )



lazy val FileIO = (project in file("FileIO"))
  .dependsOn(model)
  .settings(
    name := "CardsAgainstHumanity-Persistence",
    version := "2.0",
    libraryDependencies ++= commonDependencies,
  )