package com.chetan.note.ui.editor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chetan.note.R
import com.chetan.note.common.BaseActivity
import com.chetan.note.data.database.NoteDB
import com.chetan.note.data.model.Note
import com.chetan.note.databinding.ActivityNoteEditorBinding
import com.chetan.note.ui.notedetails.NoteDetailActivity
import com.chetan.note.ui.noteslist.BUNDLE_NOTE

class NoteEditorActivity : BaseActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: ActivityNoteEditorBinding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_editor)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val noteDao = NoteDB.getInstance(applicationContext)?.noteDao()
        
        viewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(
                noteDao, intent.extras?.let {
                    supportActionBar!!.title = getString(R.string.title_edit_note)

                    it.getSerializable(BUNDLE_NOTE) as Note

                } ?: run {
                    supportActionBar!!.title = getString(R.string.title_new_note)

                    Note()

                }
            )
        ).get(
            NoteViewModel::
            class.java
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        viewModel.editorStatus.observe(this@NoteEditorActivity, Observer {
            val loginState = it ?: return@Observer
            menu?.getItem(0)?.isEnabled = loginState.isValid

            if (loginState.noteTitleError != null) {
                binding.textNoteTitle.error = getString(loginState.noteTitleError)
            }
            if (loginState.noteContentError != null) {
                binding.textNoteContent.error = getString(loginState.noteContentError)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(0)?.isEnabled = false

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> viewModel.saveNote().observe(this, Observer {
                when (it) {
                    is NoteResult.Success -> {
                        val bundle = Bundle()
                        bundle.putSerializable(BUNDLE_NOTE, it.note)
                        val intent = Intent(this@NoteEditorActivity, NoteDetailActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                        finish()
                    }
                    is NoteResult.Failure -> {
                        Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
        return super.onOptionsItemSelected(item)
    }
}