package presentation

object CustomerNameForm {
  def apply(name: String): Option[String] =
    if (name.isEmpty)
      None
    else
      Some(name)
}
