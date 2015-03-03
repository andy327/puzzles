package puzzles.fourcubes

sealed abstract class Color
case object R extends Color
case object P extends Color
case object Y extends Color

class Cube(val seq: Color*) {
  import Cube._
  require(seq.length == 12)

  val minimalLayout: Seq[Color] = generators.map(permuteSeq(_)).minBy(_.mkString)

  private def permuteSeq(mapping: Seq[Int]): Seq[Color] = {
    require(mapping.length == 12)
    mapping.zip(seq).sortBy(_._1).map(_._2)
  }

  private def permuteCube(mapping: Seq[Int]): Cube = Cube(permuteSeq(mapping): _*)

  def cubePermutations: Seq[Cube] = generators.map(permuteCube(_))

  def faceMatchesCube(face: Seq[Int], other: Cube, mask: List[Boolean]): Boolean = {
    assert (mask.size == 4)
    val matchingFace = matchMap(face)
    val faceColors = face.map(seq.apply(_))
    val matchingFaceColors = matchingFace.map(other.seq.apply(_))
    (faceColors zip matchingFaceColors).map{ case (a, b) => a == b }.zip(mask)
      .forall{ case (edgeMatch, mask) => edgeMatch || mask }
  }

  def rotateF1_90:  Cube = permuteCube(p_2)
  def rotateF1_180: Cube = permuteCube(p_3)
  def rotateF1_270: Cube = permuteCube(p_4)
  def rotateF2_90:  Cube = permuteCube(p_5)
  def rotateF2_180: Cube = permuteCube(p_6)
  def rotateF2_270: Cube = permuteCube(p_7)
  def rotateF3_90:  Cube = permuteCube(p_8)
  def rotateF3_180: Cube = permuteCube(p_9)
  def rotateF3_270: Cube = permuteCube(p_10)
  def rotateV1_120: Cube = permuteCube(p_11)
  def rotateV1_240: Cube = permuteCube(p_12)
  def rotateV2_120: Cube = permuteCube(p_13)
  def rotateV2_240: Cube = permuteCube(p_14)
  def rotateV3_120: Cube = permuteCube(p_15)
  def rotateV3_240: Cube = permuteCube(p_16)
  def rotateV4_120: Cube = permuteCube(p_17)
  def rotateV4_240: Cube = permuteCube(p_18)
  def flipE1:       Cube = permuteCube(p_19)
  def flipE2:       Cube = permuteCube(p_20)
  def flipE3:       Cube = permuteCube(p_21)
  def flipE4:       Cube = permuteCube(p_22)
  def flipE5:       Cube = permuteCube(p_23)
  def flipE6:       Cube = permuteCube(p_24)
  override def toString: String = seq.mkString("[", " ", "]")
  def ==(that: Cube): Boolean = minimalLayout == that.minimalLayout
}

object Cube {
  def apply(elems: Color*): Cube = new Cube(elems: _*)
  val p_1  = Seq(0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11)
  val p_2  = Seq(1,  2,  3,  0,  5,  6,  7,  4,  9, 10, 11,  8)
  val p_3  = Seq(2,  3,  0,  1,  6,  7,  4,  5, 10, 11,  8,  9)
  val p_4  = Seq(3,  0,  1,  2,  7,  4,  5,  6, 11,  8,  9, 10)
  val p_5  = Seq(7,  3,  6, 11,  0,  2, 10,  8,  4,  1,  5,  9)
  val p_6  = Seq(8, 11, 10,  9,  7,  6,  5,  4,  0,  3,  2,  1)
  val p_7  = Seq(4,  9,  5,  1,  8, 10,  2,  0,  7, 11,  6,  3)
  val p_8  = Seq(8,  4,  0,  7,  9,  1,  3, 11, 10,  5,  2,  6)
  val p_9  = Seq(10, 9,  8, 11,  5,  4,  7,  6,  2,  1,  0,  3)
  val p_10 = Seq(2,  5, 10,  6,  1,  9, 11,  3,  0,  4,  8,  7)
  val p_11 = Seq(4,  0,  7,  8,  1,  3, 11,  9,  5,  2,  6, 10)
  val p_12 = Seq(1,  4,  9,  5,  0,  8, 10,  2,  3,  7, 11,  6)
  val p_13 = Seq(9,  5,  1,  4, 10,  2,  0,  8, 11,  6,  3,  7)
  val p_14 = Seq(6,  2,  5, 10,  3,  1,  9, 11,  7,  0,  4,  8)
  val p_15 = Seq(5, 10,  6,  2,  9, 11,  3,  1,  4,  8,  7,  0)
  val p_16 = Seq(11, 7,  3,  6,  8,  0,  2, 10,  9,  4,  1,  5)
  val p_17 = Seq(3,  6, 11,  7,  2, 10,  8,  0,  1,  5,  9,  4)
  val p_18 = Seq(7,  8,  4,  0, 11,  9,  1,  3,  6, 10,  5,  2)
  val p_19 = Seq(0,  7,  8,  4,  3, 11,  9,  1,  2,  6, 10,  5)
  val p_20 = Seq(5,  1,  4,  9,  2,  0,  8, 10,  6,  3,  7, 11)
  val p_21 = Seq(10, 6,  2,  5, 11,  3,  1,  9,  8,  7,  0,  4)
  val p_22 = Seq(6, 11,  7,  3, 10,  8,  0,  2,  5,  9,  4,  1)
  val p_23 = Seq(9,  8, 11, 10,  4,  7,  6,  5,  1,  0,  3,  2)
  val p_24 = Seq(11,10,  9,  8,  6,  5,  4,  7,  3,  2,  1,  0)
  val generators: Seq[Seq[Int]] = Seq(
    p_1, p_2, p_3, p_4, p_5, p_6,
    p_7, p_8, p_9, p_10, p_11, p_12,
    p_13, p_14, p_15, p_16, p_17, p_18,
    p_19, p_20, p_21, p_22, p_23, p_24)
  val (top, bottom) = (Seq(0,  1,  2,  3), Seq(8,  9, 10, 11))
  val (front, back) = (Seq(8,  4,  0,  7), Seq(10, 5,  2,  6))
  val (left, right) = (Seq(4,  9,  5,  1), Seq(7, 11,  6,  3))
  val matchMap = Map(top -> bottom, bottom -> top, front -> back, back -> front, left -> right, right -> left)
}
