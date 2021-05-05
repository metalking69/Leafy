package com.example.leafy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.leafy.Data.DataManager
import com.example.leafy.Data.ImagenesPublicaciones
import com.example.leafy.Data.Publicaciones
import com.example.leafy.R
import com.example.leafy.Utilities.ShowHideInterface
import com.synnapps.carouselview.CarouselView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetalles.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetalles : Fragment(), ShowHideInterface {
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
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_detalles, container, false)

        var bundle: Bundle?=this.arguments
        var str1: Int?=bundle?.getInt("idPlantaDetalle")
        //Toast.makeText(this.context, str1.toString(), Toast.LENGTH_SHORT).show()

        var planta: Publicaciones? = DataManager.publicaciones.find { it.id == str1 }
        var plantaImage=planta?.imgArray

        v.findViewById<TextView>(R.id.textView18).setText(planta?.descripcion)

        var imgs: ArrayList<ImagenesPublicaciones>? =null

        var con1=0

        val numbers = IntArray(3)
        DataManager.imagenesPublicaciones.forEach {
            //Toast.makeText(this.context, , Toast.LENGTH_SHORT).show()
            if(it.idPublicacion!! == str1){
                //Toast.makeText(this.context, "agregar", Toast.LENGTH_SHORT).show()
                imgs?.add(it)
                numbers.set(con1, it.idImg!!)
                con1++

            }
        }


        //val cantidad =con1

        //Toast.makeText(this.context, cantidad.toString(), Toast.LENGTH_SHORT).show()


        /*
        var flores = arrayOf(
            "girasol",
            "gerbera",
            "lirio"
        )*/


        var carouselView: CarouselView=v.findViewById(R.id.carousel)
        carouselView.pageCount=con1
        carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(numbers[position])
        }

        carouselView.setImageClickListener{position->
            //Toast.makeText(this.context, flores[position], Toast.LENGTH_SHORT).show()
        }


        val edtTitulo = v.findViewById<TextView>(R.id.textView15)
        edtTitulo.setText(planta?.titulo)

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
         * @return A new instance of fragment FragmentDetalles.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetalles().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()

        showSave(search!!, combobox!!, btnS1!!, btnS2!!, btnAdd!!)

    }
}