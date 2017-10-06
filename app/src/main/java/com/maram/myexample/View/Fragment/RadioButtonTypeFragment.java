package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class RadioButtonTypeFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    /**
     * simple default toast
     */
    RadioButton radioButtonFirst;

    /**
     * Customized toast
     */
    RadioButton radioButtonSecond;

    /**
     *  timer toast
     */
    RadioButton radioButtonThird;

    /**
     *  timer toast
     */
    RadioButton radioButtonFourth;

    /**
     * fragment layout view
     */
    View viewFragment;

    IMainCommunicator iMainCommunicator;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_radiobutton, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_radiobox));
        initFragments();
    }

    /**
     *  Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        radioButtonFirst = (RadioButton) viewFragment.findViewById(R.id.radiobox_first);
        radioButtonSecond = (RadioButton) viewFragment.findViewById(R.id.radiobox_second);
        radioButtonThird = (RadioButton) viewFragment.findViewById(R.id.radiobox_third);
        radioButtonFourth = (RadioButton) viewFragment.findViewById(R.id.radiobox_fourth);

        radioButtonFirst.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.radiobox_first:
                if(b)
                    Toast.makeText(getActivity(), "First checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radiobox_second:
                if(b)
                    Toast.makeText(getActivity(), "Second checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radiobox_third:
                if(b)
                    Toast.makeText(getActivity(), "Third checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radiobox_fourth:
                if(b)
                    Toast.makeText(getActivity(), "Fourth checked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
