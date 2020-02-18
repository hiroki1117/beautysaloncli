package beauty.domain

sealed trait Menu {
  val name: String
  val price: Int
}

case object Cut extends Menu {
  val name  = "カット"
  val price = 5000

  override def toString: String = name
}

case object CutColor extends Menu {
  val name  = "カットカラー"
  val price = 10000

  override def toString: String = name
}

case object CutPerm extends Menu {
  val name  = "カットパーマ"
  val price = 10000

  override def toString: String = name
}

case object ColorOnly extends Menu {
  val name  = "カラーのみ"
  val price = 7000

  override def toString: String = name
}

case object PermOnly extends Menu {
  val name  = "パーマのみ"
  val price = 7000

  override def toString: String = "パーマのみ"
}
