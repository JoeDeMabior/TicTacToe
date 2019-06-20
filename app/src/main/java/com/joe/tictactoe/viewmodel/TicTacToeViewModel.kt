package com.joe.tictactoe.viewmodel

import android.databinding.ObservableArrayMap
import android.databinding.ObservableField
import com.joe.tictactoe.model.Board

class TicTacToeViewModel : ViewModel {

    var model: Board? = null

    var cells = ObservableArrayMap<String, String>()
    var winner = ObservableField<String>()

    init {
        model = Board()
    }

    override fun onCreate() {

    }

    override fun onPause() {

    }

    override fun onResume() {

    }

    override fun onDestroy() {

    }

    fun onResetSelected() {
        model?.restart()
        winner.set(null)
        cells.clear()
    }

    fun onClickedCellAt(row: Int, col: Int) {
        cells["" + row + col] = model?.mark(row, col)?.toString()
        winner.set(if (model?.getWinner() == null) null else model?.getWinner().toString())
    }

}
