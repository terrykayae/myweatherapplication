package uk.co.tezk.myweatherapplication.injection;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tezk on 12/06/17.
 */

public class SchedulerProviderImpl implements ISchedulerProvider {
    @Override
    public Scheduler provideSubscriberScheduler() {
        return Schedulers.io();
    }

    @Override
    public Scheduler provideObserverScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
