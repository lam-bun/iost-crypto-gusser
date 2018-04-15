package com.lambun.gusser.feature.prebattle

import android.view.View
import com.lambun.gusser.R
import com.lambun.gusser.databinding.ActPreBattleBinding
import com.lambun.gusser.feature.BaseBindingActivity

class PreBattleActivity : BaseBindingActivity<ActPreBattleBinding>(), PreBattleContract.View {

    private lateinit var presenter: PreBattleContract.Presenter

    override fun initView() {
        presenter = PreBattlePresenter(this)
        getBinding().onSearchClickListener = View.OnClickListener {
            presenter.getNearByGusser()
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.act_pre_battle
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

}