package com.example.project6


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.auth.FirebaseAuth

class NoteRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("notes")
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()

    fun insert(note: Note) {
        val userId = firebaseAuth.currentUser?.uid
        val noteId = databaseReference.push().key

        if (userId != null && noteId != null) {
            note.id = noteId.toInt()
            databaseReference.child(userId).child(noteId).setValue(note)
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            databaseReference.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val notes = mutableListOf<Note>()
                    for (noteSnapshot in snapshot.children) {
                        val note = noteSnapshot.getValue(Note::class.java)
                        if (note != null) {
                            notes.add(note)
                        }
                    }
                    notesLiveData.postValue(notes)
                }

                override fun onCancelled(error: DatabaseError) {
                    //blank rn
                }
            })
        }

        return notesLiveData
    }

    fun update(note: Note) {
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null && note.id != null) {
            databaseReference.child(userId).child(note.id.toString()).setValue(note)
        }
    }

    fun delete(note: Note) {
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null && note.id != null) {
            databaseReference.child(userId).child(note.id.toString()).removeValue()
        }
    }
}