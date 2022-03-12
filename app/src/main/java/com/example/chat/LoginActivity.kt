package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.chat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val TAG = LoginActivity::class.java.simpleName
    lateinit var binding: ActivityLoginBinding

    // 預設現在沒有打勾
    var remember = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 呼叫 getSharedPreferences()方法，產生一個檔名為 chat.xml的設定儲存檔，並只供本專案 (app)可讀取。
        val pref = getSharedPreferences("chat", MODE_PRIVATE)

        // 呼叫"rem_username"的 Boolean，true代表上次打勾
        // 第一次登入 "rem_username"不會存在，預設給 false
        val checked = pref.getBoolean("rem_username", false)

        // 複製上次的打勾情形
        binding.cbRemember.isChecked = checked

        // 根據現在的打勾情形，立即記錄起來並存到本地資料夾
        binding.cbRemember.setOnCheckedChangeListener { compoundButton, checked ->
            remember = checked
            pref.edit().putBoolean("rem_username", remember).apply()

            // 如果 checked == false，把記錄的 userid改成空字串
            if (!checked) {
                pref.edit().putString("USER", "").apply()
            }
        }



        setupFunction()
    }

    private fun setupFunction() {

        // 登入按鈕，登入成功跳轉回 MainA
        // 登入失敗，提示錯誤訊息
        binding.btLogin.setOnClickListener {

            // 存取帳密用，從 "userinfo"資料夾檢查
            val prefUser = getSharedPreferences("userinfo", MODE_PRIVATE)
            // 取目前輸入的帳號密碼準備做檢查
            var ed_user = binding.edLoginUserid.text.toString()
            var ed_pwd = binding.edLoginPwd.text.toString()
            var check_user = prefUser.getString("${ed_user}","")
            var check_pwd = prefUser.getString("${ed_user}pwd","")
            var error_text = ""

            error_text =
                when {
                    check_user == "" -> "The Userid is not exist."
                    ed_pwd != check_pwd -> "Wrong Password."
                    else -> ""
                }

            if (error_text == "") {

                //
                Log.d(TAG, "帳號密碼正確")

                // 登入狀態詢問對話框，按「是」或「否」都會跳轉到 MainA
                // 差在登入狀態不一樣，影響到下次開 APP，以 Boolean控制
                // TODO

                // 錯誤訊息對話框
            } else {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.wrong_message))
                    .setMessage(error_text)
                    .setPositiveButton("OK",null)
                    .show()
            }

        }

        // 註冊按鈕，前往 SignUpA
        binding.btSignUp.setOnClickListener {
            val intent_to_sign_up = Intent(this, SignUpActivity::class.java)
            startActivity(intent_to_sign_up)
        }

    }
}