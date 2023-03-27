package com.assignment.myandroidjournal.view.interfaces;

import com.assignment.myandroidjournal.model.database.datamodels.Journal;

public interface ListItem {
    public boolean isHeader();
    public boolean isSubHeader();
    public Journal getJournal();
    public void setOverallMood(int overallMood);
    public int getOverallMood();
    public void setItemCount(int count);
    public int getItemCount();
    public void setSubItemCount(int count);
    public int getSubItemCount();
}
