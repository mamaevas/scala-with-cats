trait D[A] // F[A] and F[B] never subtypes of one another
trait F[+A] // F[B] subtype of F[A] if B is subtype of A
trait G[-A] // F[B] subtype of F[A] if A is subtype of B
