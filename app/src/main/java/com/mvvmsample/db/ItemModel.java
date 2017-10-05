package com.mvvmsample.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by hirenpatel on 04/10/17.
 */

@Entity
public class ItemModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;

    public ItemModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
