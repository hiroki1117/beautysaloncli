package beutiy.application.form

class PhoneNumberForm(val phoneNumber: String)

object PhoneNumberForm {
  private val acceptableNumRegex = """([0-9]{10,11})""".r

  def apply(phoneNumber: String): Option[String] = phoneNumber match {
    case acceptableNumRegex(v) => Some(v)
    case _ => None
  }
}
