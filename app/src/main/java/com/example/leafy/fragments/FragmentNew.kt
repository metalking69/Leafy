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
import com.example.leafy.CAMERA_CODE
import com.example.leafy.IMAGE_PICK_CODE
import com.example.leafy.PERMISSION_CODE
import com.example.leafy.R
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentNew.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNew : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var viewFr: View?=null
    private var numImagen: Int?= null

    var btnSave1: ImageButton?=null
    var btnSave2: ImageButton?=null
    var btnSave3: ImageButton?=null


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
        // Inflate the layout for this fragment
        viewFr=inflater.inflate(R.layout.fragment_new, container, false)

        btnSave1=viewFr?.findViewById(R.id.imageButton3)
        btnSave2=viewFr?.findViewById(R.id.imageButton5)
        btnSave3=viewFr?.findViewById(R.id.imageButton6)
        //var img1: ImageButton= v.findViewById(R.id.imageButton3)

        val btnPopup= viewFr?.findViewById<ImageButton>(R.id.imageButton3)
        val popupMenu= PopupMenu(this.context, btnPopup)

        val btnPopup2= viewFr?.findViewById<ImageButton>(R.id.imageButton5)
        val popupMenu2= PopupMenu(this.context, btnPopup2)

        val btnPopup3= viewFr?.findViewById<ImageButton>(R.id.imageButton6)
        val popupMenu3= PopupMenu(this.context, btnPopup3)

        //inflar el popup con los items del menu
        popupMenu.menuInflater.inflate(R.menu.menu_image_change, popupMenu.menu)
        popupMenu2.menuInflater.inflate(R.menu.menu_image_change, popupMenu2.menu)
        popupMenu3.menuInflater.inflate(R.menu.menu_image_change, popupMenu3.menu)

        //manejar los clicks
        popupMenu.setOnMenuItemClickListener { menuItem->

            val id = menuItem.itemId

            if(id== R.id.menu_abrir_camara){
                //showToast("camara")
                //Toast.makeText(this.context, "Crear->Camara", Toast.LENGTH_SHORT).show()
                numImagen=1
                camera()

            }
            else if(id== R.id.menu_abrir_galeria){
                //showToast("galeria")
                //Toast.makeText(this.context, "Crear->Galería", Toast.LENGTH_SHORT).show()
                numImagen=1
                changeImage()
            }

            false
        }

        //manejar los clicks
        popupMenu2.setOnMenuItemClickListener { menuItem->

            val id = menuItem.itemId

            if(id== R.id.menu_abrir_camara){
                //showToast("camara")
                //Toast.makeText(this.context, "Crear->Camara", Toast.LENGTH_SHORT).show()
                numImagen=2
                camera()

            }
            else if(id== R.id.menu_abrir_galeria){
                //showToast("galeria")
                //Toast.makeText(this.context, "Crear->Galería", Toast.LENGTH_SHORT).show()
                numImagen=2
                changeImage()
            }

            false
        }

        //manejar los clicks
        popupMenu3.setOnMenuItemClickListener { menuItem->

            val id = menuItem.itemId

            if(id== R.id.menu_abrir_camara){
                //showToast("camara")
                //Toast.makeText(this.context, "Crear->Camara", Toast.LENGTH_SHORT).show()
                numImagen=3
                camera()

            }
            else if(id== R.id.menu_abrir_galeria){
                //showToast("galeria")
                //Toast.makeText(this.context, "Crear->Galería", Toast.LENGTH_SHORT).show()
                numImagen=3
                changeImage()
            }

            false
        }

        btnPopup?.setOnClickListener{
            popupMenu.show()
        }

        btnPopup2?.setOnClickListener{
            popupMenu2.show()
        }
        btnPopup3?.setOnClickListener{
            popupMenu3.show()
        }

        return viewFr
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentNew.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentNew().apply {
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
            if (this.context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
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


            var imgViewPic: ImageView?= null

            if(numImagen==1){
                imgViewPic= viewFr?.findViewById<ImageView>(R.id.imageView5)
            }
            else if(numImagen==2){
                imgViewPic= viewFr?.findViewById<ImageView>(R.id.imageView6)
            }
            else if(numImagen==3){
                imgViewPic= viewFr?.findViewById<ImageView>(R.id.imageView7)
            }

            //RESPUESTA DE LA CÁMARA CONTIENE LA IMAGEN
            if (requestCode == CAMERA_CODE) {

                val photo = data?.extras?.get("data") as Bitmap
                val stream = ByteArrayOutputStream()

                //Bitmap.CompressFormat agregar el formato desado, estoy usando aqui jpeg
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream)

                //Mostramos la imagen en la vista

                //val albumEdit: Album = DataManager.albums[albumPosition]
                //albumEdit.imgArray = stream.toByteArray()

                if(numImagen==1){
                    btnSave2?.isEnabled = true
                    btnSave2?.isClickable = true
                    btnSave2?.visibility= View.VISIBLE

                }
                else if(numImagen==2){
                    btnSave3?.isEnabled = true
                    btnSave3?.isClickable = true
                    btnSave3?.visibility= View.VISIBLE
                }

                imgViewPic!!.setImageBitmap(photo)

            }
            if (requestCode == IMAGE_PICK_CODE) {
                imgViewPic?.setImageURI(data?.data)
                val bitmap = (imgViewPic?.getDrawable() as BitmapDrawable).bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                //val albumEdit: Album = DataManager.albums[albumPosition]
                //albumEdit.imgArray = baos.toByteArray()

                if(numImagen==1){
                    btnSave2?.isEnabled = true
                    btnSave2?.isClickable = true
                    btnSave2?.visibility= View.VISIBLE
                }
                else if(numImagen==2){
                    btnSave3?.isEnabled = true
                    btnSave3?.isClickable = true
                    btnSave3?.visibility= View.VISIBLE
                }

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
                            this.context,
                            getString(R.string.Permission_denied).toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

}