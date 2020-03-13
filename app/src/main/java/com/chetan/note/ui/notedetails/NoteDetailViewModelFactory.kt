package com.chetan.note.ui.notedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.data.model.Note

class NoteDetailViewModelFactory(private val note: Note) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(
                note = note
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}