package beutiy.application.form

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.util.Try

object BirthdayForm {
  private val acceptableBirthdayRegex = """([0-9]{4}-[0-9]{2}-[0-9]{2})""".r
  private val birthdayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def apply(birthday: String): Option[LocalDate] =
    for {
      format <- checkBirthdayFormat(birthday)
      convert <- checkConvertBirthday(format)
      date <- checkFutureDate(convert)
    } yield date

  //フォーマットのバリデーション
  private[form] def checkBirthdayFormat(birthdayString: String): Option[String] = birthdayString match {
    case acceptableBirthdayRegex(v) => Some(v)
    case _ => None
  }

  //入力された日付がLocalDateに変換できるかバリデーション
  private[form] def checkConvertBirthday(birthdayString: String): Option[LocalDate] = Try {
    LocalDate.parse(birthdayString, birthdayFormat)
  }.toOption


  //未来の日付じゃない
  private[form] def checkFutureDate(birthday: LocalDate): Option[LocalDate] =
    Option(birthday).filter(LocalDate.now.compareTo(_) >= 0)
}
