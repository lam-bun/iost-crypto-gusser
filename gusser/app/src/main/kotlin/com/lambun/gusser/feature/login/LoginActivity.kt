package com.lambun.gusser.feature.login

import android.content.Intent
import android.view.View
import com.lambun.gusser.R
import com.lambun.gusser.databinding.ActLoginBinding
import com.lambun.gusser.feature.BaseBindingActivity
import com.lambun.gusser.feature.prebattle.PreBattleActivity

class LoginActivity : BaseBindingActivity<ActLoginBinding>() {

    override fun initView() {
        getBinding().onLoginClickListener = View.OnClickListener {
            val intent = Intent(this, PreBattleActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.act_login
    }

}