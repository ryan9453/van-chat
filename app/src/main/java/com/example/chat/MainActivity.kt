package com.example.chat

import android.content.Intent
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
//    val personResultLauncher = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()) { result ->
//        Log.d(TAG, "成功接收")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefLogin = getSharedPreferences("login", MODE_PRIVATE)
        var login = prefLogin.getBoolean("login_state", false)
        var username = prefLogin.getString("login_userid", "")

        if (login) binding.tvMainLoginUserid.setText(username)
        else binding.tvMainLoginUserid.setText("")

        // 呼叫「設置按鈕事件」
        setupFunction()
    }

    // 將所有按鈕事件寫在名為「設置的」方法裡，並在畫面開啟時呼叫
    private fun setupFunction() {

        // 從本地 login文件建立存取變數
        val prefLogin = getSharedPreferences("login", MODE_PRIVATE)

        // 檢查登入狀態，若為登入狀態，intent to PersonA
        // 若為否，intent to LoginA
        binding.bPerson.setOnClickListener {

            // 從存取變數建立 login的布林控制變數
            var login = prefLogin.getBoolean("login_state", false)

            if (login) {
                val intent_to_person = Intent(this, PersonActivity::class.java)
                startActivity(intent_to_person)
            } else {
                val intent_to_login = Intent(this, LoginActivity::class.java)
                startActivity(intent_to_login)
            }

        }
        binding.bHome.setOnClickListener {
            TODO()
        }
        binding.bSearch.setOnClickListener {
            TODO()
        }
    }


}