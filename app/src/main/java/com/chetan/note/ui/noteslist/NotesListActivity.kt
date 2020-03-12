package com.chetan.note.ui.noteslist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.R
import com.chetan.note.adapter.NotesAdapter
import com.chetan.note.adapter.NotesListener
import com.chetan.note.data.database.NoteDB
import com.chetan.note.databinding.ActivityNotesListBinding
import com.chetan.note.ui.editor.NoteEditorActivity

const val BUNDLE_NOTE = "BUNDLE_NOTE"

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
            val bundle = Bundle()
            bundle.putSerializable(BUNDLE_NOTE, note)
            val intent = Intent(this@NotesListActivity, NoteEditorActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })
        binding.notesList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_new_note -> {
                val intent = Intent(this@NotesListActivity, NoteEditorActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
