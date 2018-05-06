package com.example.tweeter.ui.base

import android.content.Context

interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun getContext() : Context
}