package com.chetan.note.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chetan.note.common.CommonTypeConverters
import java.util.*

/**
 * Created by Chetan on 2020-03-08.
 */
@Entity(tableName = "note")
@TypeConverters(CommonTypeConverters::class)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var contents: String,
    var createDate: Date,
    var updateDate: Date
)