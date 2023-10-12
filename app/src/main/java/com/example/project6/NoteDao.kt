package com.example.project6
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteId: Note)

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun delete(noteId: Note)

    @Update
    suspend fun update(noteId: Note)
}