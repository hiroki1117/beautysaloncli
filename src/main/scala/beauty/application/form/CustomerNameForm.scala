package beauty.application.form

object CustomerNameForm {
  def apply(name: String): Option[String] =
    if (name.isEmpty)
      None
    else
      Some(name)
}
