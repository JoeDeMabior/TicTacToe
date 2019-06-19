package com.joe.tictactoe.controller

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
import kotlinx.android.synthetic.main.activity_main.*

const val TAG: String = "TicTacToe"

class MainActivity : AppCompatActivity() {

    private var model: Board? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = Board()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_reset -> {
                reset()
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

        val playerThatMoved: Player? = model?.mark(row, col)

        if (playerThatMoved != null) {
            button.text = playerThatMoved.toString()
            if (model?.getWinner() != null) {
                winnerPlayerLabel.text = playerThatMoved.toString()
                winnerPlayerViewGroup.visibility = View.VISIBLE
            }
        }
    }

    private fun reset() {
        winnerPlayerViewGroup.visibility = View.GONE
        winnerPlayerLabel.text = ""

        model?.restart()

        for (i in 0 until button_grid.childCount) {
            (button_grid.getChildAt(i) as Button).text = ""
        }
    }

}
