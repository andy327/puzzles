# Puzzles

This is a place where I put miscellaneous puzzle-related projects. I've got a lot (tons) of puzzles tucked away from when I was kid, and every now and then I'll pull one out and mess around with it again. Sometimes I'll feel like approaching it programmatically‎. If I feel like writing a program to find the solution for me, I might generalize the solution, find ways to optimize the running-time, and/or try to create a solution that takes the best of both worlds: how computers can speed through millions of quick computations, and how humans can reason a large problem down with logic. The first bit of code I'm posting is for the 4-cubes puzzle.

## Four Cubes

| ![four-cube puzzle](https://i.imgur.com/QPnvpd0.jpg) | ![edge enumerations](https://i.imgur.com/pVmWKOD.png) |
|:----------------:|:--------------------------------:|
| four-cube puzzle | enumeration of edges in the cube |

This puzzle consists of four unique cubes of the same size, with each edge of a cube coloring one fourth of the incident faces a particular color. The cubes can make any of a number of arrangements, the most difficult one being a 2x2 square, but any two faces that are showing must match colors for the quarter-face that is incident to the shared edge. I had a version of the puzzle released by Binary Arts using three colors (yellow, purple, and red), titled the *Nearly Almost Perhaps Impossible Puzzle: Four Cube*. The following is reproduced by [Jaap's Puzzle Page](http://www.jaapsch.net/puzzles/fourcube.htm):

  >The Four Cube Impossible puzzle was created by Scott Nelson in 1971. This puzzle is based on the MacMahon Squares, the mathematically complete set of 24 colored puzzle squares discovered by Major P. A. MacMahon in the 1920s. Serious puzzle lovers have been challenged by these shapes for half a century, but not until Nelson's work had anyone solved how to place the 24 squares on the 24 faces of cubes so that all edges match. Amazingly enough, Scott was only nine years old when he made this discovery!
  >© 1998 Binary Arts Corp.

If you brute force the solution, you might try fixing the first cube and cycling every permutation of the other three cubes, and try something in the range of ```3! * 24^3 = 82,944``` combinations. This doesn't scale up too well, and we're doing a lot of useless work by continuing to place two cubes against two cubes that already don't match. We (the humans) certainly don't try anywhere near that many combinations on average before arriving upon the answer, so we're probably doing something right in how we narrow down the solution space. The ```FourCubePuzzle.scala``` file reflects the logic of solving the puzzle while calculating a minimal amount of combinations, and ```Cube.scala``` handles the representation of each cube and it's colorings and permutations.

# License

Who cares? Use as you wish.
