package com.mvvmsample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by hirenpatel on 04/10/17.
 */

@Dao
public interface ItemDao {

    @Query("Select * from ItemModel")
    LiveData<List<ItemModel>> getItems();

    @Insert(onConflict = REPLACE)
    void addItem(ItemModel itemModel);

    @Delete
    void deleteItem(ItemModel itemModel);
}
