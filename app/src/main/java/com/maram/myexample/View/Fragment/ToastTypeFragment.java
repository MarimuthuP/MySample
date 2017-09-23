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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;
import com.maram.myexample.View.Adapter.CustomSpinnerAdapter;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class ToastTypeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /**
     * simple default toast
     */
    TextView textViewSimpleToast;

    /**
     * Customized toast
     */
    TextView textViewCustomToast;

    /**
     *  timer toast
     */
    TextView textViewTimerToast;

    /**
     * fragment layout view
     */
    View viewFragment;

    /**
     * Interface obj
     */
    IMainCommunicator iMainCommunicator;

    String accountTypes[];

    int[] accountIcons = {R.drawable.ic_merchant, R.drawable.ic_personal};

    CustomSpinnerAdapter customSpinnerAdapter;

    Spinner accountSpinner;

    String typeStr;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toasttypes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_toast));
        initFragments();
    }

    /**
     *  Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        textViewSimpleToast = (TextView)viewFragment.findViewById(R.id.textview_simple_toast);
        textViewCustomToast = (TextView)viewFragment.findViewById(R.id.textview_custom_toast);
        textViewTimerToast = (TextView)viewFragment.findViewById(R.id.textview_timer_toast);
        accountSpinner = (Spinner)viewFragment.findViewById(R.id.spinner_account);
        accountTypes =  new String[]{
                getResources().getString(R.string.business_account),
                getResources().getString(R.string.personal_account)
        };
        setSpinnerAdapter();
        setOnClickEvent();

    }

    private void setSpinnerAdapter() {
        customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(),accountTypes,accountIcons);
        accountSpinner.setAdapter(customSpinnerAdapter);
        accountSpinner.setSelection(0);
    }

    /**
     *  This is used to invoke the onclick event listener
     */
    private void setOnClickEvent() {
        textViewSimpleToast.setOnClickListener(this);
        textViewCustomToast.setOnClickListener(this);
        textViewTimerToast.setOnClickListener(this);
        accountSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textview_simple_toast:
                callSimpleToast();
                break;
            case R.id.textview_custom_toast:
                customToast();
                break;
            case R.id.textview_timer_toast:
                callCustomToastWithDuration();
                accountSpinner.setSelection(0);
                accountSpinner.setEnabled(false);
                break;
            default:
                break;
        }
    }

    /**
     * used to show the toast in given duration
     */
    private void callCustomToastWithDuration() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom_layout, (ViewGroup)getActivity().findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("This is a custom toast");

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    /**
     * used to show the toast in particular position of the screen.
     */
    private void callSimpleToast() {

        //Toast.makeText(getActivity(), "Simple Toast", Toast.LENGTH_SHORT).show();

        Toast toast = Toast.makeText(getActivity(), "This is a Simple Toast",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.AXIS_PULL_AFTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * This method is used to made and show the custom toast.
     */
    public void customToast(){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom_layout, (ViewGroup)getActivity().findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("This is a custom toast");

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String accountType = accountTypes[position];

        if(accountType.equals(accountTypes[position])){
            Toast.makeText(getActivity(), "Clicked My Business - "+accountType, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(), "Clicked Me - "+accountType, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
