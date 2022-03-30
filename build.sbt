name := "de.htwg.se.CardsAgainstHumanity"
version := "0.1.1"
scalaVersion := "3.1.1"
crossScalaVersions ++= Seq("2.13.2", "3.1.1")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13)

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"

scalacOptions ++= {
  Seq(
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:implicitConversions"
    // disabled during the migration
    // "-Xfatal-warnings"
  ) ++
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq(
        "-unchecked",
        "-source:3.0-migration"
      )
      case _ => Seq(
        "-deprecation",
        "-Xfatal-warnings",
        "-Wunused:imports,privates,locals",
        "-Wvalue-discard"
      )
    })
}