package com.example.quicknotes.data.repository

import com.example.quicknotes.data.note.NoteDao
import com.example.quicknotes.data.note.NoteEntity
import kotlinx.coroutines.flow.Flow


class NoteRepository(
    private val noteDao: NoteDao
) {
    suspend fun saveNote(noteEntity: NoteEntity) {
        noteDao.upsertNote(noteEntity)
    }

    suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.deleteNote(noteEntity)
    }

    fun getNotesByDateDesc(): Flow<List<NoteEntity>> {
        return noteDao.getNotesByDateDesc()
    }

    fun getNotesByTitleAsc(): Flow<List<NoteEntity>> {
        return noteDao.getNotesByTitlesAsc()
    }

    suspend fun getNoteById(id: Int): NoteEntity {
        return noteDao.getNoteById(id)
    }


}