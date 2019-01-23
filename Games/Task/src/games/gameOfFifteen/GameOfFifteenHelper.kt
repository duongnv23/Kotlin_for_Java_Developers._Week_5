package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var invert = 0
    val n = permutation.size

    val min: Int = permutation.min()!!
    val max: Int = permutation.max()!!

    for (i in min..max) {
        for (j in i + 1..max) {
            if (permutation.indexOf(i) > permutation.indexOf(j)) {
                invert++
            }
        }
    }


    return invert % 2 == 0
}