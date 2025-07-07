package com.openclassrooms.notes.repository

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.service.NotesApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class NotesRepositoryTest {

    private lateinit var mockNotesApiService: NotesApiService

    private lateinit var notesRepository: NotesRepository

    @Before
    fun setUp() {
        mockNotesApiService = mock() // Crée un mock de NotesApiService
        notesRepository = NotesRepository(mockNotesApiService)
    }

    @Test
    fun `notes flow should emit notes list from api service`() = runTest {
        // Arrange:
        val fakeNotesList = listOf(
            Note("Test Note 1", "Content for test note 1"),
            Note("Test Note 2", "Content for test note 2")
        )
        whenever(mockNotesApiService.getAllNotes()).thenReturn(fakeNotesList)

        // Act:
        val emittedNotes = notesRepository.notes.first()

        // Assert:
        assertEquals("Emitted notes should match the fake list from the service", fakeNotesList, emittedNotes)
        assertEquals("Should have emitted 2 notes", 2, emittedNotes.size)
        assertEquals("First note title should be 'Test Note 1'", "Test Note 1", emittedNotes[0].title)
    }

    @Test
    fun `notes flow should emit empty list when api service returns empty list`() = runTest {
        // Arrange
        val emptyList = emptyList<Note>()
        whenever(mockNotesApiService.getAllNotes()).thenReturn(emptyList)

        // Act
        val emittedNotes = notesRepository.notes.first()

        // Assert
        assertTrue("Emitted notes list should be empty", emittedNotes.isEmpty())
    }

    @Test
    fun `notes flow should propagate exceptions from api service`() = runTest {
        // Arrange:
        val expectedException = RuntimeException("API Error")
        whenever(mockNotesApiService.getAllNotes()).thenThrow(expectedException)

        // Act & Assert:
        try {
            notesRepository.notes.first() //
            fail("Flow should have thrown an exception")
        } catch (e: Exception) {
            assertEquals("Exception message should match", "API Error", e.message)
            assertTrue("Exception should be of the expected type", e is RuntimeException)
        }
    }
}
