name := """store"""

version := "1.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

sbt.Keys.fork in Test := false

resolvers += "ruimo.com" at "http://static.ruimo.com/release"

libraryDependencies ++= Seq(
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "com.typesafe.play" %% "play-mailer" % "2.4.0",
  "org.mockito" % "mockito-all" % "1.9.5" % "test",
  "com.ruimo" %% "recoengplugin4play23" % "1.0-SNAPSHOT",
  "com.ruimo" %% "csvparser" % "1.0-SNAPSHOT",
  "com.ruimo" %% "scoins" % "1.0-SNAPSHOT",
  jdbc,
  anorm,
  cache,
  ws,
  filters
)

val buildSettings = Defaults.defaultSettings ++ Seq(
   javaOptions += "-Xmx1G"
)
