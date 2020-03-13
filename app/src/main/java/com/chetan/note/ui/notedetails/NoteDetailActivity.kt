package com.chetan.note.ui.notedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.R
import com.chetan.note.data.model.Note
import com.chetan.note.databinding.ActivityNoteDetailBinding
import com.chetan.note.ui.noteslist.BUNDLE_NOTE

class NoteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteDetailBinding
    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_detail)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(
            this,
            NoteDetailViewModelFactory(intent.extras?.let {
                val note = it.getSerializable(BUNDLE_NOTE) as Note
                supportActionBar!!.title = note.title
                note
            } ?: run {
                Note()
            })
        ).get(NoteDetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
