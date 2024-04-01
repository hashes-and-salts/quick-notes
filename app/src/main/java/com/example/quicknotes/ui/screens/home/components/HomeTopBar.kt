package com.example.quicknotes.ui.screens.home.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun HomeTopBar() {

    CenterAlignedTopAppBar(
        title = { Text("ShittyNotes") }
    )

}