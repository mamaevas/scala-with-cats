//import cats.Show
//import cats.instances.int._
//import cats.instances.string._

import java.util.Date

import cats._
import cats.implicits._

val showInt = Show.apply[Int]
val showString = Show.apply[String]

val intAsString: String = showInt.show(123)
val stringAsString: String = showString.show("abc")

import cats.syntax.show._

val showInt_2 = 123.show
val showString_2 = "abc".show

/*
implicit val dateShow:Show[Date] = new Show[Date] {
  override def show(date: Date) = s"${date.getTime}ms since the epoch."
}
*/

implicit val dateShow_2: Show[Date] =
  Show.show(date => s"${date.getTime}ms since the epoch.")

val dateToString = new Date().show


final case class Cat(name: String, age: Int, color: String)

implicit val catShow: Show[Cat] =
  Show.show(cat => s"PrintableCat($cat)")

val cat = Cat("name", 34, "black")
cat.show

