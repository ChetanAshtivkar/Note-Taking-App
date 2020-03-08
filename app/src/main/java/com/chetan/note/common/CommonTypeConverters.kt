package com.chetan.note.common

import androidx.room.TypeConverter
import java.util.*

class CommonTypeConverters {
    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time;
    }
}