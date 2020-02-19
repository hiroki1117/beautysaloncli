package presentation

import java.time.{LocalDate, LocalDateTime}

import presentation.Information._

case class Information(
    customerName: CustomerName,
    phoneNumber: PhoneNumber,
    birthday: LocalDate,
    reservationDateTime: LocalDateTime,
    menu: Menu,
    stylist: Stylist
)

object Information {
  type CustomerName = String
  type PhoneNumber = String
  type Menu = String
  type Stylist = String
}