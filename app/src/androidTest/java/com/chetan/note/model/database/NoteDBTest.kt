package com.chetan.note.model.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chetan.note.model.Note
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by Chetan on 08/03/20.
 */
@RunWith(AndroidJUnit4::class)
class NoteDBTest {
    private lateinit var noteDao: NoteDao
    private lateinit var noteDB: NoteDB

    @Before
    fun init() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        noteDB = Room.inMemoryDatabaseBuilder(context, NoteDB::class.java)
            .allowMainThreadQueries()
            .build()
        noteDao = noteDB.noteDao()
    }

    @After
    fun destroy() {
        noteDB.close()
    }

    @Test
    fun testInsert() {
        val note = Note(
            1,
            "Title",
            "Content",
            Date(),
            Date()
        )
        noteDao.insert(note)
        assertThat(noteDao.getAllNotes()[0], equalTo(note))
    }

    @Test
    fun testUpdate() {
        val note = Note(
            1,
            "Title",
            "Content",
            Date(),
            Date()
        )
        noteDao.insert(note)

        note.title = "Title 1"
        noteDao.update(note)

        Assert.assertEquals(note.title, noteDao.getAllNotes()[0].title)
    }

    @Test
    fun testInsertOrUpdate() {
        val note = Note(
            1,
            "Title",
            "Content",
            Date(),
            Date()
        )
        noteDao.addOrUpdate(note)

        Assert.assertEquals(note.title, noteDao.getAllNotes()[0].title)
    }

    @Test
    fun testUpdate1() {
        val note = Note(
            1,
            "Title",
            "Content",
            Date(),
            Date()
        )
        noteDao.insert(note)

        note.title = "Title 1"
        noteDao.addOrUpdate(note)

        Assert.assertEquals(note.title, noteDao.getAllNotes()[0].title)
    }

    @Test
    fun testCount() {
        val note = Note(
            1,
            "Title",
            "Content",
            Date(),
            Date()
        )
        noteDao.insert(note)
        Assert.assertEquals(noteDao.getCount(), 1)
    }

    @Test
    fun testGetAll() {
        for (i in 1..5) {
            val note = Note(
                i,
                "Title",
                "Content",
                Date(),
                Date()
            )
            noteDao.insert(note)
        }
        Assert.assertEquals(noteDao.getAllNotes().size, 5)
    }
}