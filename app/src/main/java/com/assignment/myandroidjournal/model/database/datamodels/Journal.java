package com.assignment.myandroidjournal.model.database.datamodels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Journal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private int Mood;
    private long timestamp;

    public Journal(String description, int mood, long timestamp) {
        this.description = description;
        Mood = mood;
        this.timestamp = timestamp;
    }

    public Journal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMood() {
        return Mood;
    }

    public void setMood(int mood) {
        Mood = mood;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
