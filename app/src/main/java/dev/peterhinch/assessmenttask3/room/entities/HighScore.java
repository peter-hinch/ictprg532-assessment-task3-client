package dev.peterhinch.assessmenttask3.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "highscore")
public class HighScore {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  public int id;

  @ColumnInfo(name = "score")
  public int score;

  @ColumnInfo(name = "date")
  public Date date;

  public HighScore() {}

  @Ignore
  public HighScore (int score, Date date) {
    this.score = score;
    this.date = date;
  }
}
