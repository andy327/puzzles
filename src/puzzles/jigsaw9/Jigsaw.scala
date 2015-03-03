package puzzles.jigsaw9

sealed abstract class Orientation
case object In extends Orientation
case object Out extends Orientation

sealed abstract class Suit
case object Spade extends Suit
case object Heart extends Suit
case object Club extends Suit
case object Diamond extends Suit

sealed abstract class Direction
case object N extends Direction
case object E extends Direction
case object S extends Direction
case object W extends Direction

class Connection(val orientation: Orientation, val suit: Suit) {
  def connectsWith(other: Connection): Boolean = (orientation != other.orientation && suit == other.suit)
  override def toString: String = (orientation, suit) match {
    case (In, Spade) => 0x2664.toChar.toString
    case (In, Heart) => 0x2661.toChar.toString
    case (In, Club) => 0x2667.toChar.toString
    case (In, Diamond) => 0x2662.toChar.toString
    case (Out, Spade) => 0x2660.toChar.toString
    case (Out, Heart) => 0x2665.toChar.toString
    case (Out, Club) => 0x2663.toChar.toString
    case (Out, Diamond) => 0x2666.toChar.toString
  }

}

object Connection {
  def apply(orientation: Orientation, suit: Suit): Connection = new Connection(orientation, suit)
}

/** Connection Types **/
val inSpade = Connection(In, Spade)
val inHeart = Connection(In, Heart)
val inClub = Connection(In, Club)
val inDiamond = Connection(In, Diamond)
val outSpade = Connection(Out, Spade)
val outHeart = Connection(Out, Heart)
val outClub = Connection(Out, Club)
val outDiamond = Connection(Out, Diamond)

class Piece(val sides: Connection*) { // defines a single orientation of a jigsaw piece
  assert(sides.size == 4) // sides describes the north, east, south, and west facing connections

  /** returns whether other matches the specified edge of this piece */
  def matchesPiece(other: Piece, edge: Direction): Boolean = edge match {
    case N => sides(0).connectsWith(other.sides(2))
    case E => sides(1).connectsWith(other.sides(3))
    case S => sides(2).connectsWith(other.sides(0))
    case W => sides(3).connectsWith(other.sides(1))
  }

  def rotate90: Piece = new Piece((sides.last +: sides.init): _*)
  def rotate180: Piece = new Piece((sides ++ sides).drop(2).take(4): _*)
  def rotate270: Piece = new Piece((sides.tail :+ sides.head): _*)
  def rotations: Iterator[Piece] = Iterator(this, rotate90, rotate180, rotate270)
  def rotateToN(dir: Direction): Piece = dir match {
    case N => this
    case E => this.rotate270
    case S => this.rotate180
    case W => this.rotate90
  }


  override def toString: String = sides.mkString("[", " ,", " ]")
}

object Piece {
  def apply(sides: Connection*): Piece = new Piece(sides: _*)
}
