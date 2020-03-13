package com.chetan.note.ui.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chetan.note.R
import com.chetan.note.data.NoteRepository
import com.chetan.note.data.model.Note
import java.util.*

/**
 * Created by Chetan on 11/03/20.
 */
class NoteViewModel(private val noteRepository: NoteRepository, val note: Note) : ViewModel() {

    private val _editorStatus = MutableLiveData<EditorStatus>()
    val editorStatus: LiveData<EditorStatus> = _editorStatus

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean> = _result

    fun saveNote(): MutableLiveData<NoteResult> {
        when (note.createDate) {
            null -> note.createDate = Date()
        }
        note.updateDate = Date()
        return noteRepository.saveNote(note)
    }

    fun onTitleChanged(title: CharSequence) = when {
        !isTitleValid(title.toString()) -> {
            _editorStatus.value = EditorStatus(noteTitleError = R.string.invalid_title)
        }
        !isContentValid(note.contents) -> {
        }
        else -> {
            _editorStatus.value = EditorStatus(isValid = true)
        }
    }

    fun onContentChanged(content: CharSequence) = when {
        !isContentValid(content.toString()) -> {
            _editorStatus.value = EditorStatus(noteContentError = R.string.invalid_content)
        }
        !isTitleValid(note.title) -> {
        }
        else -> {
            _editorStatus.value = EditorStatus(isValid = true)
        }
    }

    private fun isContentValid(contents: String?): Boolean {
        return contents?.trim()?.isNotEmpty() ?: kotlin.run {
            return false
        }
    }

    private fun isTitleValid(title: String?): Boolean {
        return title?.trim()?.isNotEmpty() ?: kotlin.run {
            return false
        }
    }
}