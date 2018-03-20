package com.example.lucvaladao.entrevistapopcode.mvp.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.lucvaladao.entrevistapopcode.db.MyApp;
import com.example.lucvaladao.entrevistapopcode.entity.Character;
import com.example.lucvaladao.entrevistapopcode.entity.CharacterBook;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucvaladao on 3/19/18.
 */

class HomeInteractorImpl implements HomeInteractor{

    private Retrofit mRetrofit = new Retrofit
            .Builder()
            .baseUrl(HomeRetrofit.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private HomeRetrofit mRetrofitService = mRetrofit.create(HomeRetrofit.class);

    @Override
    public void getCharacterList(final GetCharacterListListener listener) {
        mRetrofitService.getCharacterList().enqueue(new Callback<CharacterBook>() {
            @Override
            public void onResponse(Call<CharacterBook> call, Response<CharacterBook> response) {
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String controleFluxo;
                        String auxSubString = response.body().getNext();
                        if (auxSubString != null) {
                            controleFluxo = auxSubString
                                    .substring(auxSubString.indexOf('=') + 1, auxSubString.length());
                        } else {
                            controleFluxo = "OFB"; //Out of bounds
                        }
                        listener.onGetCharacterListSuccess(
                                response.body().getResults(),
                                controleFluxo,
                                response.body().getCount());
                    } catch (NullPointerException npe) {
                        Log.e("Error", "Chracters loading error - NPE!");
                    }
                }
            }

            @Override
            public void onFailure(Call<CharacterBook> call, Throwable t) {
                Log.e("Error", "Chracters loading error! - Retrofit Failure");
                listener.onGetCharacterListFailure(new Exception());
            }
        });
    }

    @Override
    public void getCharacterNextPage(final List<Character> characterList, final String controleFluxo, final GetCharacterNextPageListener listener) {
        mRetrofitService.getCharacterListNextPage(controleFluxo).enqueue(new Callback<CharacterBook>() {
            @Override
            public void onResponse(Call<CharacterBook> call, Response<CharacterBook> response) {
                if (response.code() == 200 && response.body() != null) {
                    try {
                        characterList.addAll(response.body().getResults());
                        String controleFluxoAux;
                        String auxSubString = response.body().getNext();
                        if (auxSubString != null) {
                            controleFluxoAux = auxSubString
                                    .substring(auxSubString.indexOf('=') + 1, auxSubString.length());
                        } else {
                            controleFluxoAux = "OFB"; //Out of bounds
                        }
                        listener.onGetCharacterNextPageSuccess(characterList,
                                controleFluxoAux,
                                response.body().getCount());
                    } catch (NullPointerException npe) {
                        Log.e("Error", "Chracters loading error - NPE!");
                    }
                }
            }

            @Override
            public void onFailure(Call<CharacterBook> call, Throwable t) {
                Log.e("Error", "Chracters loading error! - Retrofit Failure");
                listener.onGetCharacterNextPageFailure(new Exception());
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void saveToDB(final List<Character> characterList) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                MyApp.database.characterDAO().insert(characterList);
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {}
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getFromDB(final GetFromDBListener listener) {
        new  AsyncTask<Void, Void, List<Character>>() {
            @Override
            protected List<Character> doInBackground(Void... params) {
                return MyApp.database.characterDAO().getAllCharacters();
            }

            @Override
            protected void onPostExecute(List<Character> characterList) {
                listener.onGetFromDBSuccess(characterList);
            }
        }.execute();
    }

}
