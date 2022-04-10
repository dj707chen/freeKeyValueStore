package freeMonad

import freeMonad.KVStoreFree._

trait Program

object Program {
  def program: KVStoreFree[(Option[Int], Option[Int])] =
    /*
    put("wild-cats", 2).flatMap { _ =>
      update[Int]("wild-cats", _ + 12).flatMap { _ =>
        put("tame-cats", 5).flatMap { _ =>
          get[Int]("wild-cats").flatMap { oi: Option[Int] =>
            delete("tame-cats").map { _ =>
              oi
            }
          }
          // Here is an example why curley is preferred over parenthesis:
          // When ( is after flatMap instead of {, and parameter type is specified,
          // parameter definition must be enclosed in ().
          // Notice that it is not needed in line 12, b/c { is used after flatMap
          get[Int]("wild-cats").flatMap((oi: Option[Int]) =>
            delete("tame-cats").map { _ =>
              oi
            }
          )
        }
      }
    }
     */

    for {
      _   <- put("wild-cats", 2)
      _   <- update[Int]("wild-cats", _ + 12)
      _   <- put("tame-cats", 5)
      oi1 <- get[Int]("wild-cats")
      _   <- clear()
      oi2 <- get[Int]("wild-cats")
      _   <- delete("tame-cats")
    } yield (oi1, oi2)
}
