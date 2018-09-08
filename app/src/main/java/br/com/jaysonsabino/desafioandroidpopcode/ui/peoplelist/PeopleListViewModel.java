package br.com.jaysonsabino.desafioandroidpopcode.ui.peoplelist;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import br.com.jaysonsabino.desafioandroidpopcode.database.AppDatabase;
import br.com.jaysonsabino.desafioandroidpopcode.database.DatabaseFactory;
import br.com.jaysonsabino.desafioandroidpopcode.entities.Character;
import br.com.jaysonsabino.desafioandroidpopcode.repository.FavoritesRepository;
import br.com.jaysonsabino.desafioandroidpopcode.repository.PeopleRepository;
import br.com.jaysonsabino.desafioandroidpopcode.services.starwarsfavorites.StarWarsFavoritesService;
import br.com.jaysonsabino.desafioandroidpopcode.services.starwarsfavorites.StarWarsFavoritesServiceFactory;
import br.com.jaysonsabino.desafioandroidpopcode.services.swapi.PeopleService;
import br.com.jaysonsabino.desafioandroidpopcode.services.swapi.ServiceFactory;

public class PeopleListViewModel extends ViewModel {

    private final PeopleRepository peopleRepository;
    private MutableLiveData<PeopleListFilter> peopleListFilter = new MutableLiveData<>();
    private LiveData<PagedList<Character>> charactersPagedList;

    private PeopleListViewModel(PeopleRepository peopleRepository, FavoritesRepository favoritesRepository) {
        this.peopleRepository = peopleRepository;

        favoritesRepository.syncFavorites();

        peopleRepository.deleteLocalCharactersCacheIfConnected();

        initPagedList();

        search(null);
    }

    public LiveData<PagedList<Character>> getCharactersPagedList() {
        return charactersPagedList;
    }

    private void initPagedList() {
        charactersPagedList = Transformations.switchMap(peopleListFilter, new Function<PeopleListFilter, LiveData<PagedList<Character>>>() {
            @Override
            public LiveData<PagedList<Character>> apply(PeopleListFilter peopleListFilter) {
                return peopleRepository.getPagedList(peopleListFilter.name, peopleListFilter.showOnlyFavorites);
            }
        });
    }

    public void search(String name) {
        peopleListFilter.setValue(new PeopleListFilter(name, showOnlyFavorites()));
    }

    public void showOnlyFavorites(boolean showOnlyFavorites) {
        this.peopleListFilter.setValue(new PeopleListFilter(null, showOnlyFavorites));
    }

    public boolean showOnlyFavorites() {
        return peopleListFilter.getValue() != null && peopleListFilter.getValue().showOnlyFavorites;
    }

    public static class Factory implements ViewModelProvider.Factory {

        private Application app;
        private Executor executor;
        private AppDatabase database;
        private PeopleService peopleService;
        private StarWarsFavoritesService favoritesService;

        public Factory(Application app, Executor executor) {
            this.app = app;
            this.executor = executor;
            database = new DatabaseFactory().getDatabase(app);
            peopleService = new ServiceFactory().getPeopleService();
            favoritesService = new StarWarsFavoritesServiceFactory().getService();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            PeopleRepository peopleRepository = new PeopleRepository(app, database, peopleService, executor);
            FavoritesRepository favoritesRepository = new FavoritesRepository(app, database, favoritesService, executor);

            return (T) new PeopleListViewModel(peopleRepository, favoritesRepository);
        }
    }

    static class PeopleListFilter {

        String name;
        boolean showOnlyFavorites;

        PeopleListFilter(String name, boolean showOnlyFavorites) {
            this.name = name;
            this.showOnlyFavorites = showOnlyFavorites;
        }
    }
}
