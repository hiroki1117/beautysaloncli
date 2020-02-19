package app

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}

import domain.{Customer, Reservation}
import presentation.{BirthdayForm, CustomerNameForm, Information, MenuForm, PhoneNumberForm, ReservationDateTimeForm}

import scala.annotation.tailrec
import scala.io.StdIn
import presentation.{CustomerNameForm, Information, MenuForm, PhoneNumberForm, ReservationDateTimeForm}

object Prompt {
  def askUserInformation(): Information = {
    //予約情報の入力
    val reservationDateTime = validateReservationDateTime(reservationDateTimePrompt())
    val menu                = validateMenu(menuPrompt())
    val stylist             = stylistPrompt()

    //お客様情報の入力
    val customerName = validateCustomerName(customerNamePrompt())
    val phoneNumber  = validatePhoneNumber(phoneNumberPrompt())
    val birthday     = validateBirthday(birthdayPrompt())

    Information(
      customerName,
      phoneNumber,
      birthday,
      reservationDateTime,
      menu,
      stylist
    )
  }

  def confirmInformation(customer: Customer, reservation: Reservation, totalPrice: Int): Unit = {
    val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    println(s"""
         |以下の通りご予約承りました
         |お客様氏名：${customer.name}
         |電話番号：${customer.phoneNumber}
         |生年月日：${customer.birthday.toString}
         |
         |ご予約日時：${reservation.reservationDate.format(dateTimeFormat)}
         |メニュー：${reservation.menu}
         |指名スタイリスト：${reservation.stylist.map(_.toString).getOrElse("")}
         |
         |料金：${totalPrice}
         |""".stripMargin)
  }

  def print20PercentOffForUnder18(): Unit = println("18歳以下のお客様は20%オフさせていただきます")

  @tailrec
  private[app] def validateCustomerName(userInput: String): String = CustomerNameForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validateCustomerName(customerNamePrompt())
    }
  }

  private[app] def customerNamePrompt(): String = {
    println("お客様のお名前を入力してください")
    StdIn.readLine()
  }

  @tailrec
  private[app] def validatePhoneNumber(userInput: String): String = PhoneNumberForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validatePhoneNumber(phoneNumberPrompt())
    }
  }

  private[app] def phoneNumberPrompt(): String = {
    println("電話番号を入力してください")
    StdIn.readLine()
  }

  @tailrec
  private[app] def validateBirthday(userInput: String): LocalDate = BirthdayForm(userInput) match {
    case Right(v) => v
    case Left(error) => {
      println(error.massage)
      validateBirthday(birthdayPrompt())
    }
  }

  private[app] def birthdayPrompt(): String = {
    println("生年月日を入力してください (例：1995-11-17)")
    StdIn.readLine()
  }

  @tailrec
  private[app] def validateReservationDateTime(userInput: String): LocalDateTime =
    ReservationDateTimeForm(userInput) match {
      case Right(v) => v
      case Left(error) => {
        println(error.massage)
        validateReservationDateTime(reservationDateTimePrompt())
      }
    }

  private[app] def reservationDateTimePrompt(): String = {
    println("予約日時を入力してください (例：202012192100")
    StdIn.readLine()
  }

  @tailrec
  private[app] def validateMenu(userInput: String): String = MenuForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validateMenu(menuPrompt())
    }
  }

  private[app] def menuPrompt(): String = {
    println("""メニューを入力してください (例：カットの場合 1
        |1. カット 5000円
        |2. カットカラー 10000円
        |3. カットパーマ 10000円
        |4. カラーのみ　7000円
        |5. パーマのみ　7000円""".stripMargin)
    StdIn.readLine()
  }

  private[app] def stylistPrompt(): String = {
    println("スタイリストを指名してください")
    StdIn.readLine()
  }
}
