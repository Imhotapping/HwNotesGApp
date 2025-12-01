package com.example.hwnotesgapp;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 2;

    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        initViews();
        initRecyclerView();
        setupFAB();
        setupObservers();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NotesAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new NotesItemDecoration(16));

        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                editNote(position);
            }
        });
    }

    private void setupObservers() {
        notesViewModel.getNotes().observe(this, new Observer<List<NoteItem>>() {
            @Override
            public void onChanged(List<NoteItem> noteItems) {
                adapter.setNotes(noteItems);
            }
        });
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_NOTE_REQUEST) {
                NoteItem note = (NoteItem) data.getSerializableExtra(AddEditNoteActivity.EXTRA_NOTE);
                notesViewModel.addNote(note);
            } else if (requestCode == EDIT_NOTE_REQUEST) {
                NoteItem note = (NoteItem) data.getSerializableExtra(AddEditNoteActivity.EXTRA_NOTE);
                int position = data.getIntExtra(AddEditNoteActivity.EXTRA_POSITION, -1);
                notesViewModel.updateNote(position, note);
            }
        }
    }

    public void editNote(int position) {
        List<NoteItem> currentNotes = notesViewModel.getNotes().getValue();
        if (currentNotes != null && position >= 0 && position < currentNotes.size()) {
            NoteItem note = currentNotes.get(position);
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            intent.putExtra(AddEditNoteActivity.EXTRA_NOTE, note);
            intent.putExtra(AddEditNoteActivity.EXTRA_POSITION, position);
            startActivityForResult(intent, EDIT_NOTE_REQUEST);
        }
    }

    public void deleteNote(int position) {
        notesViewModel.deleteNote(position);
    }
}