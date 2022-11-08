package com.sabdio.workoutlog.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun listToString(listX:List<String>):String{
//        var  outputString = ""
//        listX.forEach{ item->
//            outputString+="$item"
//        }
//        return  outputString
        return listX.joinToString(",")
    }
    @TypeConverter
    fun stringToList(stringX:String): List<String>{
        return  stringX.split(",")
    }
}