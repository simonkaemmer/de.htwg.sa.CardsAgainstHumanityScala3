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
  dependencies.typesafeplay,
  dependencies.slf4jNop,
  dependencies.mysql,
  dependencies.githubSlick
)

lazy val Persistence = (project in file("Persistence"))
  .settings(
    name := "CardsAgainstHumanity-Persistence",
    version := projectVersion,
    libraryDependencies ++= commonDependencies,
    libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7"
  )


lazy val root = project
  .in(file("."))
  .aggregate(Persistence)
  .dependsOn(Persistence)
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

val akkaVersion = "2.6.19"
val akkaHttpVersion = "10.2.9"

libraryDependencies += ("com.typesafe.akka" %% "akka-actor-typed" % akkaVersion).cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.akka" %% "akka-stream" % akkaVersion).cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.akka" %% "akka-actor" % akkaVersion).cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.akka" %% "akka-http" % akkaHttpVersion).cross(CrossVersion.for3Use2_13)

libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7"

libraryDependencies += ("com.typesafe.slick" %% "slick" % "3.3.3").cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.slick" %% "slick-hikaricp" % "3.3.3").cross(CrossVersion.for3Use2_13)
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.29"
libraryDependencies += "com.github.slick.slick" % "slick_3" % "nafg~dottyquery-SNAPSHOT"
