package com.example.leafy.Utilities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import com.example.leafy.Data.SetDB.Companion.DB_NAME
import com.example.leafy.Data.SetDB.Companion.USER_EMAIL
import com.example.leafy.Data.SetDB.Companion.USER_ID
import com.example.leafy.Data.SetDB.Companion.USER_IMG
import com.example.leafy.Data.SetDB.Companion.USER_L_NAME
import com.example.leafy.Data.SetDB.Companion.USER_NAME
import com.example.leafy.Data.SetDB.tblPlant.Companion.COL_ID_USER
import com.example.leafy.MainActivity
import com.example.leafy.R

class LoginActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //this.deleteDatabase(DB_NAME);
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
            R.id.btnLogin -> {
                val user = findViewById<EditText>(R.id.txtCorreo)
                val pass = findViewById<EditText>(R.id.txtContraseña)
                if (user.text.isEmpty() || pass.text.isEmpty() || user.text.isNullOrBlank() || pass.text.isNullOrBlank())  {
                    Toast.makeText(this, "Ingrese todos los datos por favor", Toast.LENGTH_SHORT).show()
                } else {
                    val usr = UserApplication.dbHelper.getUser(user.text.toString(), pass.text.toString())
                    //val p1 = UserApplication.dbHelper.getPublication(1)
                    if (usr?.id != null) {
                        USER_ID = usr?.id
                        USER_IMG = usr?.avatar
                        USER_NAME = usr?.nombre
                        USER_EMAIL = usr?.correo
                        USER_L_NAME = usr?.apellido
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    /*else {
                        Toast.makeText(this, "Correo o contraseña no válidos", Toast.LENGTH_SHORT).show()
                    }*/
                }

            }
        }
    }


}