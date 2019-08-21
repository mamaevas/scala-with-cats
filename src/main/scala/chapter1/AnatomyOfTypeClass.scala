package chapter1

object AnatomyOfTypeClass extends App {

  import JsonSyntax._
  import JsonWriterInstances._

  val wr = implicitly[JsonWriter[Person]]
  val person = Person("Dave", "dave@example.com").toJson
  val personOption_1 = Option(Person("Dave", "dave@example.com")).toJson
  val personOption_2 = Json.toJson(Option(Person("Dave", "dave@example.com")))(optionalWriter(personWriter))

  println(wr)
  println(person)
  println(personOption_1)
}

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

case object JsNull extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    (value: String) => JsString(value)

  implicit val personWriter: JsonWriter[Person] =
    (value: Person) => JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email)
    ))

  implicit def optionalWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {
      override def write(value: Option[A]): Json = value match {
        case Some(aValue) => writer.write(aValue)
        case None => JsNull
      }
    }
}

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

object JsonSyntax {

  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }

}

