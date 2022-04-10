package freeMonad

object KVStoreFree {
  import cats.free.Free
  import cats.free.Free.liftF

  type KVStoreFree[A] = Free[KVStoreOp, A]

  // Put returns nothing (i.e. Unit).
  def put[T](key: String, value: T): KVStoreFree[Unit] =
    liftF[KVStoreOp, Unit](Put[T](key, value))

  // Get returns a T value.
  def get[T](key: String): KVStoreFree[Option[T]] =
    liftF[KVStoreOp, Option[T]](Get[T](key))

  // Delete returns nothing (i.e. Unit).
  def delete(key: String): KVStoreFree[Unit] =
    liftF(Delete(key))

  // Update composes get and set, and returns nothing.
  def update[T](key: String, f: T => T): KVStoreFree[Unit] =
    for {
      vMaybe <- get[T](key)
      _ <- vMaybe.map(v => put[T](key, f(v))).getOrElse(Free.pure(()))
    } yield ()
}