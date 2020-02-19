package beauty.presentation.form

class PhoneNumberForm(val phoneNumber: String)

object PhoneNumberForm {
  private val acceptableNumRegex = """(\d{10,11})""".r

  def apply(phoneNumber: String): Option[String] = phoneNumber match {
    case acceptableNumRegex(v) => Some(v)
    case _                     => None
  }
}
