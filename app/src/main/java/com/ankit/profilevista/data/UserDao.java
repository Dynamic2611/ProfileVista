package com.ankit.profilevista.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ankit.profilevista.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    LiveData<User> getUserById(int id);

    @Query("DELETE FROM users")
    void deleteAll();
}
