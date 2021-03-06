package com.example.diplomnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ListNoteActivity extends AppCompatActivity {

    private List<Note> notes = new ArrayList<>();
    private BaseAdapter adapter;
    private NoteRepository noteRepository = App.getNoteRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsSpinner.onActivityCreateSetTheme(ListNoteActivity.this);
        setContentView(R.layout.activity_list_note);
        this.setTitle(R.string.title_notes);
        initView();
    }

    private List<Note> getNotesList() {
        try {
            notes = noteRepository.getNotes();
        } catch (EmptyStackException e) {
            e.getMessage();
            Toast.makeText(this, R.string.toast_error_add, Toast.LENGTH_LONG).show();
        }
        if (notes != null) {
            adapter = new NoteAdapter(this, notes);
            ListView listView = findViewById(R.id.list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            alertDialogInfo();
            Toast.makeText(this, R.string.toast_error_add, Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private void initView() {
        getNotesList();
        FloatingActionButton buttonAddNewNote = findViewById(R.id.floatingActionButton);
        buttonAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListNoteActivity.this, NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sett) {
            startActivity(new Intent(ListNoteActivity.this, OtherSettingsActivity.class));
            return false;
        } else if (id == R.id.action_info) {
            alertDialogInfo();
            return false;
        }
        return true;
    }

    private void alertDialogInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListNoteActivity.this);
        final TextView info = new TextView(ListNoteActivity.this);
        final ScrollView scrollView = new ScrollView(ListNoteActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        info.setGravity(Gravity.CENTER);
        info.setLayoutParams(params);
        info.setText(R.string.about_program);
        scrollView.addView(info);
        builder.setTitle(R.string.dialog_info)
                .setIcon(R.drawable.ic_perm_device_information_24dp)
                .setView(scrollView)
                .setPositiveButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}