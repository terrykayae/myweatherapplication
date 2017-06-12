package uk.co.tezk.myweatherapplication.presenter;

import uk.co.tezk.myweatherapplication.model.List;

/**
 * Created by tezk on 11/06/17.
 */

public interface IWeatherPresenter {
    interface IView {
        void showData(java.util.List <List> weatherList) ;
        void onStartLoading() ;
        void onLoadingCompleted() ;
        void onError(Throwable e) ;
    }

    interface IPresenter {
        void bind(IView view) ;
        void unbind() ;
        void fetchData() ;
    }
}
