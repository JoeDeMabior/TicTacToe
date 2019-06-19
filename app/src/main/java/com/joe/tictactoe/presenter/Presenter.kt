package com.joe.tictactoe.presenter

interface Presenter {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onDestroy()
}