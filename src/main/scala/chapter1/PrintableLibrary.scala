package chapter1

object PrintableLibrary extends App {

  import PrintableSyntax._
  import PrintableInstances._

  val cat = Cat("name", 35, "white")
  print(cat)
  println()

  val cat2 = Cat("name2", 34, "black")
  cat2.print
}

trait Printable[A] {
  def format(value: A): String
  def print(value:A): Unit
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))
}

object PrintableInstances {
  implicit val printString: Printable[String] = new Printable[String] {
    override def format(value: String): String = value

    override def print(value: String): Unit = println(value)
  }
  implicit val printInt: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString

    override def print(value: Int): Unit = println(value)
  }
  implicit val printCat: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = s"FormatCat($value)"

    override def print(value: Cat): Unit = println(format(value))
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def print(implicit p: Printable[A]): Unit = println(format(p))
    def format(implicit p: Printable[A]): String = p.format(value)
  }
}

final case class Cat(name: String, age: Int, color: String)