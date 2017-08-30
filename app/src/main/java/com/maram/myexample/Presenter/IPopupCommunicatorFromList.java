package com.maram.myexample.Presenter;

import com.maram.myexample.View.Pojo.PojoClosingDays;
import com.maram.myexample.View.Pojo.PojoWorkingDays;

import java.util.ArrayList;

/**
 * Created by Marimuthu on 8/26/17.
 */

public interface IPopupCommunicatorFromList {

    /**
     * which is used to dismiss the dialog when receive the value from click event of popup
     * @param valueText
     * @param keyValue
     */
    void callToDismissDialog(String valueText, int keyValue);
}
