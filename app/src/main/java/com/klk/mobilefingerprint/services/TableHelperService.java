package com.klk.mobilefingerprint.services;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public interface TableHelperService {
    void insert(SQLiteDatabase db, ContentValues values);
    ArrayList getAll(SQLiteDatabase db);
}
