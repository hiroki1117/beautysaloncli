package beauty.application.form

import java.time.LocalDate
import java.time.format.{DateTimeFormatter, ResolverStyle}

import scala.util.{Success, Try}

object BirthdayForm {
  private val acceptableBirthdayRegex = """([0-9]{4}-[0-9]{2}-[0-9]{2})""".r
  private val birthdayFormat          = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def apply(birthday: String): Either[BirthdayFormError ,LocalDate] =
    for {
      format  <- checkBirthdayFormat(birthday)
      convert <- checkConvertBirthday(format)
      date    <- checkFutureDate(convert)
    } yield date

  //フォーマットのバリデーション
  private[form] def checkBirthdayFormat(birthdayString: String): Either[BirthdayFormError ,String] = birthdayString match {
    case acceptableBirthdayRegex(v) => Right(v)
    case _                          => Left(FormatError)
  }

  //入力された日付がLocalDateに変換できるかバリデーション
  private[form] def checkConvertBirthday(birthdayString: String): Either[BirthdayFormError ,LocalDate] =
    Try {
      LocalDate.parse(birthdayString, birthdayFormat)
    } match {
      case Success(v) => Right(v)
      case _ => Left(ConvertError)
    }

  //未来の日付じゃない
  private[form] def checkFutureDate(birthday: LocalDate): Either[BirthdayFormError, LocalDate] =
    Option(birthday).filter(LocalDate.now.compareTo(_) >= 0) match {
      case Some(v) => Right(v)
      case None => Left(FutureDateError)
    }

  sealed class BirthdayFormError(val massage: String)
  case object FormatError       extends BirthdayFormError("入力の形式が違います。")
  case object ConvertError      extends BirthdayFormError("不正な日付が入力されました")
  case object FutureDateError extends BirthdayFormError("入力が未来の日付です")
}
