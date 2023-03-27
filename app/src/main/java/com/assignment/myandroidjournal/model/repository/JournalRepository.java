package com.assignment.myandroidjournal.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.assignment.myandroidjournal.App;
import com.assignment.myandroidjournal.R;
import com.assignment.myandroidjournal.model.database.AppDatabase;
import com.assignment.myandroidjournal.model.database.dao.JournalDao;
import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.view.utils.DateFormatUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JournalRepository {

    private JournalDao journalDao;
    private LiveData<List<Journal>> journals;

    @Inject
    public JournalRepository(JournalDao journalDao) {
        this.journalDao = journalDao;
        journals = journalDao.getAllJournals();
    }

    public void insert(Journal journal) {
        new InsertJournalAsyncTask(journalDao).execute(journal);
    }

    public void delete(Journal journal) {
        new DeleteJournalAsyncTask(journalDao).execute(journal);
    }

    public LiveData<List<Journal>> getAllJournals(){
        return journals;
    }

    public void initializeEmptyDB() {
        int descriptionCount =1;
        List<Journal> list = new ArrayList<>();

        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.red), DateFormatUtil.getOldDateString(1)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.green), DateFormatUtil.getOldDateString(1)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.green), DateFormatUtil.getOldDateString(3)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.red), DateFormatUtil.getOldDateString(4)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.yellow), DateFormatUtil.getOldDateString(4)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.green), DateFormatUtil.getOldDateString(4)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.yellow), DateFormatUtil.getOldDateString(14)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.red), DateFormatUtil.getOldDateString(14)));
        list.add(new Journal("description"+descriptionCount++, App.getINSTANCE().getColor(R.color.yellow), DateFormatUtil.getOldDateString(24)));
        list.add(new Journal("description"+descriptionCount++,App.getINSTANCE().getColor(R.color.yellow), DateFormatUtil.getOldDateString(24)));
        list.add(new Journal("description"+descriptionCount++,App.getINSTANCE().getColor(R.color.red), DateFormatUtil.getOldDateString(34)));
        list.add(new Journal("description"+descriptionCount++,App.getINSTANCE().getColor(R.color.yellow), DateFormatUtil.getOldDateString(36)));
        list.add(new Journal("description"+descriptionCount++,App.getINSTANCE().getColor(R.color.green), DateFormatUtil.getOldDateString(36)));

        for(Journal j :list)
        {
            insert(j);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class InsertJournalAsyncTask extends AsyncTask<Journal, Void, Void> {
        private final JournalDao journalDao;

        public InsertJournalAsyncTask(JournalDao journalDao) {
            this.journalDao = journalDao;
        }

        @Override
        protected Void doInBackground(Journal... journal) {
            journalDao.insert(journal[0]);
            return null;
        }
    }

    private static class DeleteJournalAsyncTask extends AsyncTask<Journal, Void, Void> {
        private final JournalDao journalDao;

        public DeleteJournalAsyncTask(JournalDao journalDao) {
            this.journalDao = journalDao;
        }

        @Override
        protected Void doInBackground(Journal... journal) {
            journalDao.delete(journal[0]);
            return null;
        }
    }
}
