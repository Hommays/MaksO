package com.maxim.makso

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.maxim.makso.constance.Constance
import com.maxim.makso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var avatarImageId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val l = data?.getStringExtra(Constance.LOGIN)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (login == l && password == p) {
                bindingClass.imAvatar.visibility = View.VISIBLE
                bindingClass.imAvatar.setImageResource(avatarImageId)
                bindingClass.bHide.visibility = View.GONE
                bindingClass.bExit.text = "Выйти"
            } else
                bindingClass.tvInfo.text = "Такого аккаунта не существует!"
        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {
            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data?.getStringExtra(Constance.PASSWORD)!!
            avatarImageId = data?.getIntExtra(Constance.AVATAR_ID, 0)
            bindingClass.imAvatar.visibility = View.VISIBLE
            bindingClass.imAvatar.setImageResource(avatarImageId)
            bindingClass.bHide.visibility = View.GONE
            bindingClass.bExit.text = "Выйти"

        }
    }

    fun onCLickSignIn(view: View) {
        if (bindingClass.imAvatar.isVisible &&
            bindingClass.tvInfo.text.toString() != "Такого аккаунта не существует!"
        ) {
            bindingClass.imAvatar.visibility = View.INVISIBLE
            bindingClass.tvInfo.text = ""
            bindingClass.bHide.visibility = View.VISIBLE
            bindingClass.bExit.text = getString(R.string.sing_in)

        } else {
            val intent = Intent(this, SignInUpAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)
        }
    }

    fun onCLickSignUp(view: View) {

        val intent = Intent(this, SignInUpAct::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)

    }
}

