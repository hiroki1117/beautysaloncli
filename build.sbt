import Dependencies._

lazy val root = (project in file("."))
  .settings(
    name := "BeautySalonCLI",
    scalaVersion := "2.13.1",
    version := "0.1.0-SNAPSHOT",
    organization := "com.example",
    organizationName := "example",
    libraryDependencies += scalaTest % Test
  )