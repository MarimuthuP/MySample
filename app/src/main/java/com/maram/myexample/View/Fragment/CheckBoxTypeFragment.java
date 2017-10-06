package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;
import com.maram.myexample.View.Adapter.CustomSpinnerAdapter;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class CheckBoxTypeFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    /**
     * simple default toast
     */
    CheckBox checkBoxFirst;

    /**
     * Customized toast
     */
    CheckBox checkBoxSecond;

    /**
     *  timer toast
     */
    CheckBox checkBoxThird;

    /**
     *  timer toast
     */
    CheckBox checkBoxFourth;

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
        return inflater.inflate(R.layout.fragment_checkbox, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_checkbox));
        initFragments();
    }

    /**
     *  Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        checkBoxFirst = (CheckBox) viewFragment.findViewById(R.id.checkbox_first);
        checkBoxSecond = (CheckBox) viewFragment.findViewById(R.id.checkbox_second);
        checkBoxThird = (CheckBox) viewFragment.findViewById(R.id.checkbox_third);
        checkBoxFourth = (CheckBox) viewFragment.findViewById(R.id.checkbox_fourth);

        checkBoxFirst.setOnCheckedChangeListener(this);
        checkBoxSecond.setOnCheckedChangeListener(this);
        checkBoxThird.setOnCheckedChangeListener(this);
        checkBoxFourth.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.checkbox_first:
                if(b)
                    Toast.makeText(getActivity(), "First checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_second:
                if(b)
                    Toast.makeText(getActivity(), "Second checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_third:
                if(b)
                    Toast.makeText(getActivity(), "Third checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_fourth:
                if(b)
                    Toast.makeText(getActivity(), "Fourth checked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
