package com.example.leafy

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.Data.DataManager
import com.example.leafy.R
import com.example.leafy.Utilities.PublicacionRecyclerAdapter
import com.example.leafy.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener, androidx.appcompat.widget.SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener {
    /*var homeFragment:Fragment = FragmentHome()
    var guardadosFragment:Fragment = FragmentGuardados()
    var crearFragment:Fragment = FragmentNew()
    var plantasFragment:Fragment = FragmentMisPlantas()
    var perfilFragment:Fragment = FragmentPerfil()*/
    var labelMain: TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DataManager.content =  this
        /*
        val hF=FragmentHome()
        val sF=FragmentGuardados()
        val nF=FragmentNew()
        val mpF=FragmentMisPlantas()
        val pF=FragmentMisPlantas()*/


        makeCurrentFragment(FragmentHome())

        val nav=findViewById(R.id.bottom_navigation) as BottomNavigationView
        nav.setOnClickListener(this)

        val search=findViewById(R.id.idSearchV) as SearchView
        search.setOnClickListener(this)

        labelMain=findViewById(R.id.textView10) as TextView

        nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> {
                    makeCurrentFragment(FragmentHome())

                    var str: String= getString(R.string.app_name)
                    labelMain?.text=str.toString()
                }
                R.id.ic_saved -> {
                    makeCurrentFragment(FragmentGuardados())

                    var str: String=getString(R.string.guardados)
                    labelMain?.text=str.toString()
                }
                R.id.ic_new -> {
                    makeCurrentFragment(FragmentNew())

                    var str: String=getString(R.string.crear_publicacion)
                    labelMain?.text=str.toString()
                }
                R.id.ic_my_plants -> {
                    makeCurrentFragment(FragmentMisPlantas())

                    var str: String=getString(R.string.mis_plantas)
                    labelMain?.text=str.toString()
                }
                R.id.ic_profile -> {
                    makeCurrentFragment(FragmentPerfil())

                    var str: String=getString(R.string.mi_perfil)
                    labelMain?.text=str.toString()
                }
            }
            true
        }



        search.setOnQueryTextListener(this)
    }

    /*private fun makeCurrentFragment(fragment: Fragment)=
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.idFragment, fragment)
        addToBackStack(null)
        commit()
    }*/
    private fun makeCurrentFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.idFragmentLay, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()

    }




    override fun onClick(v: View?) {
        when(v!!.id){
            //R.id.ic_home->makeCurrentFragment(homeFragment)

            /*
            R.id.ic_home->makeCurrentFragment(homeFragment)
            R.id.ic_saved -> makeCurrentFragment(guardadosFragment)
            R.id.ic_new -> makeCurrentFragment(crearFragment)
            R.id.ic_my_plants -> makeCurrentFragment(plantasFragment)
            R.id.ic_profile -> makeCurrentFragment(perfilFragment)*/

            /*
            R.id.ic_home->makeCurrentFragment(FragmentHome())
            R.id.ic_saved -> makeCurrentFragment(FragmentGuardados())
            R.id.ic_new -> makeCurrentFragment(FragmentNew())
            R.id.ic_my_plants -> makeCurrentFragment(FragmentMisPlantas())
            R.id.ic_profile -> makeCurrentFragment(FragmentPerfil())*/


        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

}