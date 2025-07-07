package com.openclassrooms.notes.repository

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.service.NotesApiService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for the notes.
 */
class NotesRepository @Inject constructor(
    private val notesApiService: NotesApiService
) {

    /**
     * A flow that emits a list of all notes.
     */
    val notes: Flow<List<Note>> = flow {
        emit(notesApiService.getAllNotes())
    }
}
        