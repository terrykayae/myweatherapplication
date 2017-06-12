package uk.co.tezk.myweatherapplication;

import android.app.Application;

import uk.co.tezk.myweatherapplication.injection.component.ApplicationComponent;
import uk.co.tezk.myweatherapplication.injection.component.DaggerApplicationComponent;
import uk.co.tezk.myweatherapplication.injection.component.DaggerMainComponent;
import uk.co.tezk.myweatherapplication.injection.component.DaggerNetworkComponent;
import uk.co.tezk.myweatherapplication.injection.component.MainComponent;
import uk.co.tezk.myweatherapplication.injection.component.NetworkComponent;
import uk.co.tezk.myweatherapplication.injection.modules.ApplicationModule;
import uk.co.tezk.myweatherapplication.injection.modules.NetworkModule;
import uk.co.tezk.myweatherapplication.injection.modules.PresenterModule;

/**
 * Created by tezk on 11/06/17.
 */

public class WeatherApplication extends Application {

    private static WeatherApplication application;

    ApplicationComponent applicationComponent;
    NetworkComponent networkComponent;
    MainComponent mainComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public static WeatherApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        // Initalise and set up for Dagger injection
        ApplicationModule applicationModule = new ApplicationModule(this);
        NetworkModule networkModule = new NetworkModule();
        PresenterModule presenterModule = new PresenterModule();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(applicationModule)
                .build();
        networkComponent = DaggerNetworkComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(networkModule)
                .build();
        mainComponent = DaggerMainComponent.builder()
                .networkModule(networkModule)
                .applicationModule(applicationModule)
                .presenterModule(presenterModule)
                .build();

    }


}
