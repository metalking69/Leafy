package com.example.leafy.fragments

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.Data.DataManager
import com.example.leafy.Data.Example
import com.example.leafy.R
import com.example.leafy.Utilities.PublicacionRecyclerAdapter
import java.util.prefs.Preferences

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var myRecyclerView: RecyclerView? = null
    //private var listExample2=ArrayList<Example>()

    var btnSave1: ImageButton?= null
    var btnSave2: ImageButton?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
        DataManager.content =  this.requireContext()




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v:View=inflater.inflate(R.layout.fragment_home, container, false)
        myRecyclerView = v.findViewById(R.id.rcListPublic)
        val recyclerAdapter: PublicacionRecyclerAdapter=PublicacionRecyclerAdapter(requireContext(),DataManager.publicaciones )
        myRecyclerView?.layoutManager=LinearLayoutManager(activity)
        myRecyclerView?.adapter=recyclerAdapter

        /*
        btnSave1=v.findViewById<ImageButton>(R.id.imageButton)
        btnSave1?.setOnClickListener(this)
        btnSave2=v.findViewById<ImageButton>(R.id.imageButton2)
        btnSave2?.setOnClickListener(this)*/




        return v
        /*
        val constraintLayout = rootPro?.findViewById(R.id.clFragmentHome) as ConstraintLayout
        val button = Button(this.requireContext())
        button.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        button.text = "Click me"
        button.setOnClickListener(View.OnClickListener {
            button.text = "You just clicked me"
        })
        button.setBackgroundColor(Color.GREEN)
        button.setTextColor(Color.RED)
        constraintLayout.addView(button);*/

        // Inflate the layout for this fragment

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.imageButton -> {

            }
            R.id.imageButton2-> {

            }
        }
    }


}