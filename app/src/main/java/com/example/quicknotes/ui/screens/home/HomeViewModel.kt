package com.example.quicknotes.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknotes.data.note.NoteEntity
import com.example.quicknotes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    val _state = MutableStateFlow<List<NoteEntity>>(emptyList())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            noteRepository.getNotesByDateDesc().collect {
                _state.value = it
            }
        }
    }


}