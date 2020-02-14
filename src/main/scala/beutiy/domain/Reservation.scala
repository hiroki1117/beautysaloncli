package beutiy.domain

import java.time.LocalDateTime

case class Reservation(reservationDate: LocalDateTime, menu: Menu, stylist: Option[Stylist])
