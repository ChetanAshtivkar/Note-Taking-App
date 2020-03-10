package com.chetan.note.data

import androidx.lifecycle.MutableLiveData
import com.chetan.note.data.database.NoteDao
import com.chetan.note.data.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Chetan on 10/03/20.
 */
class NoteRepository(private val noteDao: NoteDao?) {

    fun saveNote(note: Note): MutableLiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        CoroutineScope(Dispatchers.IO).launch {
            data.postValue(noteDao?.addOrUpdate(note))
        }
        return data
    }

    fun getAllNotes() = noteDao?.getAll()
}