package com.maram.myexample.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.maram.myexample.R;

/**
 * Created by Marimuthu on 10/13/17.
 */

public class SecondActivity extends AppCompatActivity {

    public static String TAG = "Map";
    PlaceAutocompleteFragment autocompleteFragment;
    /**
     * Toolbar field
     */
    Toolbar toolBarTop;

    /**
     * Toolbar Title TextView
     */
    TextView tvToolBarTitle;

    CardView cardViewFlag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolBarTop = (Toolbar) findViewById(R.id.toolbar_layout);
        tvToolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        cardViewFlag = (CardView) findViewById(R.id.cardview_language_flag);
        setupToolBar();
        initActivity();
    }

    private void initActivity() {
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setCountry("MM")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();

        autocompleteFragment.setFilter(autocompleteFilter);

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .setFilter(autocompleteFilter)
                            .build(this);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "onPlaceSelected: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "onError: " + status);
            }
        });
    }

    /**
     * This method used to set the toolbar
     */
    private void setupToolBar() {
        //toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back));
        cardViewFlag.setVisibility(View.GONE);
        setSupportActionBar(toolBarTop);                                            // Setting/replace toolbar as the ActionBar
        toolBarTop.setTitle("");
        tvToolBarTitle.setText("Google Place");                                      // set the custom textview for the Toolbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);                    // hide the current title from the Toolbar

        toolBarTop.setLogoDescription("LOGO");                                      // set description for the logo

        // Back button going to hide.. will make it like menu
        toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back)); // set icon for navigation button

        //toolBarTop.setSubtitle("AutoCompleteView");                                       // set subtitle for toolbar

        //toolBarTop.setNavigationContentDescription("Navigation content");           // set the navigation content string.
        tvToolBarTitle.setTextColor(getResources().getColor(R.color.colorAccent));  // set text color for Toolbar title. subtitle color also can change.

        toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
