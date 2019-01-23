package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val indicator = this.map { false }.toBooleanArray()
    val result: MutableList<T> = mutableListOf()

    for (i in 0 until this.size) {
        if (indicator[i]) {
            continue
        } else {
            val current = this[i]
            if (current != null) {
                if (i + 1 < size) {
                    for (j in i + 1 until size) {
                        val next = this[j]
                        if (next == null) {
                            if(j == size-1){
                                result.add(current)
                            }
                            indicator[j] = true
                            continue
                        } else {
                            if (current == next) {
                                result.add(merge(current))
                                indicator[j] = true
                            } else {
                                result.add(current)
                            }
                            break
                        }
                    }


                } else {
                    result.add(current)
                }

            }

            indicator[i] = true
        }
    }

    return result.toList()
}



