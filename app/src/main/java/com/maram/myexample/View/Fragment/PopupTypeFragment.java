package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class PopupTypeFragment extends Fragment implements IPopupItemClickedFromList,View.OnClickListener{

    /**
     * simple default popup
     */
    TextView textViewSimpleDefaultPopup;

    /**
     * Customized popup
     */
    TextView textViewCustomPopup;

    /**
     *  Common popup
     */
    TextView textViewCommonPopup;

    /**
     * Common popup with recycler list
     */
    TextView textViewCommonPopupWithList;

    /**
     * Common popup with input field
     */
    TextView textViewCommonPopupWithInput;

    /**
     * Common popup with check box
     */
    TextView textViewCommonPopupWithCheckbox;

    /**
     * fragment layout view
     */
    View viewFragment;

    /**
     * Interface obj
     */
    IMainCommunicator iMainCommunicator;

    /**
     * string array of ownership item
     */
    ArrayList<String> stringArrayListOwnership = new ArrayList<>(Arrays.asList("Individual","Private","Government","Partnership","Enter Amount"));

    /**
     * Selected value from list
     */
    TextView textViewSelectedValueFromList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
        ((MainActivity)activity).iPopupItemClickedFromList = this;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popuptypes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_popup));
        initFragments();
    }

    /**
     *  Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        textViewSimpleDefaultPopup = (TextView)viewFragment.findViewById(R.id.tv_first_popup);
        textViewCustomPopup = (TextView)viewFragment.findViewById(R.id.tv_second_popup);
        textViewCommonPopup = (TextView)viewFragment.findViewById(R.id.tv_third_popup);
        textViewCommonPopupWithList = (TextView)viewFragment.findViewById(R.id.tv_fourth_popup);
        textViewCommonPopupWithInput = (TextView)viewFragment.findViewById(R.id.tv_fifth_popup);
        textViewCommonPopupWithCheckbox = (TextView)viewFragment.findViewById(R.id.tv_sixth_popup);
        textViewSelectedValueFromList = (TextView)viewFragment.findViewById(R.id.tv_result);

        setOnClickEvent();
    }

    /**
     *  This is used to invoke the onclick event listener
     */
    private void setOnClickEvent() {
        textViewSimpleDefaultPopup.setOnClickListener(this);
        textViewCustomPopup.setOnClickListener(this);
        textViewCommonPopup.setOnClickListener(this);
        textViewCommonPopupWithList.setOnClickListener(this);
        textViewCommonPopupWithInput.setOnClickListener(this);
        textViewCommonPopupWithCheckbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_first_popup:
                callDefaultAlertDialog();
                break;
            case R.id.tv_second_popup:
                callDefaultAlertDialogWithTwoButtons();
                break;
            case R.id.tv_third_popup:
                callCustomCommonDialog();
                break;
            case R.id.tv_fourth_popup:
                callCustomCommonDialogWithList();
                break;
            case R.id.tv_fifth_popup:
                callCustomCommonDialogWithInputField();
                break;
            case R.id.tv_sixth_popup:
                showDatePicker();
                break;
            default:
                break;
        }
    }

    /**
     * This is the method for invoke dialog with the list
     */
    private void callCustomCommonDialogWithList() {
        DialogFragment dialogFragment;
        dialogFragment = new CommonDialogWithList();
        Bundle bundle = new Bundle();
        PojoAlertMessage pojoModel = new PojoAlertMessage(getResources().getString(R.string.alert_list_title),R.mipmap.ic_launcher,
                "","","","","",
                true,true,false,false,false,false,false,true,false);
        bundle.putSerializable(MyConstant.DIALOG_TEXT,pojoModel);
        bundle.putStringArrayList("OwnerList",stringArrayListOwnership);
        dialogFragment.setArguments(bundle);
        dialogFragment.setCancelable(true);
        dialogFragment.show(getFragmentManager(),"CommonDialogInput");
    }

    /**
     * This is the method for invoke dialog with input field
     */
    private void callCustomCommonDialogWithInputField() {
        DialogFragment dialogFragmentWithList;
        dialogFragmentWithList = new CommonDialogWithList();
        Bundle bundle = new Bundle();
        PojoAlertMessage pojoModel = new PojoAlertMessage(getResources().getString(R.string.alert_list_title),R.mipmap.ic_launcher,
                "","","",getResources().getString(R.string.ok_text),getResources().getString(R.string.cancel_text),
                true,true,false,false,false,true,true,false,true);
        bundle.putSerializable(MyConstant.DIALOG_TEXT,pojoModel);
        //bundle.putStringArrayList("OwnerList",stringArrayListOwnership);
        dialogFragmentWithList.setArguments(bundle);
        dialogFragmentWithList.setCancelable(true);
        dialogFragmentWithList.show(getFragmentManager(),"CommonDialogInput");
    }

    /**
     * This is the method for invoke custom common dialog
     */
    private void callCustomCommonDialog() {
        DialogFragment dialogFragment;
        dialogFragment = new CommonDialogFragment();
        Bundle bundle = new Bundle();
        PojoAlertMessage pojoModel = new PojoAlertMessage(getResources().getString(R.string.alert_title),R.mipmap.ic_launcher,
                getResources().getString(R.string.alert_message),getResources().getString(R.string.alert_message2),"",getResources().getString(R.string.ok_text),getResources().getString(R.string.cancel_text),
                true,true,true,true,false,true,true);
        bundle.putSerializable(MyConstant.DIALOG_TEXT,pojoModel);
        dialogFragment.setArguments(bundle);
        dialogFragment.setCancelable(true);
        dialogFragment.show(getFragmentManager(),"CommonDialog");
    }

    /**
     *  This is used to show default alert dialog
     */
    private void callDefaultAlertDialog() {
        //AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),R.style.AppThemeDialogAlertGreen)).create();
        alertDialog.setTitle(getResources().getString(R.string.alert_title));
        alertDialog.setMessage(getResources().getString(R.string.alert_message));
        alertDialog.setIcon(R.mipmap.ic_launcher);

        alertDialog.setCancelable(true);
        alertDialog.setButton(getResources().getString(R.string.ok_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }

    /**
     * This method is used to show alert with two button action
     */
    public void callDefaultAlertDialogWithTwoButtons(){
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),R.style.AppThemeDialogAlertGreen));
        builder.setTitle(getResources().getString(R.string.alert_title));
        builder.setMessage(getResources().getString(R.string.alert_message));
        builder.setCancelable(true);

        builder.setPositiveButton(getResources().getString(R.string.ok_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Ok Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // we can show the popup by the desired position
        AlertDialog dialog_card = builder.create();
        // dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_card.getWindow().setGravity(Gravity.TOP);
        dialog_card.show();
        //builder.show();
    }

    /**
     * This method is used to made and show the custom toast. it was committed on 23Aug17
     */
    public void customToast(){
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View viewtoast = layoutInflater.inflate(R.layout.fragment_popuptypes,null);
        Toast myToast = new Toast(getActivity());
        myToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(viewtoast);
        myToast.show();

    }

    /**
     * Which method is used to expand the clicked view
     * @param v
     */
    public static void expandView(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    /**
     * Which method is used to collapse the clicked view.
     * @param v
     */
    public static void collapseView(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    @Override
    public void popupItemClicked(String value, int keyValue) {
        if(value.contains("Enter")){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    callCustomCommonDialogWithInputField();
                }
            }, 550);

        }
        else{
            textViewSelectedValueFromList.setText("SELECTED: "+value);
        }
    }


    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year1, month1, day1;

        final AlertDialog.Builder mDateTimeDialog = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),R.style.AppThemeDialogAlertGreen));
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.custom_date_picker, null);
        final DatePicker datePickerView = (DatePicker) view.findViewById(R.id.datePickerView);
        datePickerView.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        //String datenow = mDateOfBirth.getText().toString();

        /*if (datenow.length() > 0) {
            String[] selectedDate_array = datenow.split("-");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            year1 = Integer.valueOf(selectedDate_array[2]);
            month1 = getSelectedMonth(selectedDate_array[1]);
            day1 = Integer.valueOf(selectedDate_array[0]);
            calendar.set(Calendar.YEAR, year1);
            calendar.set(Calendar.MONTH, month1);
            calendar.set(Calendar.DAY_OF_MONTH, day1);
        } else
            */
        {
            calendar.add(Calendar.YEAR, -12);
        }
        datePickerView.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        mDateTimeDialog.setView(view);
        String set = "set"; //getResources().getString(R.string.set);
        String cancel = "cancel";

        mDateTimeDialog.setPositiveButton(set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                view.clearFocus();
                int day = datePickerView.getDayOfMonth();
                int month = datePickerView.getMonth();
                int year = datePickerView.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                Calendar preCalendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                preCalendar.add(Calendar.YEAR, -12);
                /*if (calendar.getTimeInMillis() <= preCalendar.getTimeInMillis()) {
                    mDateOfBirth.setText(sdf.format(calendar.getTime()));
                    isAgelower18();
                    isNameEdited = true;
                    llTakePhoto.setVisibility(View.VISIBLE);
                    languageReset();
                } else {
                    languageReset();
                    mDateOfBirth.setHint(getResources().getString(R.string.dob_text));
                    showCustomToast(getResources().getString(R.string.dob_error));
                    return;
                }*/
            }
        });
        mDateTimeDialog.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //languageReset();
            }
        });
        mDateTimeDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                //languageReset();
            }
        });
        mDateTimeDialog.show();

    }
}
