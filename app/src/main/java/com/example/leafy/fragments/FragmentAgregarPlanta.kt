package com.example.leafy.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.*
import com.example.leafy.R
import com.example.leafy.Utilities.ShowHideInterface

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAgregarPlanta.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAgregarPlanta : Fragment(), ShowHideInterface {
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
        val v=inflater.inflate(R.layout.fragment_agregar_planta, container, false)

        /*
        val adapterGenre =  ArrayAdapter<Genre>(this,android.R.layout.simple_spinner_item, DataManager.genres)
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenre.adapter =  adapterGenre*/

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
         * @return A new instance of fragment FragmentAgregarPlanta.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAgregarPlanta().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)



    }

    override fun onDetach() {
        super.onDetach()




    }

    override fun onStart() {
        super.onStart()
        Hide5(search!!, combobox!!, btnAdd!!, btnS1!!, btnS2!!)


    }

    override fun onPause() {
        super.onPause()

    }

}