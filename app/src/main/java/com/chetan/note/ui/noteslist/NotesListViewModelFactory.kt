package com.chetan.note.ui.noteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.data.NoteRepository
import com.chetan.note.data.database.NoteDao

/**
 * Created by Chetan on 11/03/20.
 */
class NotesListViewModelFactory(private val noteDao: NoteDao?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesListViewModel::class.java)) {
            return NotesListViewModel(
                noteRepository = NoteRepository(
                    noteDao = noteDao
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}