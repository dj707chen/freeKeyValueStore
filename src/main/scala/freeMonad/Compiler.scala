package freeMonad

// import cats.arrow.FunctionK
import cats.{Id, ~>}

import scala.collection.mutable

object Compiler {

  // The program will crash if a key is not found,
  // or if a type is incorrectly specified.
  // def impureCompiler: FunctionK[KVStoreOp, Id] = new FunctionK[KVStoreOp, Id] {
  def impureCompiler: KVStoreOp ~> Id = {

    // A very simple (and imprecise) key-value store
    val kvs = mutable.Map.empty[String, Any]

    λ[KVStoreOp ~> Id] {
      case Put(key, value) =>
        println(s"put($key, $value)")
        kvs(key) = value
        ()
      case Get(key) =>
        val value = kvs.get(key)
        println(s"get($key)=$value")
        value
      case Delete(key) =>
        println(s"delete($key)")
        kvs.remove(key)
        ()
      case Clear() =>
        println(s"clear")
        kvs.clear()
    }
  }
}
