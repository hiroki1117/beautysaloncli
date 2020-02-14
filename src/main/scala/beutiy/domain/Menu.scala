package beutiy.domain

sealed trait Menu {
  val price: Int
}

case object Cut extends Menu {val price = 5000}
case object CutColor extends Menu {val price = 10000}
case object CutPerm extends Menu {val price = 10000}
case object ColorOnly extends Menu {val price = 10000}
case object PermOnly extends Menu {val price = 7000}

