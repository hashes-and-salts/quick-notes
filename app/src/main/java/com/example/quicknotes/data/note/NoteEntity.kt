package com.example.quicknotes.data.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteEntity(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "last_edited_date")
    val dateEdited: Long,

    @ColumnInfo(name = "color")
    val color: String = "none",

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)