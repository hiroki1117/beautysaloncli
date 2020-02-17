package beutiy.application.form

object MenuForm {
  private val acceptableMenuNumRegex = """([1-5]{1})""".r

  def apply(menuNum: String): Option[Int] = menuNum match {
    case acceptableMenuNumRegex(v) => Some(v.toInt)
    case _                         => None
  }
}
