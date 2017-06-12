package uk.co.tezk.myweatherapplication.injection.component;

import dagger.Component;
import uk.co.tezk.myweatherapplication.injection.modules.NetworkModule;
import uk.co.tezk.myweatherapplication.presenter.WeatherPresenterImpl;

/**
 * Created by tezk on 11/06/17.
 */
@Component(modules = NetworkModule.class, dependencies = ApplicationComponent.class)
public interface NetworkComponent {
    void inject(WeatherPresenterImpl weatherPresenter) ;
}
