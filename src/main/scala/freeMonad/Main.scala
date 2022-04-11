package freeMonad

import cats.effect.{ExitCode, IO, IOApp}
import freeMonad.FreeCompiler.compile
import freeMonad.ProgramFree.programFree

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {

    val result: (Option[Int], Option[Int]) =
      programFree.foldMap(
        compile
      )

    println(s"result=$result")

    IO.unit.as(ExitCode.Success)
  }

}
