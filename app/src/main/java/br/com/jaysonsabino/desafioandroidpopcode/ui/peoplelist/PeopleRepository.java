package br.com.jaysonsabino.desafioandroidpopcode.ui.peoplelist;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;

import br.com.jaysonsabino.desafioandroidpopcode.database.AppDatabase;
import br.com.jaysonsabino.desafioandroidpopcode.entities.Character;
import br.com.jaysonsabino.desafioandroidpopcode.services.swapi.ServiceFactory;
import br.com.jaysonsabino.desafioandroidpopcode.util.NetworkHelper;

public class PeopleRepository {

    private Activity activity;
    private AppDatabase database;
    private Executor executor;
    private PeopleBoundaryCallback boundaryCallback;

    PeopleRepository(Activity activity, AppDatabase database, Executor executor) {
        this.activity = activity;
        this.database = database;
        this.executor = executor;

        initBoundaryCallback();
    }

    public void deleteLocalCharactersCacheIfConnected() {
        if (!NetworkHelper.isConnected(activity)) return;

        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.getCharacterDAO().deleteAll();
            }
        });
    }

    public LiveData<PagedList<Character>> getPagedList(String queryName) {
        DataSource.Factory<Integer, Character> dataSourceFactory;

        if (queryName == null) {
            dataSourceFactory = database.getCharacterDAO().findAll();
        } else {
            dataSourceFactory = database.getCharacterDAO().findByName("%" + queryName + "%");
        }

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(8)
                .setPrefetchDistance(24)
                .setInitialLoadSizeHint(24)
                .setEnablePlaceholders(false)
                .build();

        return new LivePagedListBuilder<>(dataSourceFactory, config)
                .setBoundaryCallback(boundaryCallback)
                .build();
    }

    private void initBoundaryCallback() {
        boundaryCallback = new PeopleBoundaryCallback(
                activity,
                database,
                new ServiceFactory().getPeopleService(),
                executor
        );
    }
}
