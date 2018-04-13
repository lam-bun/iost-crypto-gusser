package com.lambun.gusser.feature

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseBindingActivity<out B : ViewDataBinding> : AppCompatActivity() {

    private lateinit var mBinding: B
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        initView()
    }

    abstract fun initView()

    abstract fun getLayoutResourceId(): Int

    fun getBinding(): B {
        return mBinding
    }

    @SuppressLint("ShowToast")
    fun showToast(toastString: String) {
        if (toast == null) {
            toast = Toast.makeText(this, toastString, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(toastString)
        }
        toast?.show()
    }
}