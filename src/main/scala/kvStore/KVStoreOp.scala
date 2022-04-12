package kvStore

/**
 * Computations
 *
 * @tparam A The type of the value that the computation yields.
 */
sealed trait KVStoreOp[A]

case class Put[T](key: String, value: T) extends KVStoreOp[Unit]
case class Get[T](key: String) extends KVStoreOp[Option[T]]
case class Delete(key: String) extends KVStoreOp[Unit]
case class Clear() extends KVStoreOp[Unit]
