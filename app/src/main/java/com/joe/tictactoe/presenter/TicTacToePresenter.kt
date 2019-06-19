package com.joe.tictactoe.presenter

import com.joe.tictactoe.model.Board
import com.joe.tictactoe.model.Player
import com.joe.tictactoe.view.TicTacToeView

class TicTacToePresenter(var view: TicTacToeView) : Presenter {

    var model: Board? = null

    init {
        this.model = Board()
    }


    override fun onCreate() {
        model = Board()
    }

    override fun onPause() {

    }

    override fun onResume() {

    }

    override fun onDestroy() {

    }

    fun onButtonSelected(row: Int, col: Int) {
        val playerThatMoved: Player? = model?.mark(row, col)
        if (playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString())
            if (model?.getWinner() != null)
                view.showWinner(playerThatMoved.toString())
        }
    }

    fun onResetSelected() {
        view.clearWinnerDisplay()
        view.clearButtons()
        model?.restart()
    }
}