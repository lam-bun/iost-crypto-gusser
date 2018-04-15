package com.lambun.gusser.feature.prebattle

import com.lambun.gusser.feature.BasePresenter
import com.lambun.gusser.feature.BaseView

interface PreBattleContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

        fun getNearByGusser()

        fun connectToGusser(mac: String)

        fun startBattle()
    }

}