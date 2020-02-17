package beutiy.domain

object PriceCalculationService {
  private val optionPrice = 500

  def calcTotalPrice(customer: Customer, reservation: Reservation): Int = {
    val option = if (reservation.stylist.isEmpty) 0 else optionPrice
    //メニュー料金 + オプション
    val generalPrice = reservation.menu.price + option

    //18歳以下の場合は20%off
    if (customer.getAge() <= 18)
      (generalPrice * 0.8).toInt
    else
      generalPrice
  }
}
