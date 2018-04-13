package com.lambun.gusser.feature.login

import com.lambun.gusser.feature.BasePresenter
import com.lambun.gusser.feature.BaseView

interface LoginContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {

        fun login()
    }
}