package uk.co.tezk.myweatherapplication.injection.modules;

import dagger.Module;
import dagger.Provides;
import uk.co.tezk.myweatherapplication.injection.ISchedulerProvider;
import uk.co.tezk.myweatherapplication.injection.SchedulerProviderImpl;

/**
 * Created by tezk on 12/06/17.
 */
@Module
public class SchedulerModule {
    @Provides
    ISchedulerProvider provideScheduler() {
        return new SchedulerProviderImpl();
    }
}
