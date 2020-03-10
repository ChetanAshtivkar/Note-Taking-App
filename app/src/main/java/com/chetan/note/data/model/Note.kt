package com.chetan.note.data.model

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
    var id: Int = 0,
    var title: String? = "",
    var contents: String? = "",
    var createDate: Date? = null,
    var updateDate: Date? = null
)