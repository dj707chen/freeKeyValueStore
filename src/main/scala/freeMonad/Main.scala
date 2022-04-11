package freeMonad

import cats.effect.{ExitCode, IO, IOApp}
import freeMonad.FreeCompiler.compile
import freeMonad.ProgramFree.programFree

object Main {

  def main(args: Array[String]): Unit = {

    val result: (Option[Int], Option[Int]) =
      programFree.foldMap(
        compile
      )

    println(s"result=$result")
  }

}
