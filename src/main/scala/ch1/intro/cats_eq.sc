import java.util.Date

import cats.Eq
import cats.instances.int._

List(1, 2, 3).map(Option(_)).filter(item => item == 1) // List[Option[Int]] = List()

val eqInt = Eq[Int]
eqInt.eqv(123, 123) // true

import cats.syntax.eq._

123 == 123 // true
123 =!= 234 // true
123 == "123" // false
//123 === "123" // Compile error

import cats.instances.option._
import cats.syntax.option._

Some(1) == None // false
Some(1) == 1 // false
(Some(1): Option[Int]) == (None: Option[Int]) // false
Option(1) === Option.empty[Int]
1.some == none[Int]
1.some === none[Int]

import cats.instances.long._

implicit val dateEq: Eq[Date] =
  Eq.instance[Date] { (date1, date2) =>
    date1.getTime === date2.getTime
  }
val x = new Date()
val y = new Date()
x === x
y === y

final case class Cat(name: String, age: Int, color: String)

implicit val catEq: Eq[Cat] =
  Eq.instance[Cat] { (cat1, cat2) =>
    cat1.name == cat2.name && cat1.age == cat2.age &&
      cat1.color == cat2.color
  }

val cat1 = Cat("Garfield", 38, "orange and black")
val cat2 = Cat("Heathcliff", 33, "orange and black")
val optionCat1 = Option(cat1)
val optionCat2 = Option.empty[Cat]

cat1 === cat1
cat1 === cat2
optionCat1 === optionCat1
optionCat1 === optionCat2