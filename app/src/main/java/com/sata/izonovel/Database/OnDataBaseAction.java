package com.sata.izonovel.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sata.izonovel.Database.model.Session;

import java.util.List;

@Dao
public interface OnDataBaseAction {
    @Query("SELECT * FROM Session LIMIT 1")
    Session getSessions();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSessions(Session sessions);

    @Query("DELETE FROM Session WHERE userID = :userID")
    void deleteSessionsById(String userID);

    @Query("DELETE FROM Session")
    void clearSessionList();
}
