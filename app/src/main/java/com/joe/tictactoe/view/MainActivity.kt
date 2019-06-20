package com.joe.tictactoe.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.joe.tictactoe.R
import com.joe.tictactoe.databinding.TicTacToeBinding
import com.joe.tictactoe.viewmodel.TicTacToeViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel = TicTacToeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<TicTacToeBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.onCreate()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_reset -> {
                viewModel.onResetSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
