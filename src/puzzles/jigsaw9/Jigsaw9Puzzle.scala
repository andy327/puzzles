package puzzles.jigsaw9

object Jigsaw9Puzzle {

  /** Pieces **/
  val piece1 = Piece(outDiamond, inHeart, inDiamond, outSpade)
  val piece2 = Piece(outSpade, inSpade, inClub, outHeart)
  val piece3 = Piece(outHeart, inDiamond, inClub, outClub)
  val piece4 = Piece(outClub, inClub, inDiamond, outDiamond)
  val piece5 = Piece(outSpade, inHeart, inClub, outSpade)
  val piece6 = Piece(outHeart, inSpade, inHeart, outClub)
  val piece7 = Piece(outDiamond, inSpade, inHeart, outSpade)
  val piece8 = Piece(outDiamond, inDiamond, inHeart, outHeart)
  val piece9 = Piece(outDiamond, inClub, inClub, outHeart)
  val allPieces = Set(piece1, piece2, piece3, piece4, piece5, piece6, piece7, piece8, piece9)

  /** Solution **/
  val solutionSet = for (loc0 <- allPieces) yield {
    val rest = allPieces - loc0
    val combos2 = rest.flatMap{ loc1 => List(N,E,S,W).map(dir => (loc1, dir)) }
      .filter{ case(loc1, dir) => loc0.matchesPiece(loc1, E) }
  }

  // naive
  val solutionSet = allPieces.flatMap(_.rotations).permutations.filter { grid => {
    grid(0).matchesPiece(grid(1), E) &&
    grid(1).matchesPiece(grid(2), E) &&
    grid(0).matchesPiece(grid(3), S) &&
    grid(1).matchesPiece(grid(4), S) &&
    grid(3).matchesPiece(grid(4), E) &&
    grid(2).matchesPiece(grid(5), S) &&
    grid(4).matchesPiece(grid(5), E) &&
    grid(3).matchesPiece(grid(6), S) &&
    grid(4).matchesPiece(grid(7), S) &&
    grid(6).matchesPiece(grid(7), E) &&
    grid(5).matchesPiece(grid(8), S) &&
    grid(7).matchesPiece(grid(8), E)
  } }

  /** Verifies a partial solution for a 3x3 grid filled from top-to-bottom, left-to-right */
  def verifySolution(sol: List[(Piece, Direction)]): Boolean = {
    val (pieces, dirs) = (sol.map(_._1), sol.map(_._2))
    (sol.size <= 1 || pieces(0).rotateToN(dirs(0)).matchesPiece(pieces(1).rotateToN(dirs(1)), E)) &&
    (sol.size <= 2 || pieces(1).rotateToN(dirs(1)).matchesPiece(pieces(2).rotateToN(dirs(2)), E)) &&
    (sol.size <= 3 || pieces(0).rotateToN(dirs(0)).matchesPiece(pieces(3).rotateToN(dirs(3)), S)) &&
    (sol.size <= 4 || (pieces(1).rotateToN(dirs(1)).matchesPiece(pieces(4).rotateToN(dirs(4)), S) &&
      pieces(3).rotateToN(dirs(3)).matchesPiece(pieces(4).rotateToN(dirs(4)), E)))
    if (sol.size <= 1) true
    else if (sol.size > 1) {
      pieces(0).rotateToN(dirs(0)).matchesPiece(pieces(1).rotateToN(dirs(1)), E)
    }
  }

  val solutionSet = for ((loc0, dir0) <- allPieces.flatMap{ piece => List(N,E,S,W).map(dir => (piece, dir)) }) yield {
    // dir0 is the side of the first piece that faces north when rotated to fit in the northwest corner of the grid
    val combo2Set = (allPieces - loc0).flatMap{ loc1 => List(N,E,S,W).map(dir1 => (loc1, dir1)) }
      .filter{ case(loc1, dir1) => loc0.rotateToN(dir0).matchesPiece(loc1.rotateToN(dir1), E)}
    val combo3Set = combo2Set.map{ case(loc1, dir1) => {
      (allPieces - loc0 - loc1).flatMap{ loc1 => List(N,E,S,W).map(dir1 => (loc1, dir1)) }
    }}
  }
}
