import sbt._
import Keys._

object ScalaLogicBuild extends Build {

  lazy val main = Project("scala-logic", file(".")) dependsOn(scalautil)

  lazy val scalautil = Project("scala-util", file("scala-util"))

}

