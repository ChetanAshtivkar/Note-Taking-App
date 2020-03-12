package com.chetan.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chetan.note.data.model.Note
import com.chetan.note.databinding.ListItemNoteBinding

/**
 * Created by Chetan on 12/03/20.
 */
class NotesAdapter(private val listener: NotesListener) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener, position)
    }

    class NoteViewHolder private constructor(private val binding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Note,
            listener: NotesListener,
            position: Int
        ) {
            binding.note = item
            binding.listener = listener
            binding.position = position
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNoteBinding.inflate(layoutInflater, parent, false)
                return NoteViewHolder(binding)
            }
        }
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}

class NotesListener(val clickListener: (note: Note, position: Int) -> Unit) {
    fun onClick(note: Note, position: Int) = clickListener(note, position)
}