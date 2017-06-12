package uk.co.tezk.myweatherapplication.presenter;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.tezk.myweatherapplication.model.Constants;
import uk.co.tezk.myweatherapplication.model.Example;

/**
 * http://api.openweathermap.org/data/2.5/forecast?q=Orpington,GB&units=metric&appid=7d353a9079c5420af3bebc1b4a65398c
 */

public interface IWeatherApi {
    @GET(Constants.BASE_URL+Constants.WEATHER_API)
    public Single<Example> getWeather(@Query("q")String location, @Query("units")String units, @Query("appid")String api_key) ;
}
