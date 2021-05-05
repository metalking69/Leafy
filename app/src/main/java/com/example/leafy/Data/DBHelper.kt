package com.example.leafy.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.util.Calendar.DATE

class DBHelper(var context: Context): SQLiteOpenHelper(context,SetDB.DB_NAME,null,SetDB.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        try{
            val createUserTable:String =  "CREATE TABLE " + SetDB.tblUser.TABLE_NAME + "(" +
                    SetDB.tblUser.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SetDB.tblUser.COL_NAME  + " VARCHAR(255)," +
                    SetDB.tblUser.COL_LASTNAME  + " VARCHAR(255)," +
                    SetDB.tblUser.COL_EMAIL   + " VARCHAR(255)," +
                    SetDB.tblUser.COL_PASSWORD   + " VARCHAR(255)," +
                    SetDB.tblUser.COL_CREATION_DATE    + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    SetDB.tblUser.COL_UPDATE_DATE    + " DATETIME DEFAULT NULL," +
                    SetDB.tblUser.COL_IMG + " BLOB)"

            db?.execSQL(createUserTable)

            val createPublicationTable:String =  "CREATE TABLE " + SetDB.tblPublication.TABLE_NAME + "(" +
                    SetDB.tblPublication.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SetDB.tblPublication.COL_TITLE + " VARCHAR(255)," +
            SetDB.tblPublication.COL_DESCRIPTION  + " VARCHAR(255)," +
            SetDB.tblPublication.COL_IDUSER   + " INTEGER," +
            SetDB.tblPublication.COL_PLANTKIND    + " VARCHAR(255)," +
            SetDB.tblPublication.COL_STATE    + " BOOLEAN)"

            db?.execSQL(createPublicationTable)

            val createPublicationPicturesTable:String = "CREATE TABLE " + SetDB.tblPublicationPictures.TABLE_NAME + "(" +
                    SetDB.tblPublicationPictures.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SetDB.tblPublicationPictures.COL_ID_IMAGE  + " INTEGER," +
            SetDB.tblPublicationPictures.COL_IMG   + " BLOB," +
            SetDB.tblPublicationPictures.COL_ID_PUBLICATION    + " INTEGER)"

            db?.execSQL(createPublicationPicturesTable)

            val createPlantTable:String = "CREATE TABLE " + SetDB.tblPlant.TABLE_NAME + "(" +
                    SetDB.tblPlant.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SetDB.tblPlant.COL_ID_USER  + " INTEGER," +
            SetDB.tblPlant.COL_NAME  + " VARCHAR(255)," +
            SetDB.tblPlant.COL_PLANTKIND  + " VARCHAR(255)," +
            SetDB.tblPlant.COL_IMG   + " BLOB," +
            SetDB.tblPlant.COL_FREQUENCY   + " INTEGER," +
            SetDB.tblPlant.COL_HOUR   + " DATETIME," +
            SetDB.tblPlant.COL_WATER_DATE   + " DATETIME," +
            SetDB.tblPlant.COL_WATERED_DATE   + " DATETIME)"

            db?.execSQL(createPlantTable)

            val createPublicationMarker: String = "CREATE TABLE " + SetDB.tblPublicationMarker.TABLE_NAME + "(" +
                    SetDB.tblPublicationMarker.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SetDB.tblPublicationMarker.COL_ID_PUBLICATION   + " INTEGER," +
                    SetDB.tblPublicationMarker.COL_ID_USER    + " INTEGER)"

            db?.execSQL(createPublicationMarker)

            Log.e("ENTRO","CREO TABLAS")

        }catch (e: Exception){
            Log.e("Execption", e.toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //db?.execSQL("DROP TABLE IF EXISTS " + SetDB.tblUser.TABLE_NAME);
        //Log.e("ENTRO","CREO TABLAS")
        // Create tables again
        //onCreate(db);
    }

    public fun insertUser(user:User):Boolean{

        val dataBase:SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        var boolResult:Boolean =  true

        values.put(SetDB.tblUser.COL_NAME,user.nombre)
        values.put(SetDB.tblUser.COL_LASTNAME,user.apellido)
        values.put(SetDB.tblUser.COL_EMAIL,user.correo)
        values.put(SetDB.tblUser.COL_PASSWORD,user.contra)
        values.put(SetDB.tblUser.COL_IMG,user.avatar)



        SetDB.tblUser.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SetDB.tblUser.COL_NAME  + " VARCHAR(255)," +
                SetDB.tblUser.COL_LASTNAME  + " VARCHAR(255)," +
                SetDB.tblUser.COL_EMAIL   + " VARCHAR(255)," +
                SetDB.tblUser.COL_PASSWORD   + " VARCHAR(255)," +
                SetDB.tblUser.COL_CREATION_DATE    + " DATE," +
                SetDB.tblUser.COL_UPDATE_DATE    + " DATE," +
                SetDB.tblUser.COL_IDIMAGE + " INTEGER," +
                SetDB.tblUser.COL_IMG + " BLOB)"
        try {
            val result =  dataBase.insert(SetDB.tblUser.TABLE_NAME, null, values)

            if (result == (0).toLong()) {
                Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
            }

        }catch (e: Exception){
            Log.e("Execption", e.toString())
            boolResult =  false
        }

        dataBase.close()

        return boolResult
    }

    public fun getUser(nameUser: String, pass: String):User?{
        var user:User? = null
        val dataBase:SQLiteDatabase = this.writableDatabase

        //QUE COLUMNAS QUEREMOS QUE ESTE EN EL SELECT
        val columns:Array<String> =  arrayOf(SetDB.tblUser.COL_ID,
                SetDB.tblUser.COL_EMAIL,
                SetDB.tblUser.COL_PASSWORD,
                SetDB.tblUser.COL_NAME,
                SetDB.tblUser.COL_LASTNAME,
                SetDB.tblUser.COL_IMG
                )

        val where:String =  SetDB.tblUser.COL_EMAIL + " = '${nameUser.toString()}' " +
                "AND " + SetDB.tblUser.COL_PASSWORD + " = '${pass.toString()}' "

        val data =  dataBase.query(SetDB.tblUser.TABLE_NAME,
                columns,
                where,
                null,
                null,
                null,
                SetDB.tblUser.COL_ID + " ASC")

        if(data.moveToFirst()){
            user = User()
            user.id = data.getString(data.getColumnIndex(SetDB.tblUser.COL_ID)).toInt()
            user.correo =  data.getString(data.getColumnIndex(SetDB.tblUser.COL_EMAIL)).toString()
            user.contra = data.getString(data.getColumnIndex(SetDB.tblUser.COL_PASSWORD)).toString()
            user.nombre = data.getString(data.getColumnIndex(SetDB.tblUser.COL_NAME)).toString()
            user.apellido = data.getString(data.getColumnIndex(SetDB.tblUser.COL_LASTNAME)).toString()
            user.avatar = data.getBlob(data.getColumnIndex(SetDB.tblUser.COL_IMG))

            Toast.makeText(this.context, "Bienvenido " + user.nombre, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this.context, "Ingrese un usuario o contraseña válidos" , Toast.LENGTH_SHORT).show()
        }

        data.close()
        return user
    }

    public fun insertPublication(pub:Publicaciones):Long{

        val dataBase:SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        var boolResult:Boolean =  true
        var result: Long ?=null

        values.put(SetDB.tblPublication.COL_IDUSER,pub.idCreador)
        values.put(SetDB.tblPublication.COL_DESCRIPTION,pub.descripcion)
        values.put(SetDB.tblPublication.COL_PLANTKIND, pub.tipo?.tipoNombre)
        values.put(SetDB.tblPublication.COL_STATE,pub.estado)
        values.put(SetDB.tblPublication.COL_TITLE,pub.titulo)



        SetDB.tblPublication.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SetDB.tblPublication.COL_DESCRIPTION  + " VARCHAR(65535)," +
                SetDB.tblPublication.COL_IDUSER  + " INT," +
                SetDB.tblPublication.COL_PLANTKIND   + " VARCHAR(255)," +
                SetDB.tblPublication.COL_STATE   + " BOOLEAN," +
                SetDB.tblPublication.COL_TITLE    + " VARCHAR(255))"
        try {
            result =  dataBase.insert(SetDB.tblPublication.TABLE_NAME, null, values)

            if (result == (0).toLong()) {
                Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
            }

        }catch (e: Exception){
            Log.e("Execption", e.toString())
            boolResult =  false
        }

        dataBase.close()

        return result!!
    }

    public fun getPublication(id: Int):Publicaciones?{
        var pub:Publicaciones? = null
        val dataBase:SQLiteDatabase = this.writableDatabase

        //QUE COLUMNAS QUEREMOS QUE ESTE EN EL SELECT
        val columns:Array<String> =  arrayOf(SetDB.tblPublication.COL_ID,
                SetDB.tblPublication.COL_TITLE,
                SetDB.tblPublication.COL_DESCRIPTION,
                SetDB.tblPublication.COL_IDUSER,
                SetDB.tblPublication.COL_PLANTKIND
        )

        val where:String =  SetDB.tblPublication.COL_ID + " = ${id.toString()} "

        val data =  dataBase.query(SetDB.tblPublication.TABLE_NAME,
                columns,
                where,
                null,
                null,
                null,
                SetDB.tblPublication.COL_ID + " ASC")

        if(data.moveToFirst()){
            pub = Publicaciones()
            pub.id = data.getString(data.getColumnIndex(SetDB.tblPublication.COL_ID)).toInt()
            pub.descripcion =  data.getString(data.getColumnIndex(SetDB.tblPublication.COL_DESCRIPTION)).toString()
            pub.idCreador = data.getString(data.getColumnIndex(SetDB.tblPublication.COL_IDUSER)).toInt()
            pub.tipo?.tipoNombre = data.getString(data.getColumnIndex(SetDB.tblPublication.COL_PLANTKIND)).toString()
            pub.estado = data.getString(data.getColumnIndex(SetDB.tblPublication.COL_STATE)).toString().toBoolean()
            pub.titulo = data.getString(data.getColumnIndex(SetDB.tblPublication.COL_TITLE)).toString()

            Toast.makeText(this.context, "Se encontro" + pub.titulo, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this.context, "No se encontro la publicación" , Toast.LENGTH_SHORT).show()
        }

        data.close()
        return pub
    }


    public fun insertImagePublication(imgPub:ImagenesPublicaciones):Boolean{

        val dataBase:SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        var boolResult:Boolean =  true

        values.put(SetDB.tblPublicationPictures.COL_ID_PUBLICATION,imgPub.idPublicacion)
        values.put(SetDB.tblPublicationPictures.COL_IMG,imgPub.imgArray)



        SetDB.tblUser.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SetDB.tblPublicationPictures.COL_ID_PUBLICATION  + " INT," +
                SetDB.tblPublicationPictures.COL_IMG + " BLOB)"
        try {
            val result =  dataBase.insert(SetDB.tblPublicationPictures.TABLE_NAME, null, values)

            if (result == (0).toLong()) {
                Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
            }

        }catch (e: Exception){
            Log.e("Execption", e.toString())
            boolResult =  false
        }

        dataBase.close()

        return boolResult
    }
}