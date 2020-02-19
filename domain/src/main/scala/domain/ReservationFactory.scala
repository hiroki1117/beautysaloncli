package domain

import presentation.form.Information

object ReservationFactory {
  def createReservation(info: Information): Reservation = {
    val menu = info.menu match {
      case "1" => Cut
      case "2" => CutColor
      case "3" => CutPerm
      case "4" => ColorOnly
      case "5" => PermOnly
      case _   => throw new IllegalArgumentException
    }

    val stylist = if (info.stylist.isEmpty) None else Some(Stylist(info.stylist))

    Reservation(info.reservationDateTime, menu, stylist)
  }
}
