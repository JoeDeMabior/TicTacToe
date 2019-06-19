package com.joe.tictactoe.view

interface TicTacToeView {
    fun showWinner(winningPlayerDisplayLabel: String)
    fun clearWinnerDisplay()
    fun clearButton()
    fun setButtonText(row: Int, col: Int, text: String)
}