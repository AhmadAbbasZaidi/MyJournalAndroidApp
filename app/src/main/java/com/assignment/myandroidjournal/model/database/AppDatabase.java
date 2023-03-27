package com.assignment.myandroidjournal.model.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.assignment.myandroidjournal.model.database.dao.JournalDao;
import com.assignment.myandroidjournal.model.database.datamodels.Journal;

@androidx.room.Database(entities = {Journal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JournalDao journalDao();
}
