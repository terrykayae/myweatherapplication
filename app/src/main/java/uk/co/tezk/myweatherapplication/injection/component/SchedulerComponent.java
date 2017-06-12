package uk.co.tezk.myweatherapplication.injection.component;

import dagger.Component;
import uk.co.tezk.myweatherapplication.injection.ISchedulerProvider;
import uk.co.tezk.myweatherapplication.injection.modules.SchedulerModule;

/**
 * Created by tezk on 12/06/17.
 */
@Component(modules = SchedulerModule.class)
public interface SchedulerComponent {
    ISchedulerProvider schedulerProvider();
}
