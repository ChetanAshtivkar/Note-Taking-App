package com.chetan.note.ui.noteslist

import androidx.lifecycle.ViewModel
import com.chetan.note.data.NoteRepository

/**
 * Created by Chetan on 11/03/20.
 */
class NotesListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    fun getNotes() = noteRepository.getAllNotes()
}