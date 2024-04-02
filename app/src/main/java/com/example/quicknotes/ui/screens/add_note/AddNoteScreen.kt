package com.example.quicknotes.ui.screens.add_note

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quicknotes.data.note.NoteEntity
import com.example.quicknotes.ui.screens.add_note.components.AddNoteFAB
import com.example.quicknotes.ui.screens.add_note.components.AddNoteTopBar
import com.example.quicknotes.ui.screens.components.NoteCard
import com.example.quicknotes.utils.getNoteCardColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavController, addNoteViewModel: AddNoteViewModel) {

    val state by remember {
        addNoteViewModel.noteState
    }.collectAsState()
    
    val isNoteBeingSaved by remember {
        addNoteViewModel.isSaving
    }.collectAsState()

    val noteSavedSuccessfully by remember {
        addNoteViewModel.noteSaved
    }.collectAsState()


    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val snackBarErrorMessage by remember {
        addNoteViewModel.snackBarErrorMessage
    }.collectAsState()

    var saveNoteClicked by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = noteSavedSuccessfully, key2 = saveNoteClicked) {
        if(saveNoteClicked) {
            if(noteSavedSuccessfully) {
                // Navigate Back
                navController.popBackStack()
            } else {
                // Show Snackbar
                snackBarHostState.showSnackbar(message = snackBarErrorMessage, duration = SnackbarDuration.Short)
            }
            saveNoteClicked = false
        }
    }

    // Color Picker Dialog Box
    var showColorPickerDialog by remember{
        mutableStateOf(false)
    }

    // Note Colors
//    val noteColors = listOf(
//        getNoteCardColor("")
//    )

    val noteColorNames = listOf(
        "blue",
        "green",
        "yellow",
        "orange",
        "red",
        "purple",
        "brown",
    )

    val noteColors = noteColorNames.map { colorName ->
        getNoteCardColor(colorName, isSystemInDarkTheme()) ?: Color.Transparent
    }

    Scaffold (
        topBar = { AddNoteTopBar() },
        floatingActionButton = { AddNoteFAB(onClick = {
            saveNoteClicked = saveNoteClicked.not()
            addNoteViewModel.saveNote()
        }) },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValue ->
        Surface(
            modifier = Modifier.padding(paddingValue)
        ) {

            // AddNoteScreen Card
            Card(
                modifier = Modifier
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getNoteCardColor(state.color, isSystemInDarkTheme()) ?: Color.Transparent
                ),
            ) {
                // Title textfield
                OutlinedTextField(
                    value = state.title,
                    onValueChange = { addNoteViewModel.updateNoteTitle(it) },
                    label = { Text(text = "Title") },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                )

                // Content textfield
                OutlinedTextField(
                    value = state.content,
                    onValueChange = { addNoteViewModel.updateNoteContent(it) },
                    label = { Text(text = "Content") },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                )


                Card(
                    onClick = {
                        // show color selection Dialog
                        showColorPickerDialog = showColorPickerDialog.not()
                    },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(0.8f)
                    ,

                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(text = "Note Color", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
                }



            }

            if(isNoteBeingSaved) {
                LinearProgressIndicator()
            }

            if(showColorPickerDialog) {
                Dialog(onDismissRequest = { showColorPickerDialog = showColorPickerDialog.not() }) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        // Color Grid
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            modifier = Modifier
                                .padding(32.dp)
                                .height(400.dp)
                        ) {

                            itemsIndexed(noteColors) {index, color ->
                                Card(
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .padding(8.dp)
                                    ,
                                    colors = CardDefaults.cardColors(
                                        containerColor = color
                                    ),
                                    onClick = {
                                        addNoteViewModel.updateNoteColor(noteColorNames[index])
                                    }
                                ) {

                                }
                            }
                            

                        }

                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            NoteCard(noteEntity = NoteEntity(title = "Note Title", content = "Note Content", System.currentTimeMillis(), color = state.color), onCardClick = {}, onCardLongPress = {})
                        }


                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextButton(onClick = { showColorPickerDialog = showColorPickerDialog.not() }) {
                                Text( text = "Set Color")
                            }
                        }


                    }

                }
            }


        }
    }
}

@Preview
@Composable
private fun AddNoteScreenPreview() {

    val viewModel = hiltViewModel<AddNoteViewModel>()
    AddNoteScreen(navController = rememberNavController(), addNoteViewModel = viewModel)

}