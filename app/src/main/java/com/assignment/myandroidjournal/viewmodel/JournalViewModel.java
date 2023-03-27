package com.assignment.myandroidjournal.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.model.repository.JournalRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class JournalViewModel extends ViewModel {
    private final JournalRepository journalRepository;
    private final LiveData<List<Journal>> journals;

    @Inject
    public JournalViewModel(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
        journals = journalRepository.getAllJournals();
    }

    public void insert(Journal journal) {
        journalRepository.insert(journal);
    }

    public void delete(Journal journal) {
        journalRepository.delete(journal);
    }

    public LiveData<List<Journal>> getAllJournals() {
        return journals;
    }

    public void initializeEmptyDB() {
        journalRepository.initializeEmptyDB();

    }
}
