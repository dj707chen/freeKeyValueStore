package kvStore

/** Define KVStoreFree type alias and a smart constructor of KVStoreFree for each operation.
  *  Refer https://github.com/tpolecat/doobie/blob/main/modules/free/src/main/scala/doobie/free/resultset.scala#L955
  */
object KVStoreFree {
  import cats.free.Free
  import cats.free.Free.liftF

  type KVStoreFree[A] = Free[KVStoreOp, A]

  def put[T](key: String, value: T): KVStoreFree[Unit] =
    liftF[KVStoreOp, Unit](
      Put[T](key, value)
    )

  def get[T](key: String): KVStoreFree[Option[T]] =
    liftF[KVStoreOp, Option[T]](
      Get[T](key)
    )

  def delete(key: String): KVStoreFree[Unit] =
    liftF(Delete(key)) // Delete does not have type parameter so liftF neither

  def update[T](key: String, f: T => T): KVStoreFree[Unit] =
    for {
      vMaybe <- get[T](key)
      _      <- vMaybe
                  .map(v => put[T](key, f(v)))
                  .getOrElse(
                    Free.pure(())
                  )
    } yield ()

  def clear(): KVStoreFree[Unit] = liftF(Clear())
}
