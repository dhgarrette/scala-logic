name := "scala-logic"

organization := "dhg"

version := "0.0.2-SNAPSHOT"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "dhg releases repo" at "http://www.cs.utexas.edu/~dhg/maven-repository/releases",
  "dhg snapshot repo" at "http://www.cs.utexas.edu/~dhg/maven-repository/snapshots",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "OpenNLP repo" at "http://opennlp.sourceforge.net/maven2"
)

libraryDependencies ++= Seq(
  "dhg" % "scala-util_2.11" % "0.0.2-SNAPSHOT",
  "junit" % "junit" % "4.10" % "test",
  "com.novocode" % "junit-interface" % "0.8" % "test->default") //switch to ScalaTest at some point...

