package uk.co.tezk.myweatherapplication.presenter;

/**
 * Created by tezk on 11/06/17.
 */

public class WeatherPresenterImpl implements IWeatherPresenter.IPresenter {
    IWeatherPresenter.IView mView;

    @Override
    public void bind(IWeatherPresenter.IView view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void fetchData() {
        mView.onStartLoading();
        mView.showData();
        mView.onLoadingCompleted();
    }
}
