

// Scala
val diffxV = "0.7.0"
val pprintV = "0.7.3"
val scalaV = "2.13.6"
val catsV = "2.6.1"
val catsEffectV = "3.2.9"
val catsTaglessV = "0.14.0"
val catsMtl = "1.2.1"
val kittens = "2.2.1"
val http4sV = "0.23.6"
val jwRedis = "1.4.1"
val fs2V = "2.5.4"
val circeV = "0.13.0"
val patchyV = "0.2.0"
val log4catsV = "2.1.1"
val mulesV = "0.5.0-M2"
val pureConfigV = "0.17.0"
val mulesHttp4sV = "0.3.0-M1"
val epimetheusV = "0.4.0"
val epimetheusHttp4sV = "0.6.0-M2"
val epimetheusMulesV = "0.5.0-M1"
val enumeratumCirceV = "1.6.1"
val specs2V = "4.13.0"
val catsEffectTestingV = "1.3.0"
val testContainersScalaV = "0.39.12"
val testContainersV = "0.2.0-M2"
val scalamockV = "5.1.0"
val scalacheckToolboxDatetime = "0.5.0"
// Java
val logbackClassicV = "1.3.0-alpha5"
val logstashEncoderV = "6.6"
val jaxbV = "2.3.1"
// Banno
val serverMetricsGeneralV = "0.5.1-RC1"
val simpleHealthV = "8.0.0-RC1"
val traceniumV = "0.8.3"
// DB Specific
val doobieV = "1.0.0-M1"
val flyWayV = "7.11.2"
val vault4sV = "9.0.0"

val kindProjectorV = "0.13.0"
val betterMonadicForV = "0.3.1"
val redis4CatsLog4Cats = "1.0.0"

lazy val core =
  project
    .in(file("."))
    .settings(commonSettings)
    .settings(
      name := "kvStore"
    )

lazy val commonSettings = Seq(
  organization := "kvStore",
  scalaVersion := scalaV,
  Compile / console / scalacOptions := Seq.empty,
  Test / console / scalacOptions := Seq.empty,
  scalacOptions -= "-Xfatal-warnings",
  scalacOptions += "-Ymacro-annotations",
  ThisBuild / publishArtifact := false,
  cancelable in Scope.Global := true,
  addCompilerPlugin("org.typelevel" % "kind-projector"     % kindProjectorV cross CrossVersion.full),
  addCompilerPlugin("com.olegpy"   %% "better-monadic-for" % betterMonadicForV),
  libraryDependencies ++= Seq(
    "com.softwaremill.diffx" %% "diffx-core"                 % diffxV,
    "com.lihaoyi"           %% "pprint"                      % pprintV,
    "org.typelevel"         %% "cats-tagless-macros"         % catsTaglessV,
    "org.typelevel"         %% "cats-core"                   % catsV,
    "org.typelevel"         %% "cats-effect"                 % catsEffectV,
    "org.typelevel"         %% "cats-mtl"                    % catsMtl,
    "com.beachape"          %% "enumeratum-circe"            % enumeratumCirceV,
    "org.typelevel"         %% "kittens"                     % kittens,
    "org.http4s"            %% "http4s-dsl"                  % http4sV,
    "javax.xml.bind"         % "jaxb-api"                    % jaxbV,
    "org.http4s"            %% "http4s-blaze-server"         % http4sV,
    "org.http4s"            %% "http4s-blaze-client"         % http4sV,
    "org.http4s"            %% "http4s-circe"                % http4sV,
    "io.circe"              %% "circe-generic"               % circeV,
    "io.circe"              %% "circe-generic-extras"        % circeV,
    "io.circe"              %% "circe-parser"                % circeV,
    "io.chrisdavenport"     %% "patchy"                      % patchyV,
    "org.typelevel"         %% "log4cats-slf4j"              % log4catsV,
    "io.chrisdavenport"     %% "epimetheus"                  % epimetheusV,
    "io.chrisdavenport"     %% "epimetheus-http4s"           % epimetheusHttp4sV,
    "io.chrisdavenport"     %% "epimetheus-mules"            % epimetheusMulesV,
    "io.chrisdavenport"     %% "mules"                       % mulesV,
    "io.chrisdavenport"     %% "mules-caffeine"              % mulesV,
    "io.chrisdavenport"     %% "mules-noop"                  % mulesV,
    "io.chrisdavenport"     %% "mules-http4s"                % mulesHttp4sV,
    "com.github.pureconfig" %% "pureconfig"                  % pureConfigV,
    "com.github.pureconfig" %% "pureconfig-http4s"           % pureConfigV,
    "ch.qos.logback"         % "logback-classic"             % logbackClassicV,
    "net.logstash.logback"   % "logstash-logback-encoder"    % logstashEncoderV,
    "org.tpolecat"          %% "doobie-core"                 % doobieV,
    "org.tpolecat"          %% "doobie-hikari"               % doobieV,
    "org.tpolecat"          %% "doobie-postgres"             % doobieV,
    "org.flywaydb"           % "flyway-core"                 % flyWayV,
    "org.tpolecat"          %% "natchez-noop"                % "0.1.4",
    "io.chrisdavenport"     %% "rediculous-concurrent"       % "0.1.0-M2",
    "com.47deg"             %% "scalacheck-toolbox-datetime" % scalacheckToolboxDatetime % Test,
    "org.scalamock"         %% "scalamock"                   % scalamockV                % Test,
    "org.tpolecat"          %% "doobie-specs2"               % doobieV                   % Test,
    "org.specs2"            %% "specs2-core"                 % specs2V                   % Test,
    "org.specs2"            %% "specs2-scalacheck"           % specs2V                   % Test,
    "org.typelevel"         %% "cats-effect-testing-specs2"  % catsEffectTestingV        % Test,
    "com.dimafeng"          %% "testcontainers-scala"        % testContainersScalaV      % Test,
    "io.chrisdavenport"     %% "testcontainers-specs2"       % testContainersV           % Test,
    "org.typelevel"         %% "log4cats-noop"               % log4catsV                 % Test,
    "dev.profunktor"        %% "redis4cats-log4cats"         % redis4CatsLog4Cats
  )
)
