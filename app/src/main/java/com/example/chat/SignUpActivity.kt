package com.example.chat

import android.content.Intent
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
            val prefUser = getSharedPreferences("userinfo", MODE_PRIVATE)
            /*
                帳密規則驗證
                正確:
                    將「名稱」「帳號」「密碼」「登入狀態」存在 shared_prefs資料夾
                    彈出「註冊成功對話框」並詢問是否記住登入狀態爾後跳轉至 MainA
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

            // 將暱稱帳號密碼存本地 √
            // 彈出對話框，內容為「註冊成功」並詢問是否要記住登入狀態
            if (error_text == "") {

                //
                prefUser.edit()
                    .putString("${user}name", name)
                    .putString("$user", user)
                    .putString("${user}pwd", pwd)
                    .apply()



                Log.d(TAG, "帳密輸入沒問題")

                // 登入狀態詢問對話框，按「是」或「否」都會跳轉到 MainA
                // 差在登入狀態不一樣，影響到下次開 APP，以 Boolean控制
                val prefLogin = getSharedPreferences("login", MODE_PRIVATE)

                val intent_to_main = Intent(this, MainActivity::class.java)

                AlertDialog.Builder(this)
                    .setTitle("Message")
                    .setMessage("Sign up successfully!")

                    // 若按 OK 登入狀態改成 true並將此次帳號存入資料夾
                    .setPositiveButton("OK") { d, w ->
                        prefLogin.edit()
                            .putBoolean("login_state", true)
                            .putString("login_userid", user)
                            .apply()
                        startActivity(intent_to_main)
                    }
                    .show()

            // 錯誤訊息對話框
            } else {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.wrong_message))
                    .setMessage(error_text)
                    .setPositiveButton("OK",null)
                    .show()
            }

        }

    }



}