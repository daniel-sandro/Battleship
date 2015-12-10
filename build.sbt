organization := "de.htwg"

name := "Battleship"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "log4j" % "log4j" % "1.2.17",
  "com.google.inject" % "guice" % "3.0",
  "com.fasterxml" % "jackson-xml-databind" % "0.6.2",
  "junit" % "junit" % "4.8.2" % "test"
)
