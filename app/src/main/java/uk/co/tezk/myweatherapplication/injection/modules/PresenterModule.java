package uk.co.tezk.myweatherapplication.injection.modules;

import dagger.Module;
import dagger.Provides;
import uk.co.tezk.myweatherapplication.injection.ISchedulerProvider;
import uk.co.tezk.myweatherapplication.presenter.IWeatherApi;
import uk.co.tezk.myweatherapplication.presenter.IWeatherPresenter;
import uk.co.tezk.myweatherapplication.presenter.WeatherPresenterImpl;

/**
 * Created by tezk on 12/06/17.
 */
@Module
public class PresenterModule {
    @Provides
    IWeatherPresenter.IPresenter providePresenter(IWeatherApi interactor, ISchedulerProvider schedulerProvider) {
        return new WeatherPresenterImpl(interactor, schedulerProvider);
    }
}
