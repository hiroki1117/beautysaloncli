import Dependencies._

name := "BeautySalonCLI"

lazy val commonSetting = Seq(
  scalaVersion := "2.13.1",
  version := "0.1.0-SNAPSHOT",
  organization := "com.example",
  organizationName := "example",
  libraryDependencies ++= Seq(
    scalaTest     % Test,
    "org.mockito" %% "mockito-scala" % "1.11.2" % Test
  )
)


lazy val root = (project in file("."))
  .withId("beauty-cli-root")
  .aggregate(app, presentation, domain)

lazy val app = (project in file("app"))
  .withId("beauty-cli-app")
  .settings(commonSetting)
  .dependsOn(domain, presentation)

lazy val domain = (project in file("domain"))
  .withId("beauty-cli-domain")
  .settings(commonSetting)

lazy val presentation = (project in file("presentation"))
  .withId("beauty-cli-presentation")
  .settings(commonSetting)
