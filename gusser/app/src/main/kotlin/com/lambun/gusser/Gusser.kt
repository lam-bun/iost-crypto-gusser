package com.lambun.gusser

import android.app.Application

class Gusser : Application() {

    private lateinit var INSTANCE: Gusser

    override fun onCreate() {
        super.onCreate()
    }

}