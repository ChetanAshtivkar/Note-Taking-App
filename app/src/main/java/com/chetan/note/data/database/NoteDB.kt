package com.chetan.note.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chetan.note.data.Note

/**
 * Created by Chetan on 2020-03-08.
 */
private const val DATABASE_NAME = "note_db"

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDB : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDB? = null

        fun getInstance(context: Context): NoteDB? {
            if (INSTANCE == null) {
                synchronized(NoteDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDB::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}