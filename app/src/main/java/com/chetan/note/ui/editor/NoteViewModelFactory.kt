package com.chetan.note.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.data.NoteRepository
import com.chetan.note.data.database.NoteDao

/**
 * Created by Chetan on 11/03/20.
 */
class NoteViewModelFactory(private val noteDao: NoteDao?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(
                noteRepository = NoteRepository(
                    noteDao = noteDao
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}