package com.mvvmsample.additem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.mvvmsample.db.AppDatabase;
import com.mvvmsample.db.ItemModel;

/**
 * Created by hirenpatel on 04/10/17.
 */

public class AddItemModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddItemModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getAppDatabase(application);
    }

    public void addItem(ItemModel itemModel){

        appDatabase.getItemDao().addItem(itemModel);
    }
}
