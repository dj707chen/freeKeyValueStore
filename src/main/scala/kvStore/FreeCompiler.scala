package kvStore

import cats.{Id, ~>}

import scala.collection.mutable

object FreeCompiler {

  // The program will crash if a key is not found,
  // or if a type is incorrectly specified.

  // Same as
  // def compile: FunctionK[KVStoreOp, Id] = new FunctionK[KVStoreOp, Id] {
  //   def apply[A](fa: KVStoreOp[A]): Id[A] = ...

  // Since this function will be parameter of Free.foldMap, for the destination type,
  // there must be Monad instance, refer
  //   final def foldMap[M[_]](f: FunctionK[S, M])(implicit M: Monad[M]): M[A] =
  //   https://github.com/typelevel/cats/blob/main/free/src/main/scala/cats/free/Free.scala#L163:

  def compile: KVStoreOp ~> Id = {

    // A very simple (and imprecise) key-value store
    val kvs = mutable.Map.empty[String, Any]

    λ[KVStoreOp ~> Id] {
      case Put(key, value) =>
        kvs(key) = value
        println(s"put($key, $value)")
      case Get(key) =>
        val value = kvs.get(key)
        println(s"get($key)=$value")
        value
      case Delete(key) =>
        kvs.remove(key)
        println(s"delete($key)")
      case Clear() =>
        kvs.clear()
        println(s"clear")
    }
  }
}
