package com.example.leafy.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.leafy.CAMERA_CODE
import com.example.leafy.IMAGE_PICK_CODE
import com.example.leafy.PERMISSION_CODE
import com.example.leafy.R
import com.example.leafy.Utilities.ShowHideInterface
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEditarPerfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEditarPerfil : Fragment(), ShowHideInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var search: SearchView?=null
    var combobox: Spinner?=null
    var btnS1: ImageButton?=null
    var btnS2: ImageButton?=null
    var btnAdd: ImageButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        val btnPopup= v.findViewById<TextView>(R.id.textView22)
        val popupMenu= PopupMenu(context, btnPopup)

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

        btnAdd = activity?.findViewById(R.id.imageButton9) as ImageButton
        btnS1 = activity?.findViewById(R.id.imageButton13) as ImageButton
        btnS2 = activity?.findViewById(R.id.imageButton14) as ImageButton
        search = activity?.findViewById(R.id.idSearchV) as SearchView
        combobox = activity?.findViewById(R.id.spinner4) as Spinner

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentEditarPerfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentEditarPerfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun camera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_CODE)
    }

    private fun changeImage() {
        //check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var boolDo: Boolean = false
            if (context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
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
        //Abrir la galería
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

            val imgViewPic= view?.findViewById<ImageView>(R.id.imageView10)
            //RESPUESTA DE LA CÁMARA CONTIENE LA IMAGEN
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
                imgViewPic?.setImageURI(data?.data)
                val bitmap = (imgViewPic?.getDrawable() as BitmapDrawable).bitmap
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
                        context,
                        getString(R.string.Permission_denied).toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()

        Hide5(search!!, combobox!!, btnAdd!!, btnS1!!, btnS2!!)

    }

}