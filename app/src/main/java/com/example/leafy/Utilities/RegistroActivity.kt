package com.example.leafy.Utilities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.leafy.CAMERA_CODE
import com.example.leafy.Data.DataManager
import com.example.leafy.Data.User
import com.example.leafy.IMAGE_PICK_CODE
import com.example.leafy.PERMISSION_CODE
import com.example.leafy.R
import java.io.ByteArrayOutputStream

class RegistroActivity: AppCompatActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val modoEditar=savedInstanceState?.getInt("editar")?: intent.getBooleanExtra("editar", false)
        if (modoEditar==true){
            val textview=findViewById<TextView>(R.id.textView)
            textview.text=getString(R.string.editar_perfil)
            val buttontext=findViewById<Button>(R.id.button2)
            buttontext.text=getString(R.string.editar_perfil)
        }

        val btnPopup= findViewById<TextView>(R.id.textView4)
        val popupMenu= PopupMenu(this, btnPopup)

        val btnRegistrar = findViewById<Button>(R.id.button2)
        btnRegistrar.setOnClickListener(this)

        //inflar el popup con los items del menu
        popupMenu.menuInflater.inflate(R.menu.menu_image_change, popupMenu.menu)

        //manejar los clicks
        popupMenu.setOnMenuItemClickListener { menuItem->

            val id = menuItem.itemId

            if(id== R.id.menu_abrir_camara){
                //showToast("camara")
                camera()

            }
            else if(id== R.id.menu_abrir_galeria){
                //showToast("galeria")
                changeImage()
            }
            //else if(id==R.id.menu_cancelar){
                //showToast("cancelar")
            //}

            false
        }

        btnPopup.setOnClickListener{
            popupMenu.show()
        }

    }


    private fun showToast(title: CharSequence?){
        Toast.makeText(this, "You clicked: "+title, Toast.LENGTH_SHORT).show()
    }

    private fun camera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_CODE)
    }

    private fun changeImage() {
        //check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var boolDo: Boolean = false
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED

            ) {
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                //show popup to request runtime permission
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                //permission already granted
                boolDo = true

            }


            if (boolDo == true) {
                pickImageFromGallery()
            }

        }

    }

    private fun pickImageFromGallery(){
        //Abrir la galer??a
        val intent = Intent()
        intent.setAction(Intent.ACTION_PICK);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.type = "image/*"
        //startActivityForResult(Intent.createChooser(intent,"Selecciona"), IMAGE_PICK_CODE)
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            val imgViewPic= findViewById<ImageView>(R.id.imageView2)
            //RESPUESTA DE LA C??MARA CONTIENE LA IMAGEN
            if (requestCode == CAMERA_CODE) {

                val photo = data?.extras?.get("data") as Bitmap
                val stream = ByteArrayOutputStream()

                //Bitmap.CompressFormat agregar el formato desado, estoy usando aqui jpeg
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream)

                //Mostramos la imagen en la vista

                //val albumEdit: Album = DataManager.albums[albumPosition]
                //albumEdit.imgArray = stream.toByteArray()

                imgViewPic!!.setImageBitmap(photo)

            }
            if (requestCode == IMAGE_PICK_CODE) {
                imgViewPic.setImageURI(data?.data)
                val bitmap = (imgViewPic.getDrawable() as BitmapDrawable).bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                //val albumEdit: Album = DataManager.albums[albumPosition]
                //albumEdit.imgArray = baos.toByteArray()
                imgViewPic!!.setImageBitmap(bitmap)
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //PERMISO DENEGADO
                    Toast.makeText(
                            this,
                            getString(R.string.Permission_denied).toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button2->this.saveUser()
        }
    }

    private fun saveUser() {
        var user: User =  User()

        //user =  DataManager.usuarios[DataManager.usuarios.lastIndexOf(DataManager.usuarios)]

        var correo= this.findViewById<EditText>(R.id.editTextTextEmailAddress)
        var nombre= this.findViewById<EditText>(R.id.editTextTextPersonName)
        var apellido= this.findViewById<EditText>(R.id.editTextTextPersonName2)
        var contrase??a= this.findViewById<EditText>(R.id.editTextTextPassword)
        var imgView = this.findViewById<ImageView>(R.id.imageView2)

        user.correo = correo.text.toString()
        user.contra =  contrase??a.text.toString()
        user.apellido = apellido.text.toString()
        user.nombre =  nombre.text.toString()
        user.id = DataManager.usuarios.lastIndexOf(DataManager.usuarios)+1




        val bitmap = (imgView.getDrawable() as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        user.avatar = baos.toByteArray()

        UserApplication.dbHelper.insertUser(user)



        //UserApplication.dbHelper.insertAlbum(user)


    }

}