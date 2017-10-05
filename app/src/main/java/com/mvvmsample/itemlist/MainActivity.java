package com.mvvmsample.itemlist;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mvvmsample.AppCompatLifecycleActivity;
import com.mvvmsample.R;
import com.mvvmsample.additem.AddItemActivity;
import com.mvvmsample.db.AppDatabase;
import com.mvvmsample.db.ItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatLifecycleActivity implements View.OnLongClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtEmptyView)
    AppCompatTextView txtEmptyView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ItemListModel itemListModel;

    @OnClick(R.id.addItem)
    public void onAddItem(View view) {
        startActivity(new Intent(MainActivity.this, AddItemActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<ItemModel>(), this);
        recyclerView.setAdapter(recyclerViewAdapter);

        itemListModel = ViewModelProviders.of(this).get(ItemListModel.class);
        itemListModel.getLiveItemList().observe(MainActivity.this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemModel> itemModels) {
                recyclerViewAdapter.addItems(itemModels);
                recyclerView.setVisibility(!itemModels.isEmpty() ? View.VISIBLE : View.GONE);
                txtEmptyView.setVisibility(itemModels.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });

    }


    @Override
    protected void onDestroy() {
        AppDatabase.getAppDatabase(this).destroyDB();
        super.onDestroy();
    }

    @Override
    public boolean onLongClick(View view) {
        ItemModel itemModel = (ItemModel) view.getTag();
        itemListModel.deleteItem(itemModel);
        return true;
    }
}
