import com.typesafe.startscript.StartScriptPlugin

name := "scala-logic"

version := "0.0.1"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "log4j" % "log4j" % "1.2.16",
  "junit" % "junit" % "4.10" % "test",
  "com.novocode" % "junit-interface" % "0.8" % "test->default") //switch to ScalaTest at some point...

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

