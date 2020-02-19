package domain

import java.time.LocalDateTime

object ReservationFactory {
  def createReservation(menuArg: String, reservationDateTimeArg: LocalDateTime, stylistArg: String): Reservation = {
    val menu = menuArg match {
      case "1" => Cut
      case "2" => CutColor
      case "3" => CutPerm
      case "4" => ColorOnly
      case "5" => PermOnly
      case _   => throw new IllegalArgumentException
    }

    val stylist = if (stylistArg.isEmpty) None else Some(Stylist(stylistArg))

    Reservation(reservationDateTimeArg, menu, stylist)
  }
}
