package com.chetan.note.ui.editor

import com.chetan.note.data.model.Note

/**
 * Created by Chetan on 13/03/20.
 */
sealed class NoteResult {
    data class Success(val note: Note) : NoteResult()
    data class Failure(val error: String) : NoteResult()
}