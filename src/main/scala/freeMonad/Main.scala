package freeMonad

import freeMonad.FreeCompiler.compile

object Main {

  def main(args: Array[String]): Unit = {

    val result: (Option[Int], Option[Int]) =
      ProgramFree.generate
        .foldMap(compile)

    println(s"ProgramFree result=$result")
  }

}
