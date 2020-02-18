package beauty.application.form

import java.time.LocalDateTime
import java.time.format.{DateTimeFormatter, ResolverStyle}

import scala.util.{Success, Try}

object ReservationDateTimeForm {
  private val acceptableReservationDateTimeRegex = """(\d{12})""".r
  private val reservationDateTimeFormat =
    DateTimeFormatter.ofPattern("uuuuMMddHHmm").withResolverStyle(ResolverStyle.STRICT)

  def apply(reservationDateTime: String): Either[ReservationDateTimeFormError, LocalDateTime] =
    for {
      format   <- checkReservationDateFormat(reservationDateTime)
      convert  <- checkConvertReservationDateTime(format)
      dateTime <- checkPastDateTime(convert)
    } yield dateTime

  //フォーマットのバリデーション
  private[form] def checkReservationDateFormat(
      reservationDateTime: String
  ): Either[ReservationDateTimeFormError, String] =
    reservationDateTime match {
      case acceptableReservationDateTimeRegex(v) => Right(v)
      case _                                     => Left(FormatError)
    }

  //入力された日付がLocalDateに変換できるかバリデーション
  private[form] def checkConvertReservationDateTime(
      reservationDateTime: String
  ): Either[ReservationDateTimeFormError, LocalDateTime] =
    Try {
      LocalDateTime.parse(reservationDateTime, reservationDateTimeFormat)
    } match {
      case Success(v) => Right(v)
      case _          => Left(ConvertError)
    }

  //過去の日付じゃない
  private[form] def checkPastDateTime(
      reservationDateTime: LocalDateTime
  ): Either[ReservationDateTimeFormError, LocalDateTime] =
    Option(reservationDateTime).filter(LocalDateTime.now.compareTo(_) < 0) match {
      case Some(v) => Right(v)
      case None    => Left(PastDateTimeError)
    }

  sealed class ReservationDateTimeFormError(val massage: String)
  case object FormatError       extends ReservationDateTimeFormError("入力の形式が違います。")
  case object ConvertError      extends ReservationDateTimeFormError("不正な日付が入力されました")
  case object PastDateTimeError extends ReservationDateTimeFormError("入力が過去の日時です")
}
