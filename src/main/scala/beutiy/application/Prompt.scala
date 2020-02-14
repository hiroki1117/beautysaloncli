package beutiy.application

import java.time.{LocalDate, LocalDateTime}

import beutiy.application.form.{BirthdayForm, CustomerNameForm, MenuForm, PhoneNumberForm, ReservationDateTimeForm}

import scala.annotation.tailrec
import scala.io.StdIn

object Prompt {

  def askUserInformation(): Information = {
    val customerName = validateCustomerName(customerNamePrompt())
    val phoneNumber = validatePhoneNumber(phoneNumberPrompt())
    val birthday = validateBirthday(birthdayPrompt())
    val reservationDateTime = validateReservationDateTime(reservationDateTimePrompt())
    val menu = validateMenu(menuPrompt())
    val stylist = stylistPrompt()
    Information(
      customerName,
      phoneNumber,
      birthday,
      reservationDateTime,
      menu,
      stylist
    )
  }

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
    println("生年月日を入力してください")
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
    println("予約日時を入力してください")
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
    println("メニューを入力してください")
    StdIn.readLine()
  }

  private[application] def stylistPrompt(): String = {
    println("スタイリストを指名してください")
    StdIn.readLine()
  }
}
