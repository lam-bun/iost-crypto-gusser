package com.lambun.gusser

import android.app.Application

class Gusser : Application() {

    companion object {

        private var INSTANCE: Gusser? = null

        @JvmStatic
        fun getInstance(): Gusser {
            return INSTANCE!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}