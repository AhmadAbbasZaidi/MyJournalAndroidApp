package com.assignment.myandroidjournal.di;

import android.content.Context;

import androidx.room.Room;

import com.assignment.myandroidjournal.model.database.AppDatabase;
import com.assignment.myandroidjournal.model.database.dao.JournalDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;

@TestInstallIn(components = SingletonComponent.class, replaces = AppModule.class)
@Module
public class AppModuleTest {

    @Singleton
    @Provides
    public AppDatabase providesDatabase(@ApplicationContext Context context) {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public JournalDao providesJournalDao(AppDatabase appDatabase) {
        return appDatabase.journalDao();
    }

}