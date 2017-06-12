package uk.co.tezk.myweatherapplication.presenter;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import uk.co.tezk.myweatherapplication.injection.ISchedulerProvider;
import uk.co.tezk.myweatherapplication.model.Constants;
import uk.co.tezk.myweatherapplication.model.Example;

/**
 * Created by tezk on 11/06/17.
 */

public class WeatherPresenterImpl implements IWeatherPresenter.IPresenter {
    IWeatherPresenter.IView mView;
    IWeatherApi interactor;
    ISchedulerProvider mSchedulers;

    CompositeDisposable compositeDisposable;

    public WeatherPresenterImpl(IWeatherApi interactor, ISchedulerProvider schedulers) {
        this.interactor = interactor;
        this.mSchedulers = schedulers;
    }

    @Override
    public void bind(IWeatherPresenter.IView view) {

        mView = view;
        if (compositeDisposable == null || compositeDisposable.isDisposed())
            compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unbind() {

        mView = null;
        compositeDisposable.dispose();
    }

    @Override
    public void fetchData() {
        mView.onStartLoading();
        Single<Example> single = interactor.getWeather(Constants.LOCATION, Constants.UNITS, Constants.API_KEY);
        single
                .observeOn(mSchedulers.provideObserverScheduler())
                .subscribeOn(mSchedulers.provideSubscriberScheduler())
                .subscribe(new SingleObserver<Example>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Example example) {
                        mView.showData(example.getList());
                        mView.onLoadingCompleted();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.onError(e);
                        mView.onLoadingCompleted();
                    }
                } );
    }
}
