package com.joe.tictactoe.model

import com.joe.tictactoe.model.Player.*

class Board() {

    var cells: Array<Array<Cell?>> = Array(3) { arrayOfNulls<Cell>(3) }
    private var winner: Player? = null
    private var state: GameState? = null
    private var currentTurn: Player? = null

    init {
        restart()
    }

    // Restart or start a new game. It will clear the board and win status
    fun restart() {
        clearCells()
        winner = null
        currentTurn = X
        state = GameState.IN_PROGRESS
    }

    /**
     * Mark the current row for the player whose current turn it is.
     * Will perform no-op if the arguments are out of range or if that position is already played.
     * Will also perform a no-op if the game is already over.
     *
     * @param row 0..2
     * @param col 0..2
     */
    fun mark(row: Int, col: Int) {
        if (isValid(row, col)) {
            cells[row][col]?.value = currentTurn
            if (isWinningMoveByPlayer(currentTurn!!, row, col)) {
                state = GameState.FINISHED
                winner = currentTurn
            } else
                flipCurrentTurn()
        }
    }

    fun getWinner(): Player {
        return winner!!
    }

    private fun clearCells() {
        for (i in 0 until 3) {
            for (j in 0 until 3)
                cells[i][j] = Cell()
        }
    }

    private fun isValid(row: Int, col: Int): Boolean {
        return if (state == GameState.FINISHED)
            false
        else if (isOutOfBounds(row) || isOutOfBounds(col))
            false
        else !isCellValueAlreadySet(row, col)
    }

    private fun isOutOfBounds(index: Int): Boolean {
        return index < 0 || index > 2
    }

    private fun isCellValueAlreadySet(row: Int, col: Int): Boolean {
        return cells[row][col]?.value != null
    }

    /**
     * Algorithm adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param player
     * @param currentRow
     * @param currentCol
     */

    private fun isWinningMoveByPlayer(player: Player, currentRow: Int, currentCol: Int): Boolean {
        return cells[currentRow][0]?.value == player
                && cells[currentRow][1]?.value == player
                && cells[currentRow][2]?.value == player
                || cells[0][currentCol]?.value == player
                && cells[1][currentCol]?.value == player
                && cells[2][currentCol]?.value == player
                || currentRow == currentCol
                && cells[0][0]?.value == player
                && cells[1][1]?.value == player
                && cells[2][2]?.value == player
                || currentRow + currentCol == 2
                && cells[0][2]?.value == player
                && cells[1][1]?.value == player
                && cells[2][0]?.value == player
    }

    private fun flipCurrentTurn(): Player {
        return if (currentTurn == X) O else X
    }


    enum class GameState {
        IN_PROGRESS, FINISHED
    }

}