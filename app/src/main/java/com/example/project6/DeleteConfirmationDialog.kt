package com.example.project6
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog

class DeleteConfirmationDialog(note: Note, private val positiveCallback: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("Are you sure you want to delete?")
            .setPositiveButton("Yes") { _, _ ->
                positiveCallback.invoke()
            }
            .setNegativeButton("No", null)
            .create()
    }
}