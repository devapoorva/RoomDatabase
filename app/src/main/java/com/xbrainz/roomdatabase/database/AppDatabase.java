package com.xbrainz.roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.xbrainz.roomdatabase.dao.UserDao;
import com.xbrainz.roomdatabase.models.UserModel;

@Database(entities = {UserModel.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context)
    {
        if(appDatabase==null)
        {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return appDatabase;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}
