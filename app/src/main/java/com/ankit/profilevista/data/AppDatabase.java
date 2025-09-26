package com.ankit.profilevista.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ankit.profilevista.model.User;
import com.ankit.profilevista.model.Converters;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "dummy_users_db";
    private static volatile AppDatabase instance;

    public abstract UserDao userDao();

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}