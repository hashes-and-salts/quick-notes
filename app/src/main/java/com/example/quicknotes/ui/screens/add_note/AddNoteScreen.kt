package com.example.quicknotes.ui.screens.add_note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.toArgb
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
import com.example.quicknotes.ui.theme.catppuccin_latte_Base
import com.example.quicknotes.ui.theme.catppuccin_latte_Blue
import com.example.quicknotes.ui.theme.catppuccin_latte_Crust
import com.example.quicknotes.ui.theme.catppuccin_latte_Flamingo
import com.example.quicknotes.ui.theme.catppuccin_latte_Green
import com.example.quicknotes.ui.theme.catppuccin_latte_Lavender
import com.example.quicknotes.ui.theme.catppuccin_latte_Mantle
import com.example.quicknotes.ui.theme.catppuccin_latte_Maroon
import com.example.quicknotes.ui.theme.catppuccin_latte_Mauve
import com.example.quicknotes.ui.theme.catppuccin_latte_Overlay0
import com.example.quicknotes.ui.theme.catppuccin_latte_Overlay1
import com.example.quicknotes.ui.theme.catppuccin_latte_Overlay2
import com.example.quicknotes.ui.theme.catppuccin_latte_Peach
import com.example.quicknotes.ui.theme.catppuccin_latte_Pink
import com.example.quicknotes.ui.theme.catppuccin_latte_Red
import com.example.quicknotes.ui.theme.catppuccin_latte_Rosewater
import com.example.quicknotes.ui.theme.catppuccin_latte_Sapphire
import com.example.quicknotes.ui.theme.catppuccin_latte_Sky
import com.example.quicknotes.ui.theme.catppuccin_latte_Subtext0
import com.example.quicknotes.ui.theme.catppuccin_latte_Subtext1
import com.example.quicknotes.ui.theme.catppuccin_latte_Surface0
import com.example.quicknotes.ui.theme.catppuccin_latte_Surface1
import com.example.quicknotes.ui.theme.catppuccin_latte_Surface2
import com.example.quicknotes.ui.theme.catppuccin_latte_Teal
import com.example.quicknotes.ui.theme.catppuccin_latte_Text
import com.example.quicknotes.ui.theme.catppuccin_latte_Yellow
import com.example.quicknotes.ui.theme.catppuccin_mocha_Base
import com.example.quicknotes.ui.theme.catppuccin_mocha_Blue
import com.example.quicknotes.ui.theme.catppuccin_mocha_Crust
import com.example.quicknotes.ui.theme.catppuccin_mocha_Flamingo
import com.example.quicknotes.ui.theme.catppuccin_mocha_Green
import com.example.quicknotes.ui.theme.catppuccin_mocha_Lavender
import com.example.quicknotes.ui.theme.catppuccin_mocha_Mantle
import com.example.quicknotes.ui.theme.catppuccin_mocha_Maroon
import com.example.quicknotes.ui.theme.catppuccin_mocha_Mauve
import com.example.quicknotes.ui.theme.catppuccin_mocha_Overlay0
import com.example.quicknotes.ui.theme.catppuccin_mocha_Overlay1
import com.example.quicknotes.ui.theme.catppuccin_mocha_Overlay2
import com.example.quicknotes.ui.theme.catppuccin_mocha_Peach
import com.example.quicknotes.ui.theme.catppuccin_mocha_Pink
import com.example.quicknotes.ui.theme.catppuccin_mocha_Red
import com.example.quicknotes.ui.theme.catppuccin_mocha_Rosewater
import com.example.quicknotes.ui.theme.catppuccin_mocha_Sapphire
import com.example.quicknotes.ui.theme.catppuccin_mocha_Sky
import com.example.quicknotes.ui.theme.catppuccin_mocha_Subtext0
import com.example.quicknotes.ui.theme.catppuccin_mocha_Subtext1
import com.example.quicknotes.ui.theme.catppuccin_mocha_Surface0
import com.example.quicknotes.ui.theme.catppuccin_mocha_Surface1
import com.example.quicknotes.ui.theme.catppuccin_mocha_Surface2
import com.example.quicknotes.ui.theme.catppuccin_mocha_Teal
import com.example.quicknotes.ui.theme.catppuccin_mocha_Text
import com.example.quicknotes.ui.theme.catppuccin_mocha_Yellow

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
    val noteColors = listOf(

        // Latte
        catppuccin_latte_Rosewater,
        catppuccin_latte_Flamingo,
        catppuccin_latte_Pink,
        catppuccin_latte_Mauve,
        catppuccin_latte_Red,
        catppuccin_latte_Maroon,
        catppuccin_latte_Peach,
        catppuccin_latte_Yellow,
        catppuccin_latte_Green,
        catppuccin_latte_Teal,
        catppuccin_latte_Sky,
        catppuccin_latte_Sapphire,
        catppuccin_latte_Blue,
        catppuccin_latte_Lavender,
        catppuccin_latte_Text,
        catppuccin_latte_Subtext1,
        catppuccin_latte_Subtext0,
        catppuccin_latte_Overlay2,
        catppuccin_latte_Overlay1,
        catppuccin_latte_Overlay0,
        catppuccin_latte_Surface2,
        catppuccin_latte_Surface1,
        catppuccin_latte_Surface0,
        catppuccin_latte_Base,
        catppuccin_latte_Mantle,
        catppuccin_latte_Crust,




        // Mocha
        catppuccin_mocha_Rosewater,
        catppuccin_mocha_Flamingo,
        catppuccin_mocha_Pink,
        catppuccin_mocha_Mauve,
        catppuccin_mocha_Red,
        catppuccin_mocha_Maroon,
        catppuccin_mocha_Peach,
        catppuccin_mocha_Yellow,
        catppuccin_mocha_Green,
        catppuccin_mocha_Teal,
        catppuccin_mocha_Sky,
        catppuccin_mocha_Sapphire,
        catppuccin_mocha_Blue,
        catppuccin_mocha_Lavender,
        catppuccin_mocha_Text,
        catppuccin_mocha_Subtext1,
        catppuccin_mocha_Subtext0,
        catppuccin_mocha_Overlay2,
        catppuccin_mocha_Overlay1,
        catppuccin_mocha_Overlay0,
        catppuccin_mocha_Surface2,
        catppuccin_mocha_Surface1,
        catppuccin_mocha_Surface0,
        catppuccin_mocha_Base,
        catppuccin_mocha_Mantle,
        catppuccin_mocha_Crust

    )

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

            Card(
                modifier = Modifier
//                    .fillMaxHeight()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(state.color.toInt())
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

                            items(noteColors) {
                                Card(
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .padding(8.dp)
                                    ,
                                    colors = CardDefaults.cardColors(
                                        containerColor = it
                                    ),
                                    onClick = {
                                        addNoteViewModel.updateNoteColor(it.toArgb().toString())
                                    }
                                ) {

                                }
                            }
                            

                        }

                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            NoteCard(noteEntity = NoteEntity(title = "Note Title", content = "Note Content", System.currentTimeMillis(), color = state.color), onClick = {})
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