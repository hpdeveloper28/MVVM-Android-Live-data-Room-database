package com.mvvmsample.itemlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvvmsample.R;
import com.mvvmsample.db.ItemModel;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<ItemModel> itemModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<ItemModel> itemModelList, View.OnLongClickListener longClickListener) {
        this.itemModelList = itemModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        ItemModel itemModel = itemModelList.get(position);
        holder.itemTextView.setText(itemModel.getItemName());
        holder.itemView.setTag(itemModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public void addItems(List<ItemModel> borrowModelList) {
        this.itemModelList = borrowModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
        }
    }
}