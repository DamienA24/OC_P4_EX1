package com.openclassrooms.notes.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.repository.NotesRepository

class NotesViewModel : ViewModel() {

    private val notesRepository = NotesRepository()

    val allNotes: LiveData<List<Note>> = notesRepository.notes.asLiveData()

}