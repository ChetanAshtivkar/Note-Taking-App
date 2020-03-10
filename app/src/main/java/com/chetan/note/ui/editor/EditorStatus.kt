package com.chetan.note.ui.editor

/**
 * Created by Chetan on 11/03/20.
 */

data class EditorStatus(
    val noteTitleError: Int? = null,
    val noteContentError: Int? = null,
    val isValid: Boolean = false
)