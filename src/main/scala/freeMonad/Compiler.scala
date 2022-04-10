package freeMonad

import cats.{Id, ~>}

import scala.collection.mutable

object Compiler {

  // The program will crash if a key is not found,
  // or if a type is incorrectly specified.
  def impureCompiler: KVStoreOp ~> Id =
    new (KVStoreOp ~> Id) {

      // A very simple (and imprecise) key-value store
      val kvs = mutable.Map.empty[String, Any]

      def apply[A](fa: KVStoreOp[A]): Id[A] =
        fa match {
          case Put(key, value) =>
            println(s"put($key, $value)")
            kvs(key) = value
            ()
          case Get(key)        =>
            println(s"get($key)")
            kvs.get(key).map(_.asInstanceOf[A])
          case Delete(key)     =>
            println(s"delete($key)")
            kvs.remove(key)
            ()
        }
    }
}
