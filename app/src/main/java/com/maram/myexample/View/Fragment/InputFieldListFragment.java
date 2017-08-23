package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.Presenter.IReceiveAmount;
import com.maram.myexample.Presenter.IRequestData;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.MainActivity;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.Utils.ProfileUtils;
import com.maram.myexample.View.customView.CustomTextWatcher;

/**
 * Created by Marimuthu on 8/17/17.
 */

public class InputFieldListFragment extends Fragment implements IReceiveAmount, IRequestData {

    /**
     *
     */
    EditText editTextAmountField;
    View mainView;
    IRequestData iRequestData;
    ImageView imageViewProfilePic;
    InputFilter filter_allow_Burmese_only, filter_allow_English_only;
    EditText editTextNameField;
    IMainCommunicator iMainCommunicator;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).iReceiveAmount = this;
        iMainCommunicator = (IMainCommunicator)activity;
        //CommReqAmount = (IEnteredAmountValidation) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_input_field));
        initializeFragment();
    }

    private void initializeFragment() {
        int min = 100000;
        int max = 1000000;
        editTextAmountField = (EditText) mainView.findViewById(R.id.edt_amount);
        editTextNameField = (EditText) mainView.findViewById(R.id.edt_name);
        imageViewProfilePic = (ImageView) mainView.findViewById(R.id.iv_profile_image);
        ProfileUtils.getInstanceObj().setMaxLength(editTextAmountField, 10);
        editTextAmountField.addTextChangedListener(new CustomTextWatcher(editTextAmountField, getActivity(), min));
        filterInputFields();
        editTextNameField.setFilters(new InputFilter[]{filter_allow_English_only});
    }

    @Override
    public void ReceiveTheAmountValue(String amtValue, int keyValue) {
        if (keyValue == MyConstant.AMOUNT_KEY) {
            editTextAmountField.setText(amtValue);
            editTextAmountField.setSelection(editTextAmountField.getText().length());
            System.out.println("AMount - " + amtValue);
        }
    }

    @Override
    public void IAmountFromViewToPresenter(String amtValue, int keyValue) {

    }

    @Override
    public void IAmountFromPresenterToView(String amtValue, int keyValue) {

    }

    public void filterInputFields(){
        // FOR ALLOWING BURMESE CHARACTERS ONLY
        filter_allow_Burmese_only = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if ((((int) source.charAt(i)) >= 65 && ((int) source.charAt(i)) <= 90) || (((int) source.charAt(i)) >= 97 && ((int) source.charAt(i)) <= 122)) {
                        return "";
                    } else {
                        return source;
                    }
                }
                return null;
            }
        };

        // FOR ALLOWING ENGLISH CHARACTER AND SPACE ONLY
        filter_allow_English_only = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if ((((int) source.charAt(i)) >= 65 && ((int) source.charAt(i)) <= 90) || (((int) source.charAt(i)) >= 97 && ((int) source.charAt(i)) <= 122) ) {
                        return source;
                    } else {
                        return "";
                    }
                }
                return null;
            }
        };
    }


}
