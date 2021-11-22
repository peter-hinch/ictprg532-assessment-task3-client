package dev.peterhinch.assessmenttask3.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import dev.peterhinch.assessmenttask3.room.entities.HighScore;

@Dao
public interface HighScoreDao {
  // Create
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertHighScore(HighScore... highScores);

  // Read all
  @Query("SELECT * FROM highscore")
  List<HighScore> getAllHighScores();
}
