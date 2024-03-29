package app

import java.time.LocalDate

import domain.{Customer, PriceCalculationService, Reservation, ReservationFactory}
import presentation.Information

object Main {
  def main(args: Array[String]): Unit = {
    //入力を受け付ける
    val info = Prompt.askUserInformation()

    //ドメインオブジェクトを作成
    val customer    = createCustomer(info)
    val reservation = createReservation(info)
    //料金を計算
    val price = PriceCalculationService.calcTotalPrice(customer, reservation)

    //18歳以下であれば値引きを知らせる
    if (customer.getAge(LocalDate.now()) <= 18)
      Prompt.print20PercentOffForUnder18()

    //料金の確認を表示する
    Prompt.confirmInformation(customer, reservation, price)
  }

  def createCustomer(info: Information): Customer =
    Customer(info.customerName, info.phoneNumber, info.birthday)

  def createReservation(info: Information): Reservation =
    ReservationFactory.createReservation(info.menu, info.reservationDateTime, info.stylist)
}
