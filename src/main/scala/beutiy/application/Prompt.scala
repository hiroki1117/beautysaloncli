package beutiy.application

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}

import beutiy.application.form.{BirthdayForm, CustomerNameForm, MenuForm, PhoneNumberForm, ReservationDateTimeForm}
import beutiy.domain.{Customer, Reservation}

import scala.annotation.tailrec
import scala.io.StdIn

object Prompt {

  def askUserInformation(): Information = {

    //予約情報の入力
    val reservationDateTime = validateReservationDateTime(reservationDateTimePrompt())
    val menu = validateMenu(menuPrompt())
    val stylist = stylistPrompt()

    //お客様情報の入力
    val customerName = validateCustomerName(customerNamePrompt())
    val phoneNumber = validatePhoneNumber(phoneNumberPrompt())
    val birthday = validateBirthday(birthdayPrompt())

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

    println()
    println("以下の通りご予約承りました")
    println(s"お客様氏名：${customer.name}")
    println(s"電話番号：${customer.phoneNumber}")
    println(s"生年月日：${customer.birthday.toString}")
    println()
    println(s"ご予約日時：${reservation.reservationDate.format(dateTimeFormat)}")
    println(s"メニュー：${reservation.menu}")
    println(s"指名スタイリスト：${reservation.stylist.map(_.toString).getOrElse("")}")
    println()
    println(s"料金：${totalPrice}")
  }

  def print20PercentOffForUnder18(): Unit = println("18歳以下のお客様は20%オフさせていただきます")

  @tailrec
  private[application] def validateCustomerName(userInput: String): String = CustomerNameForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validateCustomerName(customerNamePrompt())
    }
  }

  private[application] def customerNamePrompt(): String = {
    println("お客様のお名前を入力してください")
    StdIn.readLine()
  }

  @tailrec
  private[application] def validatePhoneNumber(userInput: String): String = PhoneNumberForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validatePhoneNumber(phoneNumberPrompt())
    }
  }

  private[application] def phoneNumberPrompt(): String = {
    println("電話番号を入力してください")
    StdIn.readLine()
  }

  @tailrec
  private[application] def validateBirthday(userInput: String): LocalDate = BirthdayForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validateBirthday(birthdayPrompt())
    }
  }

  private[application] def birthdayPrompt(): String = {
    println("生年月日を入力してください (例：1995-11-17)")
    StdIn.readLine()
  }

  @tailrec
  private[application] def validateReservationDateTime(userInput: String): LocalDateTime = ReservationDateTimeForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validateReservationDateTime(reservationDateTimePrompt())
    }
  }

  private[application] def reservationDateTimePrompt(): String = {
    println("予約日時を入力してください (例：202012192100")
    StdIn.readLine()
  }

  @tailrec
  private[application] def validateMenu(userInput: String): Int = MenuForm(userInput) match {
    case Some(v) => v
    case None => {
      println("不正な値が入力されました")
      validateMenu(menuPrompt())
    }
  }

  private[application] def menuPrompt(): String = {
    println(
      """メニューを入力してください (例：カットの場合 1
        |1. カット 5000円
        |2. カットカラー 10000円
        |3. カットパーマ 10000円
        |4. カラーのみ　7000円
        |5. パーマのみ　7000円""".stripMargin)
    StdIn.readLine()
  }

  private[application] def stylistPrompt(): String = {
    println("スタイリストを指名してください")
    StdIn.readLine()
  }
}
