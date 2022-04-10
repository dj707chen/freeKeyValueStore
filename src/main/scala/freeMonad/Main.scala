package freeMonad

import cats.effect.{ExitCode, IO, IOApp}
import freeMonad.Compiler.impureCompiler
import freeMonad.Program.program

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val result = program.foldMap(impureCompiler)
    println(s"result=$result")
    IO.unit.as(ExitCode.Success)
  }
}
