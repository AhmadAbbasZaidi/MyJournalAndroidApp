package com.assignment.myandroidjournal.model.database.dao;


import android.media.Image;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.myandroidjournal.model.database.datamodels.Journal;

import java.util.List;

@Dao
public interface JournalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Journal journal);

    @Delete
    void delete(Journal journal);

    @Query("Select * From journal where id= :id")
    LiveData<Journal> getJournalById(String id);

    @Query("Select * From journal Order By timestamp Desc")
    LiveData<List<Journal>> getAllJournals();

    @Query("Select * From journal Order By timestamp Desc")
    List<Journal> getAllJournalList();



}
