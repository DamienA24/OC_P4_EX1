package com.openclassrooms.notes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.viewModel.NotesViewModel
import com.openclassrooms.notes.widget.NoteItemDecoration
import com.openclassrooms.notes.widget.NotesAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity for the app.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     */
    private lateinit var binding: ActivityMainBinding

    private val notesViewModel: NotesViewModel by viewModels()

    private val notesAdapter = NotesAdapter(emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initFABButton()
        observeNotes()
    }


    /**
     * Initializes the FAB button.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setTitle(R.string.coming_soon)
                setMessage(R.string.not_available_yet)
                setPositiveButton(android.R.string.ok, null)
            }.show()
        }
    }

    /**
     * Initializes the RecyclerView.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )

            adapter = notesAdapter
        }
    }

    /**
     * Observes the notes and updates the adapter when they change.
     */
    private fun observeNotes() {
            notesViewModel.allNotes.observe(this@MainActivity) { notes ->
                notesAdapter.updateNotes(notes)
            }
    }

}
