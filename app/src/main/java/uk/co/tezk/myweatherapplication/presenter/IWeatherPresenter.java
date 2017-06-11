package uk.co.tezk.myweatherapplication.presenter;

/**
 * Created by tezk on 11/06/17.
 */

public interface IWeatherPresenter {
    interface IView {
        void showData() ;
        void onStartLoading() ;
        void onLoadingCompleted() ;
    }

    interface IPresenter {
        void bind(IView view) ;
        void unbind() ;
        void fetchData() ;
    }
}
