package com.example.project6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class NotesListFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)
        val adapter = NotesAdapter(
            onClick = { note -> val action = NotesListFragmentDirections.actionToEditNote()
                findNavController().navigate(action)
            },
            onDelete = { note ->
                val dialog = DeleteConfirmationDialog(note) {
                    viewModel.delete(note)
                }
                dialog.show(parentFragmentManager, "DeleteNoteDialogFragment")
            }
        )
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNotes)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
        val addButton: Button = view.findViewById(R.id.buttonAddNote)
        addButton.setOnClickListener {
            val action = NotesListFragmentDirections.actionToAddNote()
            findNavController().navigate(action)
        }
        return view
    }
}