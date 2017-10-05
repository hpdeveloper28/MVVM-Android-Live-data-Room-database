package com.mvvmsample.itemlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.mvvmsample.MyApplication;
import com.mvvmsample.db.AppDatabase;
import com.mvvmsample.db.ItemModel;

import java.util.List;

/**
 * Created by hirenpatel on 04/10/17.
 */

public class ItemListModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<ItemModel>> liveItemList;

    public ItemListModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getAppDatabase(application);
        liveItemList = appDatabase.getItemDao().getItems();
    }

    public LiveData<List<ItemModel>> getLiveItemList() {
        return liveItemList;
    }

    public void deleteItem(final ItemModel itemModel) {
        MyApplication.runInBackground(new Runnable() {
            @Override
            public void run() {
                appDatabase.getItemDao().deleteItem(itemModel);
            }
        });

    }

}
