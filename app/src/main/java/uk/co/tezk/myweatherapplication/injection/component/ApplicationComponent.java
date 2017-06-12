package uk.co.tezk.myweatherapplication.injection.component;

import dagger.Component;
import uk.co.tezk.myweatherapplication.WeatherApplication;
import uk.co.tezk.myweatherapplication.injection.modules.ApplicationModule;

/**
 * Created by tezk on 11/06/17.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    WeatherApplication provideApplication() ;
}
