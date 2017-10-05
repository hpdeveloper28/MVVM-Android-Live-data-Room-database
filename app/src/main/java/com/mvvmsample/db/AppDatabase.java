package com.mvvmsample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by hirenpatel on 04/10/17.
 */

@Database(entities = {ItemModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "item_db").build();
        }
        return INSTANCE;
    }

    public abstract ItemDao getItemDao();


    public void destroyDB(){
        INSTANCE = null;
    }

}
