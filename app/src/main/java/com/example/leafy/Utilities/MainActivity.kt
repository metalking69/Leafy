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


        nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(FragmentHome())
                R.id.ic_saved -> makeCurrentFragment(FragmentGuardados())
                R.id.ic_new -> makeCurrentFragment(FragmentNew())
                R.id.ic_my_plants -> makeCurrentFragment(FragmentMisPlantas())
                R.id.ic_profile -> makeCurrentFragment(FragmentPerfil())
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