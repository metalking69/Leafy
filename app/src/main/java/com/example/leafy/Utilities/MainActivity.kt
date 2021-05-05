package com.example.leafy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.Data.DataManager
import com.example.leafy.R
import com.example.leafy.Utilities.ActionListenerInterface
import com.example.leafy.Utilities.PublicacionRecyclerAdapter
import com.example.leafy.Utilities.RegistroActivity
import com.example.leafy.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener, androidx.appcompat.widget.SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener, ActionListenerInterface {
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


        firstFragment(FragmentHome())

        val nav=findViewById(R.id.bottom_navigation) as BottomNavigationView
        nav.setOnClickListener(this)

        val search=findViewById(R.id.idSearchV) as SearchView
        search.setOnClickListener(this)

        val addMisPlantas=findViewById(R.id.imageButton9) as ImageButton
        addMisPlantas.setOnClickListener(this)

        labelMain=findViewById(R.id.textView10) as TextView

        nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> {
                    makeCurrentFragment(FragmentHome())

                    EnDisableSearchView(2)
                    var str: String= getString(R.string.app_name)
                    labelMain?.text=str.toString()
                }
                R.id.ic_saved -> {
                    makeCurrentFragment(FragmentGuardados())

                    EnDisableSearchView(2)
                    var str: String=getString(R.string.guardados)
                    labelMain?.text=str.toString()
                }
                R.id.ic_new -> {
                    makeCurrentFragment(FragmentNew())

                    EnDisableSearchView(1)
                    var str: String=getString(R.string.crear_publicacion)
                    labelMain?.text=str.toString()
                }
                R.id.ic_my_plants -> {
                    makeCurrentFragment(FragmentMisPlantas())

                    EnDisableSearchView(1)
                    var str: String=getString(R.string.mis_plantas)
                    labelMain?.text=str.toString()
                }
                R.id.ic_profile -> {
                    makeCurrentFragment(FragmentPerfil())

                    EnDisableSearchView(1)
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

    private fun firstFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.idFragmentLay, fragment)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()

    }




    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.imageButton9->{
                makeCurrentFragment(FragmentAgregarPlanta())
                var str: String=getString(R.string.agregar_planta)
                labelMain?.text=str.toString()
                //EnDisableAddButton(1)
            }

        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    override fun onClickFragmentButton(buttonId: Int, plantaId: Int?) {
        when(buttonId){
            R.id.idFrameLayoutCard -> {
                //makeCurrentFragment(FragmentDetalles())
                //intent.putExtra("idPlanta", plantaId)
                //startActivity(intent)
                val bundle=Bundle()
                if (plantaId != null) {
                    bundle?.putInt("idPlantaDetalle", plantaId)
                }
                val fragmentDetalles: Fragment? = FragmentDetalles()
                fragmentDetalles?.arguments=bundle
                makeCurrentFragment(fragmentDetalles!!)
                //makeCurrentFragment(FragmentDetalles())
            }
            R.id.button7->{
                /*val intent= Intent(this, RegistroActivity::class.java)
                intent.putExtra("editar", true)
                startActivity(intent)
                 */
                makeCurrentFragment(FragmentEditarPerfil())
                var str: String=getString(R.string.editar_perfil)
                labelMain?.text=str.toString()
            }
            R.id.button3->{
                makeCurrentFragment(FragmentMisPublicaciones())
                var str: String=getString(R.string.mis_publicaciones)
                labelMain?.text=str.toString()
            }

        }
    }

    private fun EnDisableSearchView(opcion: Int){

        var searchView=findViewById<SearchView>(R.id.idSearchV)
        if(opcion==1){
            searchView.isEnabled = false
            searchView.isClickable = false
            searchView.visibility = INVISIBLE
        }
        else if (opcion==2){
            searchView.isEnabled = true
            searchView.isClickable = true
            searchView.visibility = VISIBLE
        }

    }



}