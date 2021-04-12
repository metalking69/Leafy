package com.example.leafy.Data


class TiposPlantas(var intId:Int, var tipoNombre:String){

    override fun toString(): String {
        return this.tipoNombre
    }
}

class Publicaciones(var id:Int?, var titulo:String?=null, var descripcion:String?=null, var idImg1:Int?= null, var idImgPerfil:Int?= null,  var tipo:TiposPlantas?=null, var imgArray:ByteArray? = null, var imgArrayPerfil:ByteArray? = null)