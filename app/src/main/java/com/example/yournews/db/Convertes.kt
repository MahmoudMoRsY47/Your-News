package com.example.yournews.db

import androidx.room.TypeConverter
import com.example.yournews.model.Source


class Convertes {
    @TypeConverter
    fun fromSource(source : Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name :String):Source{
        return Source(name,name)
    }
}