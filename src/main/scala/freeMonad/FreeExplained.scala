package freeMonad

/**
 * Typed in from https://www.youtube.com/watch?v=cxMo1RMsD0M&t=146s
 * by Daniel Spiewak
 */

import cats.{Monad, ~>}

// @formatter:off
sealed trait Free[F[_], A] {
  import Free._

  final def flatMap[B](
                        f: A => Free[F, B]
                      ): Free[F, B] = FlatMap(this, f)

  final def foldMap[G[_]: Monad, E](nt: F ~> G): G[A] = this match { // I added E
    case Pure(a)   => Monad[G].pure(a): G[A]
    case LiftM(fa) => nt(fa)
    case FlatMap(
                  inner: Free[F, E],
                  f: (E => Free[F, A])
                ) =>
      Monad[G].flatMap(
        inner.foldMap(nt)
      )(
        e => f(e).foldMap(nt)
      )

  }
}

object Free {
  def pure[F[_], A](a: A): Free[F, A] = Pure(a)

  final case class Pure[F[_], A](a: A)      extends Free[F, A]
  final case class LiftM[F[_], A](fa: F[A]) extends Free[F, A]

  final case class FlatMap[F[_], E, A](
                                        self:   Free[F, E],
                                        f: E => Free[F, A]
                                      ) extends Free[F, A]
}
