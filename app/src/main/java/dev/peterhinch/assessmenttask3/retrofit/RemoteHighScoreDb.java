package dev.peterhinch.assessmenttask3.retrofit;

import java.util.List;

import dev.peterhinch.assessmenttask3.room.entities.HighScore;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RemoteHighScoreDb {
  @POST("highscores")
  Call<HighScore> highScoresCreate(@Body HighScore highScore);

  @GET("highscores")
  Call<List<HighScore>> highScoresReadAll();
}
