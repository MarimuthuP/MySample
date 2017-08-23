package com.maram.myexample.View.customView;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.maram.myexample.Presenter.IEnteredAmountValidation;
import com.maram.myexample.R;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.Utils.ProfileUtils;

import java.text.DecimalFormat;

/**
 * Created by Marimuthu on 8/17/17.
 */

public class CustomTextWatcher implements TextWatcher {

    EditText myview;
    Context contextnew;
    IEnteredAmountValidation iEnteredAmountValidation;
    int limitamt;

    public CustomTextWatcher(EditText myedtview, Context context, int limit) {
        this.myview = myedtview;
        this.contextnew = context;
        limitamt = limit;
        iEnteredAmountValidation = (IEnteredAmountValidation)contextnew;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        switch (myview.getId()) {
            case R.id.edt_amount:
                myview.removeTextChangedListener(this);
                try {
                    String givenstring = s.toString();
                    Long longval;
                    if (givenstring.contains(",")) {
                        givenstring = givenstring.replaceAll(",", "");
                    }
                    if (ProfileUtils.getInstanceObj().checkminAmountLimit(givenstring, limitamt)) {
                        longval = Long.parseLong(givenstring);
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        String formattedString = formatter.format(longval);
                        iEnteredAmountValidation.onEnteredAmountWithComma(formattedString, MyConstant.AMOUNT_KEY);
                    } else {
                        myview.setText(s.subSequence(0, s.length() - 1));
                        myview.setSelection(myview.getText().toString().length());
                        Toast.makeText(contextnew, "Limit Reached : " + s.toString(), Toast.LENGTH_SHORT).show();
                    }
                    // to place the cursor at the end of text
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                myview.addTextChangedListener(this);
                break;
            default:
                break;
        }

    }
}
