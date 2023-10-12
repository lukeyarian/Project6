package com.example.project6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note, container, false)

        val titleEditText: EditText = view.findViewById(R.id.noteTitleEditText)
        val descriptionEditText: EditText = view.findViewById(R.id.noteDescriptionEditText)

        val saveButton: Button = view.findViewById(R.id.saveNoteButton)
        saveButton.setOnClickListener {
            val note = Note(
                title = titleEditText.text.toString(),
                description = descriptionEditText.text.toString()
            )
            viewModel.insert(note)
            findNavController().popBackStack()
        }

        return view
    }
}