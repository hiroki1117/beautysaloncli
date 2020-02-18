package beauty.application.form

object MenuForm {
  private val acceptableMenuNumRegex = """([1-5]{1})""".r

  def apply(menuNum: String): Option[String] = menuNum match {
    case acceptableMenuNumRegex(v) => Some(v)
    case _                         => None
  }
}
