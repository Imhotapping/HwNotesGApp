package com.example.hwnotesgapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private MutableLiveData<List<NoteItem>> notesLiveData = new MutableLiveData<>();
    private List<NoteItem> notes = new ArrayList<>();
    private NotesPrefs notesPrefs;

    public NotesViewModel(Application application) {
        super(application);
        notesPrefs = new NotesPrefs(application.getApplicationContext());
        loadNotesFromPrefs();
    }

    private void loadNotesFromPrefs() {
        notes = notesPrefs.loadNotes();
        notesLiveData.setValue(notes);
    }

    private void saveNotesToPrefs() {
        if (notesPrefs != null) {
            notesPrefs.saveNotes(notes);
        }
    }

    public LiveData<List<NoteItem>> getNotes() {
        return notesLiveData;
    }

    public void addNote(NoteItem note) {
        notes.add(note);
        notesLiveData.setValue(notes);
        saveNotesToPrefs();
    }

    public void updateNote(int position, NoteItem note) {
        if (position >= 0 && position < notes.size()) {
            notes.set(position, note);
            notesLiveData.setValue(notes);
            saveNotesToPrefs();
        }
    }

    public void deleteNote(int position) {
        if (position >= 0 && position < notes.size()) {
            notes.remove(position);
            notesLiveData.setValue(notes);
            saveNotesToPrefs();
        }
    }

    public void clearAllNotes() {
        notes.clear();
        notesLiveData.setValue(notes);
        if (notesPrefs != null) {
            notesPrefs.clearNotes();
        }
    }
}