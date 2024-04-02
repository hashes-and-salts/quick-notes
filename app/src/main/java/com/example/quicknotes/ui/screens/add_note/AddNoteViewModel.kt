package com.example.quicknotes.ui.screens.add_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknotes.data.note.NoteEntity
import com.example.quicknotes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    private val _noteState = MutableStateFlow(
        NoteEntity(
            title = "",
            content = "",
            dateEdited = System.currentTimeMillis(),
            color = "none"
        )
    )
    val noteState = _noteState.asStateFlow()


    private val _isSaving = MutableStateFlow(false)
    val isSaving = _isSaving.asStateFlow()

    private val _noteSaved = MutableStateFlow(false)
    val noteSaved = _noteSaved.asStateFlow()

    private val _snackBarErrorMessage = MutableStateFlow("")
    val snackBarErrorMessage = _snackBarErrorMessage.asStateFlow()

    fun updateNoteTitle(noteTitle: String) {
        _noteState.update {
            it.copy(title = noteTitle)
        }
    }

    fun updateNoteContent(noteContent: String) {
        _noteState.update {
            it.copy(content = noteContent)
        }
    }

    fun updateNoteColor(noteColor: String) {
        _noteState.update {
            it.copy(color = noteColor)
        }
    }

    fun saveNote() {
        try {
            if(isNoteValid()) {
                _isSaving.value = true
                viewModelScope.launch {
                    noteRepository.saveNote(_noteState.value)

                    _noteSaved.value = true
                }
            }
        } catch (e: Exception) {
            e.localizedMessage?.let {
                _snackBarErrorMessage.value = it
            }
            _noteSaved.value = false
        }
    }


    private fun isNoteValid(): Boolean {
        if(_noteState.value.title.trim().isBlank() || _noteState.value.title.trim().isEmpty()) {
            throw Exception("Empty Note Title!")
        }

        if(_noteState.value.content.trim().isBlank() || _noteState.value.content.trim().isEmpty()) {
            throw Exception("Empty Note Content!")
        }

        return true
    }

}