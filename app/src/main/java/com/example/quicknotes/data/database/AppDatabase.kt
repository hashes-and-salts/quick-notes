package com.example.quicknotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicknotes.data.note.NoteDao
import com.example.quicknotes.data.note.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): NoteDao
}