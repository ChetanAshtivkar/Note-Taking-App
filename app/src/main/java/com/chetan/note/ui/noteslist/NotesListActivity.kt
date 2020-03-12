package com.chetan.note.ui.noteslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.R
import com.chetan.note.adapter.NotesAdapter
import com.chetan.note.adapter.NotesListener
import com.chetan.note.data.database.NoteDB
import com.chetan.note.databinding.ActivityNotesListBinding

class NotesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotesListBinding
    private lateinit var viewModel: NotesListViewModel
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_list)
        val noteDao = NoteDB.getInstance(applicationContext)?.noteDao()

        viewModel =
            ViewModelProvider(
                this,
                NotesListViewModelFactory(noteDao)
            ).get(NotesListViewModel::class.java)

        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)

        setAdapter()
        viewModel.getNotes()?.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun setAdapter() {
        adapter = NotesAdapter(NotesListener { note, position ->
            //TODO
        })
        binding.notesList.adapter = adapter
    }
}
