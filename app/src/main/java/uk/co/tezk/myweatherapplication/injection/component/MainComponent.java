package uk.co.tezk.myweatherapplication.injection.component;

import dagger.Component;
import uk.co.tezk.myweatherapplication.MainActivity;
import uk.co.tezk.myweatherapplication.injection.modules.ApplicationModule;
import uk.co.tezk.myweatherapplication.injection.modules.NetworkModule;
import uk.co.tezk.myweatherapplication.injection.modules.PresenterModule;
import uk.co.tezk.myweatherapplication.injection.modules.SchedulerModule;

/**
 * Created by tezk on 11/06/17.
 */
@Component(modules = {ApplicationModule.class, NetworkModule.class, PresenterModule.class, SchedulerModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity) ;
}
