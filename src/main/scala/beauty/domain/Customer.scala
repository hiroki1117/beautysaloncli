package beauty.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

case class Customer(name: String, phoneNumber: String, birthday: LocalDate) {
  def getAge(baseDate: LocalDate): Int = ChronoUnit.YEARS.between(birthday, baseDate).toInt
}
