package com.example.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.chat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    lateinit var binding: ActivityMainBinding
    val personResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d(TAG, "成功接收")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFunction()
    }

    private fun setupFunction() {
        binding.bPerson.setOnClickListener {
            TODO()
        }
        binding.bHome.setOnClickListener {
            TODO()
        }
        binding.bSearch.setOnClickListener {
            TODO()
        }

    }


}