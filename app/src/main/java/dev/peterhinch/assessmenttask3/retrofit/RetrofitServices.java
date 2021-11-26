package dev.peterhinch.assessmenttask3.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import dev.peterhinch.assessmenttask3.room.entities.HighScore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {
  private final String TAG = this.getClass().getSimpleName();

  // Static variable to define retrofitServicesInstance, which will be a singleton.
  private static RetrofitServices retrofitServicesInstance = null;
  private static RemoteHighScoreDb service;

  // Private constructor only accessible from this class.
  private RetrofitServices() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.65.1:44327/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    service = retrofit.create(RemoteHighScoreDb.class);
  }

  // Static method to retrieve a RetrofitServices instance.
  public static RetrofitServices getInstance() {
    if (retrofitServicesInstance == null) {
      retrofitServicesInstance = new RetrofitServices();
    }
    return retrofitServicesInstance;
  }

  // Create.
  public void highScoreCreate(@NonNull HighScore highScore,
                              final ResultsHandler resultsHandler) {
    // Prepare an API call.
    highScore.setId(0);
    Call<HighScore> highScoreCreate = service.highScoresCreate(highScore);
    // Call the API.
    highScoreCreate.enqueue(new Callback<HighScore>() {
      // When the API is called with successful results.
      @Override
      public void onResponse(@NonNull Call<HighScore> call, @NonNull Response<HighScore> response) {
        try {
          HighScore highScore1 = response.body();
          resultsHandler.createOnResponseHandler(highScore1);
        }
        catch (Exception ex) {
          Log.e(TAG, "An exception occurred while creating HighScore: " + ex);
        }
      }
      // When the API is called, but fails.
      @Override
      public void onFailure(@NonNull Call<HighScore> call, @NonNull Throwable t) {
        resultsHandler.onFailureHandler(t);
      }
    });
  }

  // Read all.
  public void highScoresReadAll(final ResultsHandler resultsHandler) {
    // Prepare an API call.
    Call<List<HighScore>> highScoresReadAll = service.highScoresReadAll();
    // Call the API.
    highScoresReadAll.enqueue(new Callback<List<HighScore>>() {
      // When the API is called with successful results.
      @Override
      public void onResponse(@NonNull Call<List<HighScore>> call, @NonNull Response<List<HighScore>> response) {
        try {
          List<HighScore> highScoreList = response.body();
          resultsHandler.readAllOnResponseHandler(highScoreList);
        }
        catch (Exception ex) {
          Log.e(TAG, "An exception occurred while reading in HighScores: " + ex);
        }
      }
      // When the API is called, but fails.
      @Override
      public void onFailure(@NonNull Call<List<HighScore>> call, @NonNull Throwable t) {
        resultsHandler.onFailureHandler(t);
      }
    });
  }

  // Provide an interface for interaction with other classes.
  public interface ResultsHandler {
    void createOnResponseHandler(HighScore highScore);
    void readAllOnResponseHandler(List<HighScore> highScoreList);
    void onFailureHandler(Throwable throwable);
  }
}
