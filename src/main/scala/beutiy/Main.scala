package beutiy

import beutiy.application.{Information, Prompt}
import beutiy.domain.{Customer, PriceCalculationService, ReservationFactory}


object Main {

  def main(args: Array[String]): Unit = {
    val info = Prompt.askUserInformation()
    val customer = createCustomer(info)
    val reservation = ReservationFactory.createReservation(info)
    val price = PriceCalculationService.calcTotalPrice(customer, reservation)

    println(price)
  }

  def createCustomer(info: Information): Customer =
    Customer(info.customerName, info.phoneNumber, info.birthday)
}
