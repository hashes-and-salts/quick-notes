package com.example.quicknotes.data.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes_table ORDER BY last_edited_date DESC")
    fun getNotesByDateDesc(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table ORDER BY title ASC")
    fun getNotesByTitlesAsc(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE id=:id")
    suspend fun getNoteById(id: Int): NoteEntity
}