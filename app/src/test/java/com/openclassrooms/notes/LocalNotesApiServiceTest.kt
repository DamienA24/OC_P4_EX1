package com.openclassrooms.notes

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.service.LocalNotesApiService
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LocalNotesApiServiceTest {

    private lateinit var localNotesApiService: LocalNotesApiService
    private lateinit var notes: List<Note>
    @Before
    fun setUp() {
        localNotesApiService = LocalNotesApiService()
        notes = localNotesApiService.getAllNotes()
    }

    @Test
    fun `getAllNotes should return a predefined list of 10 notes`() {
        // Assert
        assertEquals("getAllNotes should return 10 notes", 10, notes.size)
    }

    @Test
    fun `getAllNotes should return the expected first note`() {

        // Assert
        assertTrue("Notes list should not be empty", notes.isNotEmpty())
        val firstNote = notes[0]
        assertEquals("La vie est belle", firstNote.title)
        assertEquals(
            "La vie est belle, pleine de choses à voir et à faire. Profitez de chaque moment et ne laissez jamais personne vous dire que vous ne pouvez pas faire ce que vous voulez.",
            firstNote.body
        )
    }

    @Test
    fun `getAllNotes should return the expected last note`() {
        // Assert
        assertTrue("Notes list should not be empty", notes.isNotEmpty())
        val lastNote = notes[notes.size - 1] // Index 9 pour une liste de 10
        assertEquals("Risez et amusez-vous.", lastNote.title)
        assertEquals(
            "La vie est trop courte pour être sérieuse tout le temps. Riez et amusez-vous. Passez du temps à faire les choses que vous aimez.",
            lastNote.body
        )
    }


}