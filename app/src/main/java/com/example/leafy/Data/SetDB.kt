package com.example.leafy.Data

class SetDB {

    companion object{
        val DB_NAME =  "bdProjectLeafy"
        val DB_VERSION =  1

        var USER_IMG: ByteArray? = null
        var USER_NAME: String ? = null
        var USER_L_NAME: String ? = null
        var USER_EMAIL: String ? = null
        var USER_ID: Int ?= null
    }

    abstract class tblUser{
        companion object{
            val TABLE_NAME = "User"
            val COL_ID =  "intID"
            val COL_NAME =  "strName"
            val COL_LASTNAME = "strLastName"
            val COL_EMAIL =  "strEmail"
            val COL_PASSWORD = "strPassword"
            val COL_CREATION_DATE = "dateTimeCreation"
            val COL_UPDATE_DATE = "dateTimeUpdate"
            val COL_IDIMAGE =  "intIdImage"
            val COL_IMG =  "imgArray" // byte Array image
        }
    }

    abstract class tblPublication{
        companion object{
            val TABLE_NAME = "Publication"
            val COL_ID =  "intID"
            val COL_TITLE =  "strTitle"
            val COL_DESCRIPTION = "strDescription"
            val COL_IDUSER =  "intIdUser"
            val COL_PLANTKIND = "strPlantKind"
            val COL_STATE = "boolState"
        }
    }

    abstract class tblPublicationPictures{
        companion object{
            val TABLE_NAME = "PublicationPictures"
            val COL_ID = "intID"
            val COL_ID_IMAGE =  "intIdImage"
            val COL_IMG =  "imgArray"
            val COL_ID_PUBLICATION = "intIdPublication"
        }
    }

    abstract class tblPlant{
        companion object{
            val TABLE_NAME = "Plant"
            val COL_ID = "intID"
            val COL_ID_USER = "intIdUser"
            val COL_NAME = "strName"
            val COL_PLANTKIND = "strPlantKind"
            val COL_IMG =  "imgArray"
            val COL_FREQUENCY = "intFrequency"
            val COL_HOUR = "hour"
            val COL_WATER_DATE = "dateWater"
            val COL_WATERED_DATE = "dateWatered"
        }
    }

    abstract class tblPublicationMarker{
        companion object{
            val TABLE_NAME = "PublicationMarker"
            val COL_ID = "intID"
            val COL_ID_PUBLICATION = "intIdPublication"
            val COL_ID_USER = "intIdUser"
        }
    }
}