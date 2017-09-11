package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.Presenter.IPopupItemClickedFromList;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.MainActivity;
import com.maram.myexample.View.Pojo.PojoAlertMessage;
import com.maram.myexample.View.Pojo.PojoClosingDays;
import com.maram.myexample.View.Pojo.PojoWorkingDays;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.customView.CommonDialogFragment;
import com.maram.myexample.View.customView.CommonDialogWithList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class ToastTypeFragment extends Fragment implements View.OnClickListener{

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
        setOnClickEvent();
    }

    /**
     *  This is used to invoke the onclick event listener
     */
    private void setOnClickEvent() {
        textViewSimpleToast.setOnClickListener(this);
        textViewCustomToast.setOnClickListener(this);
        textViewTimerToast.setOnClickListener(this);
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

}
