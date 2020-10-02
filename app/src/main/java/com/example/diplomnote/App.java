package com.example.diplomnote;

import android.app.Application;

public class App extends Application {

    private static NoteRepository noteRepository;
    private static Key key;

    @Override
    public void onCreate() {
        super.onCreate();
        noteRepository = new FileNotes(this);
        key = new FilePin(this);
    }

    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }

    public static Key getKey() {
        return key;
    }
}