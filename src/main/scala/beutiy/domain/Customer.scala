package beutiy.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

case class Customer(name: String, phoneNumber: String, birthday: LocalDate) {
  def getAge(): Int = ChronoUnit.YEARS.between(birthday, now).toInt

  private[domai] def now() = LocalDate.now()
}

