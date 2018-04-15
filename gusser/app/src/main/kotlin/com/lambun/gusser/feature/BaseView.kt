package com.lambun.gusser.feature

interface BaseView<P : BasePresenter> {

    fun showMessage(message: String)
}