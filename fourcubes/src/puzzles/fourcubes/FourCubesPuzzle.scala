package puzzles.fourcubes

object FourCubesPuzzle {

  /** Cubes **/
  val cubeA1 = Cube(Y, R, Y, R, Y, P, R, P, R, R, R, R)
  val cubeA2 = Cube(P, R, R, P, P, P, P, P, P, P, R, Y)
  val cubeA3 = Cube(R, Y, Y, Y, P, Y, Y, P, Y, P, Y, R)
  val cubeA4 = Cube(P, Y, Y, Y, Y, R, P, P, R, R, R, Y)

  /** Solution **/
  val solutionSet = for (cubeOrdering <- List(cubeA1, cubeA2, cubeA3, cubeA4).permutations) yield {
    val (cube1, cube2, cube3, cube4) = (cubeOrdering(0), cubeOrdering(1), cubeOrdering(2), cubeOrdering(3))

    val combosWith2 = for(c1 <- cube1.cubePermutations; c2 <- cube2.cubePermutations) yield (c1, c2)
    val validCombosWith2 = combosWith2.filter{ case (c1, c2) => c1.faceMatchesCube(Cube.back, c2, List(false, false, false, true)) }

    val combosWith3 = cube3.cubePermutations.flatMap{ c3 => validCombosWith2.map{ case (c1, c2) => (c1, c2, c3) } }
    val validCombosWith3 = combosWith3.filter{ case (c1, c2, c3) => c2.faceMatchesCube(Cube.right, c3, List(true, false, false, false)) }

    val combosWith4 = cube4.cubePermutations.flatMap{ c4 => validCombosWith3.map{ case (c1, c2, c3) => (c1, c2, c3, c4) } }
    val solutions = combosWith4
      .filter{ case (c1, c2, c3, c4) => c3.faceMatchesCube(Cube.front, c4, List(false, true, false, false)) }
      .filter{ case (c1, c2, c3, c4) => c4.faceMatchesCube(Cube.left, c1, List(false, false, true, false)) }

    solutions
  }

  val solutions = solutionSet.flatMap(identity).toSeq

  val ANSWER = solutions.head
}
