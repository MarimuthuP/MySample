package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.maram.myexample.R;

/**
 * Created by Marimuthu on 10/13/17.
 */

public class GoogleDefaultAutoComplete extends Fragment {

    public static String TAG = "Map";
    PlaceAutocompleteFragment autocompleteFragment;
    View mainView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_autocomplete, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        initFragment();
    }

    private void initFragment() {
        /*autocompleteFragment = (PlaceAutocompleteFragment)getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setCountry("MM")
                .setTypeFilter(TYPE_FILTER_NONE)
                .build();

        autocompleteFragment.setFilter(autocompleteFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "onPlaceSelected: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "onError: " + status);
            }
        });*/
    }
}
