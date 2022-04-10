resolvers ++= Seq(
  "Oncue Bintray Repo" at "https://dl.bintray.com/oncue/releases",
  "Banno Releases" at "https://artifactory.banno-tools.com/artifactory/libs-release"
)

addSbtPlugin("org.scalameta"             % "sbt-scalafmt"        % "2.4.3")
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat"        % "0.1.20")
addSbtPlugin("org.lyranthe.sbt"          % "partial-unification" % "1.1.2")
addSbtPlugin("com.banno"                 % "banno-sbt-plugin"    % "11.1.0")
