package beutiy.domain

sealed trait Menu {
  val price: Int
}

case object Cut extends Menu {
  val price = 5000

  override def toString: String = "カット"
}

case object CutColor extends Menu {
  val price = 10000

  override def toString: String = "カットカラー"
}

case object CutPerm extends Menu {
  val price = 10000

  override def toString: String = "カットパーマ"
}

case object ColorOnly extends Menu {
  val price = 7000

  override def toString: String = "カラーのみ"
}

case object PermOnly extends Menu {
  val price = 7000

  override def toString: String = "パーマのみ"
}

