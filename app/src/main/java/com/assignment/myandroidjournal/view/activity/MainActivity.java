package com.assignment.myandroidjournal.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.assignment.myandroidjournal.R;
import com.assignment.myandroidjournal.databinding.ActivityJournalBinding;
import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.view.adapters.JournalCustomAdapter;
import com.assignment.myandroidjournal.view.interfaces.ListItem;
import com.assignment.myandroidjournal.view.model.HeaderModel;
import com.assignment.myandroidjournal.view.model.ItemModel;
import com.assignment.myandroidjournal.view.model.SubHeaderModel;
import com.assignment.myandroidjournal.view.utils.DateFormatUtil;
import com.assignment.myandroidjournal.viewmodel.JournalViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityJournalBinding binding;
    private JournalViewModel journalViewModel;
    private JournalCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        setListeners();
        initRecyclerView();
        initViewModel();

    }

    private void initialize() {
        binding = ActivityJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
    }


    private void setListeners() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, AddJournalActivity.class));
            }
        });
    }

    private void initViewModel() {
        journalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);

        journalViewModel.getAllJournals().observe(this, new Observer<List<Journal>>() {
            @Override
            public void onChanged(List<Journal> journals) {
                loadData(journals);
            }
        });
    }

    private void loadData(List<Journal> journals) {
        new ListReordering(journals).execute();
    }

    private void initRecyclerView() {
        adapter = new JournalCustomAdapter(this);
        binding.contentActivityMain.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.contentActivityMain.rv.setAdapter(adapter);
//        binding.contentActivityMain.rv.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fetch_dummy_data) {
            journalViewModel.initializeEmptyDB();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListReordering extends AsyncTask<Void, Void, List<ListItem>> {
        List<Journal> journals;
        ListItem item;

        public ListReordering(List<Journal> journals) {
            this.journals = journals;
        }

        @Override
        protected List<ListItem> doInBackground(Void... voids) {
            List<ListItem> modifiedList = new ArrayList<>();

            String monthYear = "";
            String date = "";
            List<Integer> headerIndex = new ArrayList<>();
            List<Integer> subHeaderIndex = new ArrayList<>();

            for (Journal journal : journals) {
                if (!monthYear.equals(DateFormatUtil.getMonthYear(journal.getTimestamp()))) {
                    HeaderModel headerModel = new HeaderModel();
                    headerModel.setJournal(journal);
                    headerIndex.add(modifiedList.size());
                    subHeaderIndex.add(modifiedList.size());

                    modifiedList.add(headerModel);

                    monthYear = DateFormatUtil.getMonthYear(journal.getTimestamp());
                    if (TextUtils.isEmpty(date)) {
                        date = DateFormatUtil.getDate(journal.getTimestamp());
                    }

                } else {
                    if (!date.equals(DateFormatUtil.getDate(journal.getTimestamp()))) {
                        SubHeaderModel subHeaderModel = new SubHeaderModel();
                        subHeaderModel.setJournal(journal);
                        subHeaderIndex.add(modifiedList.size());
                        modifiedList.add(subHeaderModel);

                        date = DateFormatUtil.getDate(journal.getTimestamp());
                    } else {
                        ItemModel itemModel = new ItemModel();
                        itemModel.setJournal(journal);
                        modifiedList.add(itemModel);
                    }
                }
            }

            monthYear = "";
            date = "";

            for (int i = 0; i < headerIndex.size(); i++) {
                if (i == headerIndex.size() - 1) {
                    int totalItemCount = 0;
                    int subItemTotalCount = 0;
                    int redCount = 0;
                    int yellowCount = 0;
                    int greenCount = 0;
                    int count = 0;

                    for (int j = headerIndex.get(i); j < modifiedList.size(); j++) {
                        ListItem li = modifiedList.get(j);
                        totalItemCount++;
                        if (li.getJournal().getMood() == getColor(R.color.yellow)) {
                            yellowCount++;
                        } else if (li.getJournal().getMood() == getColor(R.color.red)) {
                            redCount++;
                        } else if (li.getJournal().getMood() == getColor(R.color.green)) {
                            greenCount++;
                        }
                    }
                    if (redCount >= count) {
                        count = redCount;
                        modifiedList.get(headerIndex.get(i)).setOverallMood(getColor(R.color.red));
                    }
                    if (yellowCount >= count) {
                        count = yellowCount;
                        modifiedList.get(headerIndex.get(i)).setOverallMood(getColor(R.color.yellow));
                    }
                    if (greenCount >= count) {
                        count = greenCount;
                        modifiedList.get(headerIndex.get(i)).setOverallMood(getColor(R.color.green));
                    }

                    modifiedList.get(headerIndex.get(i)).setItemCount(totalItemCount);

                } else {
                    int totalItemCount = 0;
                    int redCount = 0;
                    int yellowCount = 0;
                    int greenCount = 0;
                    int count = 0;
                    for (int j = headerIndex.get(i); j < headerIndex.get(i + 1); j++) {
                        ListItem li = modifiedList.get(j);
                        totalItemCount++;
                        if (li.getJournal().getMood() == getColor(R.color.yellow)) {
                            yellowCount++;
                        } else if (li.getJournal().getMood() == getColor(R.color.red)) {
                            redCount++;
                        } else if (li.getJournal().getMood() == getColor(R.color.green)) {
                            greenCount++;
                        }
                    }
                    if (redCount >= count) {
                        count = redCount;
                        modifiedList.get(headerIndex.get(i)).setOverallMood(getColor(R.color.red));
                    }
                    if (yellowCount >= count) {
                        count = yellowCount;
                        modifiedList.get(headerIndex.get(i)).setOverallMood(getColor(R.color.yellow));
                    }
                    if (greenCount >= count) {
                        count = greenCount;
                        modifiedList.get(headerIndex.get(i)).setOverallMood(getColor(R.color.green));
                    }

                    modifiedList.get(headerIndex.get(i)).setItemCount(totalItemCount);
                }

            }

            for (int i = 0; i < subHeaderIndex.size(); i++) {
                if (i == subHeaderIndex.size() - 1) {
                    int totalItemCount = 0;
                    for (int j = subHeaderIndex.get(i); j < modifiedList.size(); j++) {
                        totalItemCount++;
                    }
                    modifiedList.get(subHeaderIndex.get(i)).setSubItemCount(totalItemCount);
                } else {
                    int totalItemCount = 0;
                    for (int j = subHeaderIndex.get(i); j < subHeaderIndex.get(i + 1); j++) {
                        totalItemCount++;
                    }
                    modifiedList.get(subHeaderIndex.get(i)).setSubItemCount(totalItemCount);
                }
            }
            return modifiedList;
        }

        @Override
        protected void onPostExecute(List<ListItem> listItems) {
            super.onPostExecute(listItems);

            adapter.updateList(listItems);
            Toast.makeText(MainActivity.this, "size = " + journals.size(), Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();

        }

    }
}