package com.assignment.myandroidjournal.di;

import android.content.Context;

import androidx.room.Room;

import com.assignment.myandroidjournal.model.database.AppDatabase;
import com.assignment.myandroidjournal.model.database.dao.JournalDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public AppDatabase providesDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "journal_database").fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    public JournalDao providesJournalDao(AppDatabase appDatabase)
    {
        return appDatabase.journalDao();
    }
}
