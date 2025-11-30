package com.example.hwnotesgapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends ViewModel {
    private MutableLiveData<List<NoteItem>> notesLiveData = new MutableLiveData<>();
    private List<NoteItem> notes = new ArrayList<>();

    public NotesViewModel() {
        notesLiveData.setValue(notes);
    }

    public LiveData<List<NoteItem>> getNotes() {
        return notesLiveData;
    }

    public void addNote(NoteItem note) {
        notes.add(note);
        notesLiveData.setValue(notes);
    }

    public void updateNote(int position, NoteItem note) {
        if (position >= 0 && position < notes.size()) {
            notes.set(position, note);
            notesLiveData.setValue(notes);
        }
    }

    public void deleteNote(int position) {
        if (position >= 0 && position < notes.size()) {
            notes.remove(position);
            notesLiveData.setValue(notes);
        }
    }

    public void setNotes(List<NoteItem> newNotes) {
        notes.clear();
        notes.addAll(newNotes);
        notesLiveData.setValue(notes);
    }
}