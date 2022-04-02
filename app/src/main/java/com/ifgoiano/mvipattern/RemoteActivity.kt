package com.ifgoiano.mvipattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RemoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote)
        setTitle("MVI Activity - REMOTE")
    }
}