package com.lambun.gusser.feature

interface BaseView<in P : BasePresenter> {

    fun setPresenter(presenter: P)
}