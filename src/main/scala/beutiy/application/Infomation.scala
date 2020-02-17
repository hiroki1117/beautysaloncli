package beutiy.application

import java.time.{LocalDate, LocalDateTime}

case class Information(
    customerName: String,
    phoneNumber: String,
    birthday: LocalDate,
    reservationDateTime: LocalDateTime,
    menu: Int,
    stylist: String
)
