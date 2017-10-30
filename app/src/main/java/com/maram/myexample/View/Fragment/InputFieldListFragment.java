package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.Presenter.IReceiveAmount;
import com.maram.myexample.Presenter.IRequestData;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.MainActivity;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.Utils.ProfileUtils;
import com.maram.myexample.View.customView.CustomEdittext;
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
    CustomEdittext editTextNameField;
    IMainCommunicator iMainCommunicator;
    boolean isEdited = false;
    Handler mHandler = new Handler();

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
        editTextAmountField = mainView.findViewById(R.id.edt_amount);
        editTextNameField = mainView.findViewById(R.id.edt_name);
        imageViewProfilePic = mainView.findViewById(R.id.iv_profile_image);
        ProfileUtils.getInstanceObj().setMaxLength(editTextAmountField, 10);
        editTextAmountField.addTextChangedListener(new CustomTextWatcher(editTextAmountField, getActivity(), min));
        filterInputFields();
        //editTextNameField.setFilters(new InputFilter[]{filter_allow_English_only});
        //editTextNameField.setInputType(InputType.TYPE_CLASS_TEXT);
        //editTextNameField.setKeyListener(DigitsKeyListener.getInstance("0123456789"));

        editTextNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                String text = s.toString();
                if (text.contains("  ")) {
                    text.replace("  ", " ");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextAmountField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNameField.setText(editTextNameField.getText().toString().trim());
            }
        });

        editTextAmountField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void callServiceMethod() {
        Toast.makeText(getActivity(), "Called Service", Toast.LENGTH_SHORT).show();
    }

    private boolean isDigits(String s) {
        return s.equals("0") ||
                s.equals("1") ||
                s.equals("2") ||
                s.equals("3") ||
                s.equals("4") ||
                s.equals("5") ||
                s.equals("6") ||
                s.equals("7") ||
                s.equals("8") ||
                s.equals("9");
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
                    if ((((int) source.charAt(i)) >= 65 && ((int) source.charAt(i)) <= 90) ||
                            (((int) source.charAt(i)) >= 97 && ((int) source.charAt(i)) <= 122) ||
                            ((((int) source.charAt(i)) >= 48 && ((int) source.charAt(i)) <= 57))) {
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
                    if ((((int) source.charAt(i)) == 32) ||
                            (((int) source.charAt(i)) >= 65 && ((int) source.charAt(i)) <= 90) ||
                            (((int) source.charAt(i)) >= 97 && ((int) source.charAt(i)) <= 122)) {
                        return source;
                    } else {
                        return "";
                    }
                }
                return null;
            }
        };
    }


    public char LastChar(String a) {
        return a.charAt(a.length() - 1);
    }
}
