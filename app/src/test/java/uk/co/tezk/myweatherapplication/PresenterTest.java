package uk.co.tezk.myweatherapplication;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.tezk.myweatherapplication.presenter.IWeatherPresenter;
import uk.co.tezk.myweatherapplication.presenter.WeatherPresenterImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by tezk on 11/06/17.
 */

public class PresenterTest {

    IWeatherPresenter.IPresenter presenter;
    @Mock
    IWeatherPresenter.IView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new WeatherPresenterImpl();
        presenter.bind(view);
    }

    @Test
    public void testBinding() {
        presenter.fetchData();
        verify(view, times(1)).onStartLoading();
        verify(view, times(1)).showData();
        verify(view, times(1)).onLoadingCompleted();
    }

    @Test(expected = NullPointerException.class)
    public void testUnbind() {
        presenter.unbind();
        presenter.fetchData();
    }
}
