package dev.peterhinch.assessmenttask3.room;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.List;

import dev.peterhinch.assessmenttask3.room.dao.HighScoreDao;
import dev.peterhinch.assessmenttask3.room.entities.Converters;
import dev.peterhinch.assessmenttask3.room.entities.HighScore;

@Database(
    entities = {HighScore.class},
    version = 1,
    exportSchema = false
)

// This is where any necessary type converters can be declared (e.g. Date to Long)
// Reference: https://developer.android.com/training/data-storage/room/referencing-data
@TypeConverters({Converters.class})

public abstract class LocalHighScoreDb extends RoomDatabase {
  private static final String TAG = "HighScoreDb";

  public abstract HighScoreDao highScoreDao();

  private static LocalHighScoreDb localHighScoreDb;

  private static LocalHighScoreDb getInstance(final Context context) {
    if (localHighScoreDb == null) {
      localHighScoreDb = Room.databaseBuilder(
          context.getApplicationContext(),
          LocalHighScoreDb.class,
          "highscore_room.db").allowMainThreadQueries().build();
    }
    return localHighScoreDb;
  }

  // Add a new HighScore to the Room database.
  public static void highScoreCreate(final Context context, HighScore highScore) {
    try {
      LocalHighScoreDb db = getInstance(context);
      db.highScoreDao().insertHighScores(highScore);
    } catch (Exception ex) {
      Log.d(TAG, "Exception occurred while adding HighScore: " + ex);
    }
  }

  // View all HighScores in the Room database.
  public static List<HighScore> highScoreReadAll(Context context) {
    List<HighScore> highScores = null;

    try {
      LocalHighScoreDb db = getInstance(context);
      highScores = db.highScoreDao().getAllHighScores();
    } catch (Exception ex) {
      Log.d(TAG, "Exception occurred while reading HighScores: " + ex);
    }
    return highScores;
  }

}
