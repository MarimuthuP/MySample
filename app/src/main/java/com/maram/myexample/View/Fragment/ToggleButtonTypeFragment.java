package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class ToggleButtonTypeFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    /**
     * simple default toast
     */
    Switch switchFirst;

    /**
     * Customized toast
     */
    Switch switchSecond;

    /**
     * timer toast
     */
    Switch switchThird;

    /**
     * timer toast
     */
    Switch switchFourth;

    /**
     * fragment layout view
     */
    View viewFragment;

    IMainCommunicator iMainCommunicator;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator) activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_togglebutton, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_checkbox));
        initFragments();
    }

    /**
     * Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        switchFirst = viewFragment.findViewById(R.id.switch_first);
        switchSecond = viewFragment.findViewById(R.id.switch_second);
        switchThird = viewFragment.findViewById(R.id.switch_third);
        switchFourth = viewFragment.findViewById(R.id.switch_fourth);

        switchFirst.setOnCheckedChangeListener(this);
        switchSecond.setOnCheckedChangeListener(this);
        switchThird.setOnCheckedChangeListener(this);
        switchFourth.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_first:
                if (b)
                    Toast.makeText(getActivity(), "First Switch clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.switch_second:
                if (b)
                    Toast.makeText(getActivity(), "Second Switch clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.switch_third:
                if (b)
                    Toast.makeText(getActivity(), "Third Switch clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.switch_fourth:
                if (b)
                    Toast.makeText(getActivity(), "Fourth Switch clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
