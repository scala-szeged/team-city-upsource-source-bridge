enablePlugins(ScalaJSPlugin)

name := "Scala.js CLI Demo"

scalaVersion := "2.12.8"

scalaJSUseMainModuleInitializer := true

// scalaJSModuleKind := ModuleKind.CommonJSModule
mainClass in Compile := Some("HelloWorldApp")

moduleName in fullOptJS := "scalajs-cli-demo"

libraryDependencies += "io.scalajs" %%% "nodejs" % "0.4.2"
