package beauty.domain

import java.time.LocalDate

import beauty.domain.{ColorOnly, Customer, Cut, CutColor, CutPerm, PermOnly, PriceCalculationService, Reservation, Stylist}
import org.mockito.MockitoSugar
import org.mockito.ArgumentMatchersSugar
import org.scalatest.funsuite.AnyFunSuite

class PriceCalculationServiceSpec extends AnyFunSuite with MockitoSugar with ArgumentMatchersSugar {
  val target = PriceCalculationService

  test("18歳割引なし、メニュー、指定なし") {
    val mockCustomer    = mock[Customer]
    val mockReservation = mock[Reservation]
    when(mockCustomer.getAge(any[LocalDate])).thenReturn(30)
    when(mockReservation.stylist).thenReturn(Option.empty[Stylist])

    //カット
    when(mockReservation.menu).thenReturn(Cut)
    val expected1 = Cut.price
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected1)

    //カットカラー
    when(mockReservation.menu).thenReturn(CutColor)
    val expected2 = CutColor.price
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected2)

    //カットパーマ
    when(mockReservation.menu).thenReturn(CutPerm)
    val expected3 = CutPerm.price
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected3)

    //カラーのみ
    when(mockReservation.menu).thenReturn(ColorOnly)
    val expected4 = ColorOnly.price
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected4)

    //パーマのみ
    when(mockReservation.menu).thenReturn(PermOnly)
    val expected5 = PermOnly.price
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected5)
  }

  test("指名がある場合は指名料500円") {
    val mockCustomer    = mock[Customer]
    val mockReservation = mock[Reservation]
    when(mockCustomer.getAge(any[LocalDate])).thenReturn(30)
    when(mockReservation.stylist).thenReturn(Option(Stylist("hoge")))
    when(mockReservation.menu).thenReturn(Cut)

    val expected = Cut.price + 500
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected)
  }

  test("18歳以下の場合は20%off") {
    val mockCustomer    = mock[Customer]
    val mockReservation = mock[Reservation]
    when(mockCustomer.getAge(any[LocalDate])).thenReturn(10)
    when(mockReservation.stylist).thenReturn(Option.empty[Stylist])
    when(mockReservation.menu).thenReturn(Cut)

    val expected = { Cut.price * 0.8 }.toInt
    assert(target.calcTotalPrice(mockCustomer, mockReservation) == expected)
  }
}
