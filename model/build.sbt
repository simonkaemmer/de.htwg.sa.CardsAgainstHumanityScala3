name := "model"
organization  := "de.htwg.se"

version := "2.0"

scalaVersion := "3.1.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.13" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.1.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7"

val akkaVersion = "2.6.19"
val akkaHttpVersion = "10.2.9"

libraryDependencies += ("com.typesafe.akka" %% "akka-actor-typed" % akkaVersion).cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.akka" %% "akka-stream" % akkaVersion).cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.akka" %% "akka-actor" % akkaVersion).cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.typesafe.akka" %% "akka-http" % akkaHttpVersion).cross(CrossVersion.for3Use2_13)