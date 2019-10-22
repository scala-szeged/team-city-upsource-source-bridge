enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin) // https://stackoverflow.com/questions/40818082/how-to-use-node-modules-in-scala-js
// https://scalacenter.github.io/scalajs-bundler/

name := "team-city-upsource-source-bridge"

scalaVersion := "2.12.8"

scalaJSUseMainModuleInitializer := true

scalaJSModuleKind := ModuleKind.CommonJSModule

mainClass in Compile := Some("TeamCityUpsourceSouthBridge")

moduleName in fullOptJS := "team-city-upsource-source-bridge"

libraryDependencies += "io.scalajs" %%% "nodejs" % "0.4.2"


libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.10.0"

npmDependencies in Compile += "node-open" → "7.0.0"

webpackConfigFile := Some(baseDirectory.value / "team-city-upsource-source-bridge.webpack.config.js")
