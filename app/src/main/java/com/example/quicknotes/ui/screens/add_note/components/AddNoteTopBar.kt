package com.example.quicknotes.ui.screens.add_note.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun AddNoteTopBar( ) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Create New Note") },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.ChangeCircle, contentDescription = "Set Color")
            }
        }
    )
}