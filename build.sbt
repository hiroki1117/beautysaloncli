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
  .aggregate(app, presentation, domain)

lazy val app = (project in file("app"))
  .settings(commonSetting)
  .dependsOn(domain, presentation)

lazy val domain = (project in file("domain"))
  .settings(commonSetting)
  .dependsOn(presentation)

lazy val presentation = (project in file("presentation"))
  .settings(commonSetting)
