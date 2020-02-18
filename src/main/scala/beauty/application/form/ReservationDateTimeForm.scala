package beauty.application.form

import java.time.LocalDateTime
import java.time.format.{DateTimeFormatter, ResolverStyle}

import scala.util.Try

object ReservationDateTimeForm {
  private val acceptableReservationDateTimeRegex = """([0-9]{12})""".r
  private val reservationDateTimeFormat          = DateTimeFormatter.ofPattern("yyyyMMddHHmm")

  def apply(reservationDateTime: String): Option[LocalDateTime] =
    for {
      format   <- checkReservationDateFormat(reservationDateTime)
      convert  <- checkConvertReservationDateTime(format)
      dateTime <- checkPastDateTime(convert)
    } yield dateTime

  //フォーマットのバリデーション
  private[form] def checkReservationDateFormat(reservationDateTime: String): Option[String] = reservationDateTime match {
    case acceptableReservationDateTimeRegex(v) => Some(v)
    case _                                     => None
  }

  //入力された日付がLocalDateに変換できるかバリデーション
  private[form] def checkConvertReservationDateTime(reservationDateTime: String): Option[LocalDateTime] =
    Try {
      LocalDateTime.parse(reservationDateTime, reservationDateTimeFormat)
    }.toOption

  //過去の日付じゃない
  private[form] def checkPastDateTime(reservationDateTime: LocalDateTime): Option[LocalDateTime] =
    Option(reservationDateTime).filter(LocalDateTime.now.compareTo(_) < 0)
}
