package uk.co.tezk.myweatherapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import uk.co.tezk.myweatherapplication.model.List;
import uk.co.tezk.myweatherapplication.presenter.IWeatherPresenter;
import uk.co.tezk.myweatherapplication.view.PlaceholderFragment;

public class MainActivity extends AppCompatActivity implements IWeatherPresenter.IView {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private ProgressDialog progressDialog;

    @Inject
    IWeatherPresenter.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialse Dagger injections
        WeatherApplication.getApplication().getMainComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mPresenter.bind(this);
        mPresenter.fetchData();
    }

    // Callbacks from the presenter
    @Override
    public void onStartLoading() {
        Log.i("MA", "onStartLoading");
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait, fetching the weather!");

        }
        progressDialog.show();
    }

    @Override
    public void showData(java.util.List<List> weatherList) {
        int startDow = -1;
        Map <Integer, java.util.List <List>>weatherMap;
        weatherMap = new HashMap<>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (List each : weatherList) {
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(df.parse(each.getDtTxt()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int dow = calendar.get(Calendar.DAY_OF_WEEK);
            if (startDow == -1)
                startDow = dow;

            if (weatherMap.get(dow) == null)
                weatherMap.put(dow, new ArrayList());

            java.util.List <List> dayList = weatherMap.get(dow);
            dayList.add(each);
        }

        for (int i=0; i<5; i++) {
            PlaceholderFragment fragment = (PlaceholderFragment) mSectionsPagerAdapter.getItem(i);
             fragment.setWeatherList(weatherMap.get((i+startDow)%7));
        }
    }

    @Override
    public void onLoadingCompleted() {
        Log.i("MA", "onCompleteLoading");
        progressDialog.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        Log.i("MA", "onError : "+e.getMessage());
        e.printStackTrace();
        progressDialog.dismiss();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        PlaceholderFragment placeholderFragmentHolder[] = new PlaceholderFragment[5];

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment
            if (placeholderFragmentHolder[position] == null)
                placeholderFragmentHolder[position] = PlaceholderFragment.newInstance(position + 1);
            return placeholderFragmentHolder[position];
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String[] stringArray = getResources().getStringArray(R.array.day_titles);
            return stringArray[position];
        }
    }
}
