package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;
import com.maram.myexample.View.Adapter.CustomSpinnerAdapter;
import com.maram.myexample.View.Adapter.CustomSpinnerLanguageAdapter;

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
     *  timer toast
     */
    TextView textViewAnimateToast;

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

    int[] flagIcons1 = {R.drawable.flag_myanma, R.drawable.flag_uk};
    int[] flagIcons2 = {R.drawable.flag_uk, R.drawable.flag_myanma};

    CustomSpinnerAdapter customSpinnerAdapter;

    CustomSpinnerLanguageAdapter customSpinnerLanguageAdapter;

    Spinner accountSpinner;

    //Spinner languageSpinner;

    String typeStr;

    //LinearLayout linearLayoutChangeLanguage;
    RelativeLayout relativeLayoutPopup;
    Context mContext;
    ImageView imageViewPopupCloseButton;
    int keyCode = 0;
    int currentPosition = 1;
    private PopupWindow mPopupWindow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
        mContext = getActivity().getApplicationContext();
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
        textViewSimpleToast = viewFragment.findViewById(R.id.textview_simple_toast);
        textViewCustomToast = viewFragment.findViewById(R.id.textview_custom_toast);
        textViewTimerToast = viewFragment.findViewById(R.id.textview_timer_toast);
        textViewAnimateToast = viewFragment.findViewById(R.id.textview_animate_toast);
        accountSpinner = viewFragment.findViewById(R.id.spinner_account);
        //languageSpinner = (Spinner)viewFragment.findViewById(R.id.spinner_language);
        relativeLayoutPopup = viewFragment.findViewById(R.id.relativelayout_main);
        //linearLayoutChangeLanguage = (LinearLayout) viewFragment.findViewById(R.id.ll_popup_layout);

        accountTypes =  new String[]{
                getResources().getString(R.string.business_account),
                getResources().getString(R.string.personal_account)
        };
        setSpinnerAdapter();
        setSpinnerLanguageAdapter(true);
        setOnClickEvent();
    }

    private void setSpinnerLanguageAdapter(boolean flag) {
        keyCode = 1;
        if(currentPosition == 1)
            customSpinnerLanguageAdapter = new CustomSpinnerLanguageAdapter(getActivity(),flagIcons1, keyCode, currentPosition);
        else
            customSpinnerLanguageAdapter = new CustomSpinnerLanguageAdapter(getActivity(),flagIcons2, keyCode, currentPosition);
        /*languageSpinner.setAdapter(customSpinnerLanguageAdapter);
        if(flag)
            languageSpinner.setSelection(0);*/
    }

    private void setSpinnerAdapter() {
        keyCode = 2;
        currentPosition = 2;
        customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(),accountTypes,accountIcons,keyCode, currentPosition);
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
        textViewAnimateToast.setOnClickListener(this);
        accountSpinner.setOnItemSelectedListener(this);
        //languageSpinner.setOnItemSelectedListener(this);
        //linearLayoutChangeLanguage.setOnClickListener(this);
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
            case R.id.textview_animate_toast:
                customAnimationToast();
                break;
            /*case R.id.ll_popup_layout:
                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupWindow = inflater.inflate(R.layout.popup_change_language,null);
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        popupWindow,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                ImageButton imageViewClose = (ImageButton)popupWindow.findViewById(R.id.ib_close);
                imageViewClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(relativeLayoutPopup, Gravity.CENTER,0,0);
                break;*/
            default:
                break;
        }
    }

    /**
     *
     */
    private void customAnimationToast() {

    }

    /**
     * used to show the toast in given duration
     */
    private void callCustomToastWithDuration() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom_layout, (ViewGroup)getActivity().findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
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

        TextView text = layout.findViewById(R.id.text);
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
        int positionNow = (position==0)?1:2;

        if (currentPosition == 1) {
            currentPosition = 2;
            //setSpinnerLanguageAdapter(false);
            Toast.makeText(getActivity(), "Changed language is Myanma", Toast.LENGTH_SHORT).show();
        } else if (currentPosition == 2){
            currentPosition = 1;
            //setSpinnerLanguageAdapter(false);
            Toast.makeText(getActivity(), "Changed language is English", Toast.LENGTH_SHORT).show();
        }
        /*if(accountType.equals(accountTypes[position])){
            Toast.makeText(getActivity(), "Clicked My Business - "+accountType, Toast.LENGTH_SHORT).show();
            currentPosition = 2;
        }
        else{
            Toast.makeText(getActivity(), "Clicked Me - "+accountType, Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
