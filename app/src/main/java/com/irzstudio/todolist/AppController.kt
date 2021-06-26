package com.irzstudio.todolist

import android.app.Application
import android.content.Context

class AppController: Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object{
        private var INSTANCE: AppController? = null

        @JvmStatic
        fun getInstance() : Context {
            return INSTANCE as AppController
        }

    }
}