package com.example.leafy.Utilities

import android.app.Application
import android.widget.Toast
import com.example.leafy.Data.DBHelper

class UserApplication : Application()  {

    companion object{
        lateinit var dbHelper: DBHelper
    }

    override fun onCreate() {
        super.onCreate()
        dbHelper =  DBHelper(applicationContext)
        //Toast.makeText(this, "user aplication runing", Toast.LENGTH_SHORT).show()
    }
}