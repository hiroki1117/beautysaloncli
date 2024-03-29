package domain

import java.time.LocalDate

import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite

class CustomerSpec extends AnyFunSuite with MockitoSugar {
  test("僕の年齢は20200214時点で24歳") {
    val customer    = Customer("hiroki", "08011119999", LocalDate.of(1995, 11, 17))
    val arg = LocalDate.of(2020, 2, 14)
    val expected    = 24

    assert(customer.getAge(arg) == expected)
  }
}
