package com.xbrainz.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xbrainz.roomdatabase.models.UserModel;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<UserModel> getAllUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserModel userModel);


}
