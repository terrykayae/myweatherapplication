package uk.co.tezk.myweatherapplication.injection.modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.tezk.myweatherapplication.WeatherApplication;
import uk.co.tezk.myweatherapplication.presenter.IWeatherApi;

import static uk.co.tezk.myweatherapplication.model.Constants.BASE_URL;

/**
 * Created by tezk on 11/06/17.
 */
@Module
public class NetworkModule {
    @Provides
    public OkHttpClient provideOkHttpclient(WeatherApplication application) {
        Cache cache = new Cache(application.getCacheDir(), 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(180, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        return okHttpClient;
    }

    @Provides
    public GsonConverterFactory provideGsonFactory() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return gsonConverterFactory;
    }

    @Provides
    public RxJava2CallAdapterFactory providesRxJavaFactory() {
        RxJava2CallAdapterFactory callAdapterFactory = RxJava2CallAdapterFactory.create();

        return callAdapterFactory;
    }

    @Provides
    public Retrofit provideRetrofit(
            OkHttpClient okHttpClient,
            GsonConverterFactory gsonConverterFactory,
            RxJava2CallAdapterFactory rxJavaCallAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(okHttpClient)
                .build();
        return retrofit;

    }

    @Provides
    public IWeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(IWeatherApi.class);
    }
}
