package freeMonad

sealed trait KVStoreOp[A]
case class Put[T](key: String, value: T) extends KVStoreOp[Unit]
case class Get[T](key: String) extends KVStoreOp[Option[T]]
case class Delete(key: String) extends KVStoreOp[Unit]
