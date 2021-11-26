package dev.peterhinch.assessmenttask3.room.entities;

import androidx.annotation.NonNull;
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

  @Ignore
  public HighScore (int id, int score, Date date) {
    this.id = id;
    this.score = score;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @NonNull
  @Override
  public String toString() {
    return "id: " + this.id + "\nscore: " + this.score + "\ndate: " + this.date;
  }
}
