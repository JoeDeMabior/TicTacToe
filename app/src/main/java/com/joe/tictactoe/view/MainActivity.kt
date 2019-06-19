package com.joe.tictactoe.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.joe.tictactoe.R
import com.joe.tictactoe.model.Board
import com.joe.tictactoe.model.Player
import com.joe.tictactoe.presenter.TicTacToePresenter
import kotlinx.android.synthetic.main.activity_main.*

const val TAG: String = "TicTacToe"

class MainActivity : AppCompatActivity(), TicTacToeView {

    private val presenter: TicTacToePresenter = TicTacToePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_reset -> {
                presenter.onResetSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onCellClicked(view: View) {
        val button = view as Button
        val tag = button.tag.toString()
        val row = Integer.valueOf(tag.substring(0, 1).toInt())
        val col = Integer.valueOf(tag.substring(1, 2).toInt())
        Log.i(TAG, "Click Row: [$row, $col]")

        presenter.onButtonSelected(row, col)
    }

    override fun showWinner(winningPlayerDisplayLabel: String) {
        winnerPlayerLabel.text = winningPlayerDisplayLabel
        winnerPlayerViewGroup.visibility = View.VISIBLE
    }

    override fun clearWinnerDisplay() {
        winnerPlayerViewGroup.visibility = View.GONE
        winnerPlayerLabel.text = ""
    }

    override fun clearButtons() {
        for (i in 0 until button_grid.childCount)
            (button_grid.getChildAt(i) as Button).text = ""
    }

    override fun setButtonText(row: Int, col: Int, text: String) {
        val button = button_grid.findViewWithTag<Button>("" + row + col)
        if (button != null)
            button.text = text
    }

}
