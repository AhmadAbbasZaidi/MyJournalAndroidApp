package com.assignment.myandroidjournal.model.database.dao;

import com.assignment.myandroidjournal.App;
import com.assignment.myandroidjournal.R;
import com.assignment.myandroidjournal.model.database.AppDatabase;
import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.view.utils.DateFormatUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
public class JournalDaoTest {

    @Inject
    AppDatabase appDatabase;
    JournalDao journalDao;

    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Before
    public void init() {
        hiltRule.inject();
        journalDao = appDatabase.journalDao();
    }

    @Test
    public void runtest()
    {
        journalDao.insert(new Journal("description", R.color.red, DateFormatUtil.getOldDateString(1)));

        List<Journal> journal = journalDao.getAllJournalList();

        Assert.assertEquals(1,journal.size());
    }
}