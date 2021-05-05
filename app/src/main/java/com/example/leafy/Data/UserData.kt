package com.example.leafy.Data

class User(var id:Int?=null, var nombre:String?=null, var apellido:String?=null, var correo:String?=null,
           var contra:String?=null, var avatar:ByteArray?= null)


class MisPlantas(var idUser:Int?, var idPlantas: Int?, var progressValue: Int?)