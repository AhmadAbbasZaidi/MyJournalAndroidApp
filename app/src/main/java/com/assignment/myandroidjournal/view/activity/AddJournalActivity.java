package com.assignment.myandroidjournal.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.myandroidjournal.R;
import com.assignment.myandroidjournal.databinding.ActivityAddJournalBinding;
import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.viewmodel.JournalViewModel;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddJournalActivity extends AppCompatActivity {

    private ActivityAddJournalBinding binding;
    private JournalViewModel journalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        setListeners();
        initViewModel();
    }

    private void initialize() {
        binding = ActivityAddJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
    }

    private void setListeners() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void initViewModel() {
        journalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);
    }

    private void validate() {
        Journal journal = new Journal();
        if (binding.radioGroup.getCheckedRadioButtonId() == R.id.red) {
            journal.setMood(getColor(R.color.red));
        } else if (binding.radioGroup.getCheckedRadioButtonId() == R.id.yellow) {
            journal.setMood(getColor(R.color.yellow));
        } else if (binding.radioGroup.getCheckedRadioButtonId() == R.id.green) {
            journal.setMood(getColor(R.color.green));
        } else {
            Snackbar.make(binding.radioGroup, getString(R.string.plz_select_mood), Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.description.getText())) {
            Snackbar.make(binding.radioGroup, getString(R.string.plz_enter_description), Snackbar.LENGTH_SHORT).show();
            return;

        } else {
            journal.setDescription(binding.description.getText().toString());
        }
        journal.setTimestamp(System.currentTimeMillis());

        journalViewModel.insert(journal);
        finish();
    }
}