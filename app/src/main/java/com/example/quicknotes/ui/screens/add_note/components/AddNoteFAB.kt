package com.example.quicknotes.ui.screens.add_note.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddNoteFAB(onClick: () -> Unit) {

    FloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Rounded.AddTask, contentDescription = "Save Note")
    }

}