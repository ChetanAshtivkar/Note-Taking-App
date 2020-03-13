package com.chetan.note.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chetan.note.data.model.Note
import com.chetan.note.ui.editor.NoteResult

/**
 * Created by Chetan on 08/03/20.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)

    @Query("SELECT COUNT(*) FROM note")
    fun getCount(): Int

    @Transaction
    fun addOrUpdate(note: Note): NoteResult {
        try {
            val id = insert(note).toInt()
            if (id == -1) {
                update(note)
            } else {
                note.id = id
            }
        } catch (e: Exception) {
            return NoteResult.Failure(e.toString())
        }
        return NoteResult.Success(note)
    }
}