package com.lambun.gusser.feature.prebattle

import android.text.TextUtils
import android.view.View
import android.widget.ScrollView
import com.lambun.gusser.R
import com.lambun.gusser.databinding.ActPreBattleBinding
import com.lambun.gusser.feature.BaseBindingActivity
import kotlinx.android.synthetic.main.act_pre_battle.*
import org.web3j.crypto.Credentials

class PreBattleActivity : BaseBindingActivity<ActPreBattleBinding>(), PreBattleContract.View {

    private lateinit var presenter: PreBattleContract.Presenter

    override fun initData() {
        presenter = PreBattlePresenter(this)
    }

    override fun initView() {
        getBinding().onConnectClickListener = View.OnClickListener {
            val ip = et_ip.text.toString().trim()
            if (TextUtils.isEmpty(ip)) {
                return@OnClickListener
            }
            presenter.connectEth(ip)
        }

        getBinding().onGetCredentialsClickListener = View.OnClickListener {
            presenter.getCredentials()
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.act_pre_battle
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun log(log: String) {
        tv_logs.append(log + "\n")
        scroller.fullScroll(ScrollView.FOCUS_DOWN)
    }

    override fun showLocalCredentialsInfo(credentials: Credentials) {
        val address = credentials.address
        val publicKey = credentials.ecKeyPair.publicKey
        val privateKey = credentials.ecKeyPair.privateKey
        log("address = $address")
        log("publicKey = $publicKey")
        log("privateKey = $privateKey")
    }

    override fun showRemoteUserInfo() {

    }

}
