package uk.co.tezk.myweatherapplication.injection;

import io.reactivex.Scheduler;

/**
 * Created by tezk on 12/06/17.
 */

public interface ISchedulerProvider {
    Scheduler provideSubscriberScheduler() ;
    Scheduler provideObserverScheduler() ;
}
