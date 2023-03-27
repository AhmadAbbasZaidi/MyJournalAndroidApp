package com.assignment.myandroidjournal.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.myandroidjournal.R;
import com.assignment.myandroidjournal.model.database.datamodels.Journal;
import com.assignment.myandroidjournal.view.interfaces.ListItem;
import com.assignment.myandroidjournal.view.utils.DateFormatUtil;

import java.util.ArrayList;
import java.util.List;

public class JournalCustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LAYOUT_HEADER= 0;
    private static final int LAYOUT_SUB_HEADER= 1;
    private static final int LAYOUT_ITEM = 2;

    private LayoutInflater inflater;
    private Context context;
    private List<ListItem> list;

    public JournalCustomAdapter(Context context){

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void updateList(List<ListItem> listItems) {
        this.list.clear();
        this.list.addAll(listItems);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if(list.get(position).isHeader()) {
            return LAYOUT_HEADER;
        }else if(list.get(position).isSubHeader()) {
            return LAYOUT_SUB_HEADER;
        }else
            return LAYOUT_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        if(viewType==LAYOUT_HEADER){
            View view = inflater.inflate(R.layout.recycler_item_header, parent, false);
            holder = new HeaderViewHolder(view);
        }else if(viewType==LAYOUT_SUB_HEADER){
            View view = inflater.inflate(R.layout.recycler_item_sub_header, parent, false);
            holder = new SubHeaderViewHolder(view);
        }else {
            View view = inflater.inflate(R.layout.recycler_item_journal, parent, false);
            holder = new ItemViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder.getItemViewType()== LAYOUT_HEADER)
        {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.mood.setBackgroundColor(list.get(position).getJournal().getMood());
            headerViewHolder.monthCount.setText(list.get(position).getItemCount()+"");
            headerViewHolder.dayCount.setText(list.get(position).getSubItemCount()+"");
            headerViewHolder.monthHeaderLayout.setBackgroundColor(list.get(position).getOverallMood());
            headerViewHolder.month.setText(DateFormatUtil.getMonth(list.get(position).getJournal().getTimestamp()));
            headerViewHolder.day.setText(DateFormatUtil.getDay(list.get(position).getJournal().getTimestamp()));
            headerViewHolder.time.setText(DateFormatUtil.getTime(list.get(position).getJournal().getTimestamp()));
            headerViewHolder.description.setText(list.get(position).getJournal().getDescription());
        }
        else if(holder.getItemViewType()== LAYOUT_SUB_HEADER)
        {
            SubHeaderViewHolder subHeaderViewHolder = (SubHeaderViewHolder) holder;
            subHeaderViewHolder.mood.setBackgroundColor(list.get(position).getJournal().getMood());
            subHeaderViewHolder.dayCount.setText(list.get(position).getSubItemCount()+"");
            subHeaderViewHolder.day.setText(DateFormatUtil.getDay(list.get(position).getJournal().getTimestamp()));
            subHeaderViewHolder.time.setText(DateFormatUtil.getTime(list.get(position).getJournal().getTimestamp()));
            subHeaderViewHolder.description.setText(list.get(position).getJournal().getDescription());
        }
        else if(holder.getItemViewType()== LAYOUT_ITEM)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mood.setBackgroundColor(list.get(position).getJournal().getMood());
            itemViewHolder.time.setText(DateFormatUtil.getTime(list.get(position).getJournal().getTimestamp()));
            itemViewHolder.description.setText(list.get(position).getJournal().getDescription());
        }

    }


    class HeaderViewHolder extends RecyclerView.ViewHolder{

        View mood;
        TextView time, description, month, monthCount, day, dayCount;
        LinearLayout monthHeaderLayout, dayHeaderLayout;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mood = itemView.findViewById(R.id.mood);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.description);
            monthHeaderLayout = itemView.findViewById(R.id.month_header_layout);
            month = itemView.findViewById(R.id.month_header);
            monthCount = itemView.findViewById(R.id.month_count);
            dayHeaderLayout = itemView.findViewById(R.id.day_header_layout);
            day = itemView.findViewById(R.id.day_header);
            dayCount = itemView.findViewById(R.id.day_count);
        }

    }
    class SubHeaderViewHolder extends RecyclerView.ViewHolder{

        View mood;
        TextView time, description, day, dayCount;
        LinearLayout dayHeaderLayout;


        public SubHeaderViewHolder(View itemView) {
            super(itemView);
            mood = itemView.findViewById(R.id.mood);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.description);
            dayHeaderLayout = itemView.findViewById(R.id.day_header_layout);
            day = itemView.findViewById(R.id.day_header);
            dayCount = itemView.findViewById(R.id.day_count);
        }

    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        View mood;
        TextView time, description;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mood = itemView.findViewById(R.id.mood);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.description);
        }

    }

}
