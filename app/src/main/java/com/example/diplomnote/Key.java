package com.example.diplomnote;

interface Key {
    boolean hasPin();
    boolean checkPin(String pin);
    void saveNew(String pin);
}
