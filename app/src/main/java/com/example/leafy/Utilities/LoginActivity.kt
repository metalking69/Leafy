package com.example.leafy.Utilities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leafy.MainActivity
import com.example.leafy.R

class LoginActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtRegistro=findViewById(R.id.btnRegistro) as TextView
        edtRegistro.setOnClickListener(this)

        val btnLogin=findViewById(R.id.btnLogin) as Button
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnRegistro ->{
                val intent= Intent(this, RegistroActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLogin->{
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}