package com.irzstudio.todolist.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.irzstudio.todolist.R
import com.irzstudio.todolist.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val splash_time_out: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, splash_time_out)
    }
}