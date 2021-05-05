package com.example.leafy.Data


class TiposPlantas(var intId:Int, var tipoNombre:String){

    override fun toString(): String {
        return this.tipoNombre
    }
}

class Publicaciones(
    var id:Int?=null,
    var titulo:String?=null,
    var descripcion:String?=null,
    var idImg1:Int?= null,
    var idImgPerfil:Int?= null,
    var tipo:TiposPlantas?=null,
    var imgArray:ByteArray? = null,
    var imgArrayPerfil:ByteArray? = null,
    var idCreador: Int? = null,
    var estado: Boolean?=null)

class ImagenesPublicaciones(
    var id:Int?=null,
    var idPublicacion: Int?=null,
    var idImg:Int?=null,
    var imgArray:ByteArray? = null
)

class Periodo(
    var id:Int,
    var periodo:String,
    var periodoInt: Int
)

class Horario(
    var id: Int,
    var horario: String,
    var horarioInt: Int
)