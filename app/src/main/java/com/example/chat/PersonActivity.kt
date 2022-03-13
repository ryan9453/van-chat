package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chat.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {

    companion object {
        private val TAG = PersonActivity::class.java.simpleName
    }
    lateinit var binding: ActivityPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityPersonBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 在紀錄 login_state的資料夾中有存取 紀錄登入狀態當下的帳號
        // 因為 login_state = true 才會進到此畫面，故可以用這裡的帳號去索引 userinfo找到暱稱
        val prefLogin = getSharedPreferences("login", MODE_PRIVATE)
        var login_userid = prefLogin.getString("login_userid", "")

        // 顯示用戶暱稱和帳號用
        val prefUser = getSharedPreferences("userinfo", MODE_PRIVATE)
        // 用取得的帳號去 userinfo資料夾索引取得暱稱
        var username = prefUser.getString("${login_userid}name", "")

        binding.tvPersonShowName.setText(username)
        binding.tvPersonShowUserid.setText(login_userid)

        Log.d(TAG, "onCreate: PersonActivity")

        setupFunction()

    }

    private fun setupFunction() {

        // 登出按鈕
        // 將登入狀態改成 false之後存入 login資料夾並跳轉至 LoginA
        binding.btLogout.setOnClickListener {

            // 登入狀態改成 false之後存入 login資料夾
            val prefLogin = getSharedPreferences("login", MODE_PRIVATE)
            var login = prefLogin.getBoolean("login_state", false)
            login = false
            prefLogin.edit()
                .putBoolean("login_state", login)
                .putString("login_userid", "")
                .apply()

            // 跳轉至 LoginA
            val intent_to_login = Intent(this, LoginActivity::class.java)
            startActivity(intent_to_login)

        }
    }

}