package com.assignment.myandroidjournal.view.model;

import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.view.interfaces.ListItem;

public class ItemModel implements ListItem {

    Journal journal;
    int overallMood;
    int count;
    int subcount;

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public boolean isSubHeader() {
        return false;
    }

    @Override
    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @Override
    public int getOverallMood() {
        return overallMood;
    }

    @Override
    public void setItemCount(int count) {
        this.count = count;
    }

    @Override
    public void setSubItemCount(int count) {
        this.subcount = count;
    }

    @Override
    public int getSubItemCount() {
        return subcount;
    }


    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public void setOverallMood(int overallMood) {
        this.overallMood = overallMood;
    }

}