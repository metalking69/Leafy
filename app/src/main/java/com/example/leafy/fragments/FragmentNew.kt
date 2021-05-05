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
import com.example.leafy.Data.*
import com.example.leafy.Data.SetDB.Companion.USER_ID
import com.example.leafy.IMAGE_PICK_CODE
import com.example.leafy.PERMISSION_CODE
import com.example.leafy.R
import com.example.leafy.Utilities.ShowHideInterface
import com.example.leafy.Utilities.UserApplication
import com.google.android.material.textfield.TextInputEditText
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
class FragmentNew : Fragment(), ShowHideInterface, View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var search: SearchView?=null
    var combobox: Spinner?=null
    var btnS1: ImageButton?=null
    var btnS2: ImageButton?=null
    var btnAdd: ImageButton?=null

    private var viewFr: View?=null
    private var numImagen: Int?= null

    var btnImg1: ImageButton?=null
    var btnImg2: ImageButton?=null
    var btnImg3: ImageButton?=null

    var btnSaveBorrador: Button?= null
    var btnSavePublicacion: Button?= null

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

        btnImg1=viewFr?.findViewById(R.id.imageButton3)
        btnImg2=viewFr?.findViewById(R.id.imageButton5)
        btnImg3=viewFr?.findViewById(R.id.imageButton6)
        btnSaveBorrador=viewFr?.findViewById(R.id.button4)
        btnSavePublicacion=viewFr?.findViewById(R.id.button6)
        btnSaveBorrador?.setOnClickListener(this)
        btnSavePublicacion?.setOnClickListener(this)
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

        btnAdd = activity?.findViewById(R.id.imageButton9) as ImageButton
        btnS1 = activity?.findViewById(R.id.imageButton13) as ImageButton
        btnS2 = activity?.findViewById(R.id.imageButton14) as ImageButton
        search = activity?.findViewById(R.id.idSearchV) as SearchView
        combobox = activity?.findViewById(R.id.spinner4) as Spinner

        val combobox2 = viewFr?.findViewById(R.id.spinner3) as Spinner
        val adapterTipos =
                ArrayAdapter<TiposPlantas>(context!!, android.R.layout.simple_spinner_dropdown_item, DataManager.tipos_plantas)
        combobox2!!.adapter=adapterTipos

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
                    btnImg2?.isEnabled = true
                    btnImg2?.isClickable = true
                    btnImg2?.visibility= View.VISIBLE

                }
                else if(numImagen==2){
                    btnImg3?.isEnabled = true
                    btnImg3?.isClickable = true
                    btnImg3?.visibility= View.VISIBLE
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
                    btnImg2?.isEnabled = true
                    btnImg2?.isClickable = true
                    btnImg2?.visibility= View.VISIBLE
                }
                else if(numImagen==2){
                    btnImg3?.isEnabled = true
                    btnImg3?.isClickable = true
                    btnImg3?.visibility= View.VISIBLE
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

    override fun onStart() {
        super.onStart()

        Hide5(search!!, combobox!!, btnAdd!!, btnS1!!, btnS2!!)

    }

    override fun onClick(v: View?) {
        when(v!!.id){

            R.id.button4->this.savePublication(1)
            R.id.button6->this.savePublication(2)
        }
    }

    private fun savePublication(opc: Int) {


        //user =  DataManager.usuarios[DataManager.usuarios.lastIndexOf(DataManager.usuarios)]

        var titulo=  viewFr!!.findViewById<EditText>(R.id.editTextTextEmailAddress2)
        var descripcion= viewFr!!.findViewById<TextInputEditText>(R.id.textInputEditText)
        var id= 1
        var tipo= viewFr!!.findViewById<Spinner>(R.id.spinner3)
        var estado: Boolean=false
        var imgView1= viewFr!!.findViewById<ImageView>(R.id.imageView5)
        var imgView2= viewFr!!.findViewById<ImageView>(R.id.imageView6)
        var imgView3= viewFr!!.findViewById<ImageView>(R.id.imageView7)

        if(imgView1.getDrawable() == null || titulo.text.isEmpty() || titulo.text.isNullOrBlank() ||
                descripcion.text?.isEmpty()!! ||  descripcion.text?.isNullOrBlank()!! ){
            Toast.makeText(this.context, "Ingrese todos los datos por favor", Toast.LENGTH_SHORT).show()
        }
        else{
            if(opc==1){
                estado = false
            }
            else{
                estado = true
            }
            var pub: Publicaciones =  Publicaciones()
            pub.titulo = titulo.text.toString()
            pub.descripcion =  descripcion.text.toString()
            pub.tipo = tipo.selectedItem as TiposPlantas
            pub.estado =  estado
            //pub.id = DataManager.publicaciones.lastIndexOf(DataManager.publicaciones)+1
            pub.idCreador = USER_ID

            var count = 1
            if(imgView2.getDrawable() != null && imgView3.getDrawable() != null)
                count=3
            else if(imgView2.getDrawable() != null)
                count=2
            var idCreated = UserApplication.dbHelper.insertPublication(pub).toInt()
            for(i in 1..count){
                if(i==1)
                    saveImages(imgView1, idCreated)
                else if(i==2)
                    saveImages(imgView2, idCreated)
                else if(i==3)
                    saveImages(imgView3, idCreated)
                Toast.makeText(this.context, "imagen guardada", Toast.LENGTH_SHORT).show()
            }

        }






        /*



        */

        //UserApplication.dbHelper.insertUser(user)
    }

    private fun saveImages(imageV: ImageView, id: Int){
        var imgPub: ImagenesPublicaciones =  ImagenesPublicaciones()
        val bitmap = (imageV.getDrawable() as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        imgPub.imgArray = baos.toByteArray()
        //imgPub.id = DataManager.imagenesPublicaciones.lastIndexOf(DataManager.imagenesPublicaciones)+1
        imgPub.idPublicacion=id

        UserApplication.dbHelper.insertImagePublication(imgPub)
    }
}