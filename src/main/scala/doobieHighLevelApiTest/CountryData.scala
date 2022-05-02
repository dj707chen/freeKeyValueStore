package doobieHighLevelApiTest

import cats.effect.{ExitCode, IO, IOApp, Resource}
import doobie._
import doobie.hikari.HikariTransactor
import doobie.implicits._
import doobie.util.yolo.Yolo
//import doobie.hikari._
//import doobie.postgres.implicits._

case class Country(name: String, code: String, pop: BigDecimal)

object CountryData extends IOApp {

  def transactor(
      dbServer: String,
      user: String,
      pass: String
  ): Resource[IO, Transactor[IO]] =
    for {
      ec <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
      xa <- HikariTransactor.newHikariTransactor[IO](
              "org.postgresql.Driver", // driver classname
              s"jdbc:postgresql://$dbServer/test_db",
              user,
              pass,
              ec                       // await connection here
            )
    } yield xa

  def biggerThan(pop: Int): doobie.Query0[Country] =
    sql"""
         |select code, name, gnp from country
         |where population > $pop
         |""".stripMargin.query[Country]

  // @formatter:on
  def largeCountries(xa: Transactor[IO]): IO[List[Country]] = {
    val q0: doobie.Query0[Country]             = biggerThan(1000000)
    val yolo                                   = new Yolo(xa); import yolo._
    q0.check
    val rs: doobie.ConnectionIO[List[Country]] = q0.to[List]
    val io: IO[List[Country]]                  = rs.transact(xa)
    io
  }

  def run(args: List[String]): IO[ExitCode] = {
    val dBServer   = "localhost:5432"
    val dBUser     = "tester"
    val dBPassword = "password"

    val xaResource: Resource[IO, doobie.Transactor[IO]] = transactor(dBServer, dBUser, dBPassword)

    xaResource.use { xa =>
      for {
        cnList <- largeCountries(xa)
        _      <- IO.println("-" * 100)
        _      <- IO.pure(pprint.log(cnList))
        _      <- IO.println("-" * 100)
      } yield ()
    } >> IO.unit.as(ExitCode.Success)
  }
}
