package com.example.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RoomActivity : AppCompatActivity() {

    companion object {
        private val TAG = RoomActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }
}