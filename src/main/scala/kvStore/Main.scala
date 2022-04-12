package kvStore

import kvStore.FreeCompiler.compile

object Main {

  def main(args: Array[String]): Unit = {

    val result: (Option[Int], Option[Int]) =
      ProgramFree.compose
        .foldMap(compile)

    println(s"ProgramFree result=$result")
  }

}
