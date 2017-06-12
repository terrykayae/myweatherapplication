package uk.co.tezk.myweatherapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.tezk.myweatherapplication.R;
import uk.co.tezk.myweatherapplication.model.List;
import uk.co.tezk.myweatherapplication.model.Main;

public class PlaceholderFragment extends Fragment {

    private java.util.List<uk.co.tezk.myweatherapplication.model.List> weatherList;

    @BindView(R.id.tvMinTemp) TextView tvMinTemp;
    @BindView(R.id.tvTemp) TextView tvTemp;
    @BindView(R.id.tvMaxTemp) TextView tvMaxTemp;
    @BindView(R.id.tvWindDir) TextView tvWindDir;
    @BindView(R.id.tvWindSpeed) TextView tvWindSpeed;
    @BindView(R.id.tvOverall) TextView tvOverall;
    @BindView(R.id.tvDetail) TextView tvDetail;

    public void setWeatherList(java.util.List<List> weatherList) {
        this.weatherList = weatherList;
        updateViews();
    }

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        updateViews();
    }

    public void updateViews() {
        // If we've not initalised yet, return
        if (tvMinTemp == null)
            return;

        // If data isn't ready, return
        if (weatherList == null || weatherList.size() == 0) {
            Log.e("fragment", "WeatherList is null or 0 " + weatherList);
            return;
        }

        String minTemp = "";
        String temp = "";
        String maxTemp = "";

        String windDir = "";
        String windSpeed = "";

        for (List each : weatherList) {
            Main eachMain = each.getMain();
            minTemp += eachMain.getTempMin()+", ";
            temp += eachMain.getTemp()+", ";
            maxTemp += eachMain.getTempMax()+", ";
            windDir += each.getWind().getDeg()+", ";
            windSpeed += each.getWind().getSpeed()+", ";

        }
        tvMinTemp.setText(minTemp);
        tvMaxTemp.setText(maxTemp);
        tvTemp.setText(temp);

        tvWindDir.setText(windDir);
        tvWindSpeed.setText(windSpeed);

        tvOverall.setText(weatherList.get(0).getWeather().get(0).getMain());
        tvDetail.setText(weatherList.get(0).getWeather().get(0).getDescription());
    }
}