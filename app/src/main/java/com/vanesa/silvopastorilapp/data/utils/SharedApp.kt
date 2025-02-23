package com.vanesa.silvopastorilapp.data.utils


import android.app.Application

class SharedApp : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}