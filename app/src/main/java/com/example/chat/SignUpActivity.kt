package com.example.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.chat.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    companion object {
        private val TAG = SignUpActivity::class.java.simpleName
    }

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: signup")

        // 註冊完成的送出按鈕
        binding.btSignSend.setOnClickListener {

            // 取輸入方塊的值
            // 建立登入狀態變數，初始值為 false
            var name = binding.edSignName.text.toString()
            var user = binding.edSignUserid.text.toString()
            var pwd = binding.edSignPwd.text.toString()
            var pwdg = binding.edSignPwdAgain.text.toString()
            var login_state: Boolean = false

            /*
                帳密規則驗證
                正確:
                    將「名稱」「帳號」「密碼」「登入狀態」存在 shared_prefs資料夾
                    彈出「註冊成功對話框」並跳轉至 MainA
                錯誤:
                    彈出「錯誤訊息對話框」
            */
            var error_text = ""
            error_text =
                when {
                    CheckNumber(user).userId() == CheckNumber.NumberState.TOOSHORT -> getString(R.string.userid_too_short)
                    CheckNumber(user).userId() == CheckNumber.NumberState.TOOLONG -> getString(R.string.userid_too_long)
                    CheckNumber(pwd).passWord() == CheckNumber.NumberState.TOOSHORT -> getString(R.string.pwd_too_short)
                    CheckNumber(pwd).passWord() == CheckNumber.NumberState.TOOLONG -> getString(R.string.pwd_too_long)
                    CheckNumber(pwd).passWord() == CheckNumber.NumberState.WRONG -> getString(R.string.pwd_is_wrong)
                    pwd != pwdg -> getString(R.string.pwd_is_not_same)
                    else -> ""
                }
            if (error_text == "") {
                // 正確
                Log.d(TAG, "帳密輸入沒問題")
            } else {
                // 錯誤訊息對話框
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.wrong_message))
                    .setMessage(error_text)
                    .setPositiveButton("OK",null)
                    .show()
            }

        }

    }



}