enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin) // https://stackoverflow.com/questions/40818082/how-to-use-node-modules-in-scala-js
// https://scalacenter.github.io/scalajs-bundler/

name := "Scala.js CLI Demo"

scalaVersion := "2.12.8"

scalaJSUseMainModuleInitializer := true

scalaJSModuleKind := ModuleKind.CommonJSModule

mainClass in Compile := Some("HelloWorldApp")

moduleName in fullOptJS := "scalajs-cli-demo"

libraryDependencies += "io.scalajs" %%% "nodejs" % "0.4.2"


libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.10.0"

npmDependencies in Compile += "node-fetch" → "1.6.3"

webpackConfigFile := Some(baseDirectory.value / "tessel.webpack.config.js")