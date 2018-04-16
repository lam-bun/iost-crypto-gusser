package com.lambun.gusser.feature.prebattle

import com.lambun.gusser.feature.BasePresenter
import com.lambun.gusser.feature.BaseView
import org.web3j.crypto.Credentials

interface PreBattleContract {

    interface View : BaseView<Presenter> {

        fun showLocalCredentialsInfo(credentials: Credentials)

        fun showRemoteUserInfo()

        fun log(log: String)
    }

    interface Presenter : BasePresenter {

        fun connectEth(ip: String)

        fun getCredentials()

        fun getRemoteUserInfo()

        fun joinBattle()
    }

}