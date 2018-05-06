package com.example.tweeter.presenter

interface IBasePresenter<ViewType> {

    fun setView(viewType: ViewType)
    fun destroyView()
}