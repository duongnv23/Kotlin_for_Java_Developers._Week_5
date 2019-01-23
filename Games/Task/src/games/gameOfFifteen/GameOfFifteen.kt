package games.gameOfFifteen

import board.Direction
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)


class GameOfFifteen(private val initializer: GameOfFifteenInitializer = RandomGameInitializer()) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        initializer.initialPermutation.withIndex().forEach {
            board[board.getCell((it.index / board.width) + 1, (it.index % board.width) + 1)] = it.value
        }
    }

    override fun canMove(): Boolean {
        return board.any { it == null }
    }

    override fun hasWon(): Boolean {
        val currentValue = board.getAllCells().asSequence().map { board[it] }.filterNotNull().toList()
        val expectValue = currentValue.sorted()
        return currentValue == expectValue
    }

    override fun processMove(direction: Direction) {
        val empty = board.getAllCells().asSequence().first { board[it] == null }
        with(board) {
            val neighbour = getAllCells().asSequence().firstOrNull { board[it] == null }?.getNeighbour(direction.reversed())
            if (neighbour != null) {
                set(empty, get(neighbour))
                set(neighbour, null)
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = this.board.run { get(getCell(i, j)) }

}
