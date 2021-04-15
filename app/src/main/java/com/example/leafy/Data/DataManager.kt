package com.example.leafy.Data

import android.content.Context
import com.example.leafy.R

object DataManager {
    val usuarios = ArrayList<User>()
    val tipos_plantas = ArrayList<TiposPlantas>()
    val publicaciones = ArrayList<Publicaciones>()
    val mis_plantas = ArrayList<MisPlantas>()
    val ejemplos = ArrayList<Example>()
    var content: Context? = null

    init {
        this.initializeUsers()
        this.initializeTipos()
        this.initializePublicaciones()
        this.initializeMisPlantas()
        this.initializeEjemplos()
    }

    private fun initializeMisPlantas() {
        var plant= MisPlantas(1, 1, 8)
        mis_plantas.add(plant)

        plant= MisPlantas(1, 2, 2)
        mis_plantas.add(plant)

        plant= MisPlantas(1, 3, 10)
        mis_plantas.add(plant)

        plant= MisPlantas(1, 2, 5)
        mis_plantas.add(plant)
    }


    private fun initializeTipos() {
        var tipo = TiposPlantas(1, "Árbol")
        tipos_plantas.add(tipo)

        tipo = TiposPlantas(2, "Planta con Flores")
        tipos_plantas.add(tipo)

        tipo = TiposPlantas(3, "Planta sin Flores")
        tipos_plantas.add(tipo)

    }

    private fun initializePublicaciones() {
        var pub = Publicaciones(1, "Girasol", "Helianthus annuus, llamado comúnmente girasol es una planta herbácea anual de la familia de las asteráceas originaria de Centro y Norteamérica y cultivada como alimenticia, oleaginosa y ornamental en todo el mundo.",
        R.drawable.gira, R.drawable.p1, tipos_plantas[2])
        publicaciones.add(pub)

        pub = Publicaciones(2, "Lirio", "es un género de plantas ornamentales de la familia Asteraceae. Comprende unas 150 especies descritas y de estas, solo 38 aceptadas. Las gerberas también es llamada margarita africana. ",
            R.drawable.lirio, R.drawable.p1, tipos_plantas[2])
        publicaciones.add(pub)

        pub = Publicaciones(3, "Gerbera", "Por lirio se hace referencia a las plantas del género Iris y otros géneros similares de la misma familia de las iridáceas.",
            R.drawable.gerbera, R.drawable.p1, tipos_plantas[2])
        publicaciones.add(pub)

    }


    private fun initializeUsers() {
        var usr=User(1, "Carlos", "López", "demoon_12@homtail.com", "a1b2c3", null)
        usuarios.add(usr)
        usr=User(2, "Paola", "Sauceda", "zay98@gmail.com", "a1b2c3", null)
        usuarios.add(usr)
        usr=User(3, "Elizabeth", "Solis", "eliza.99@homtail.com", "a1b2c3", null)
        usuarios.add(usr)

    }

    private fun initializeEjemplos() {
        var ejemplo = Example(1, "Zayra", "Sauceda")
        ejemplos.add(ejemplo)

        ejemplo = Example(1, "Carlos", "López")
        ejemplos.add(ejemplo)

        ejemplo = Example(1, "Maggie", "Solis")
        ejemplos.add(ejemplo)

        ejemplo = Example(1, "Bnet", "God")
        ejemplos.add(ejemplo)
    }

}