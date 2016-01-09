name := "MyExpenses"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.nrinaudo" %% "tabulate" % "0.1.6"
libraryDependencies += "com.nrinaudo" %% "tabulate-generic" % "0.1.6"
libraryDependencies += "codes.reactive" %% "scala-time" % "0.3.0-SNAPSHOT"