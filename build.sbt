import Dependencies._

val libs = Seq(
    scalaTest % Test,
    "org.mockito" %% "mockito-scala" % "1.11.2" % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "BeautySalonCLI",
    scalaVersion := "2.13.1",
    version := "0.1.0-SNAPSHOT",
    organization := "com.example",
    organizationName := "example",
    libraryDependencies ++= libs
  )