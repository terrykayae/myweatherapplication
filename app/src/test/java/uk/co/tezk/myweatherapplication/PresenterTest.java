package uk.co.tezk.myweatherapplication;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import uk.co.tezk.myweatherapplication.injection.ISchedulerProvider;
import uk.co.tezk.myweatherapplication.model.Example;
import uk.co.tezk.myweatherapplication.model.List;
import uk.co.tezk.myweatherapplication.presenter.IWeatherApi;
import uk.co.tezk.myweatherapplication.presenter.IWeatherPresenter;
import uk.co.tezk.myweatherapplication.presenter.WeatherPresenterImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tezk on 11/06/17.
 */

public class PresenterTest {

    IWeatherPresenter.IPresenter presenter;
    @Mock
    IWeatherPresenter.IView view;
    @Mock
    IWeatherApi interactor;

    ISchedulerProvider schedulerProvider;

    java.util.List<List> myList;
    Single mySingle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        schedulerProvider = new ISchedulerProvider() {
            @Override
            public Scheduler provideSubscriberScheduler() {
                return Schedulers.trampoline();
            }

            @Override
            public Scheduler provideObserverScheduler() {
                return Schedulers.trampoline();
            }
        };

        presenter = new WeatherPresenterImpl(interactor, schedulerProvider);
        presenter.bind(view);

        myList = new ArrayList<>();
        Example example = new Example();
        example.setList(myList);
        mySingle = Single.just(example);

    }

    @Test
    public void testBinding() {

        when(interactor.getWeather(anyString(), anyString(), anyString())).thenReturn(mySingle);

        presenter.fetchData();
        verify(view, times(1)).onStartLoading();
        verify(view, times(1)).showData(myList);
        verify(view, times(1)).onLoadingCompleted();
    }

    @Test(expected = NullPointerException.class)
    public void testUnbind() {
        presenter.unbind();
        presenter.fetchData();
    }
}
