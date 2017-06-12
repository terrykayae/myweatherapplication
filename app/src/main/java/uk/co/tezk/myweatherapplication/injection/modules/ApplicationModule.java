package uk.co.tezk.myweatherapplication.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.tezk.myweatherapplication.WeatherApplication;

/**
 * Created by tezk on 11/06/17.
 */
@Module
@Singleton
public class ApplicationModule {
    WeatherApplication weatherApplication;

    public ApplicationModule(WeatherApplication application) {
        this.weatherApplication = application;
    }


    @Provides

    public WeatherApplication provideApplication() {
        return weatherApplication;
    }
}
