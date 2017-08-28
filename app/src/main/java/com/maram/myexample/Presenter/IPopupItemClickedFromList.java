package com.maram.myexample.Presenter;

/**
 * Created by Marimuthu on 8/25/17.
 */

public interface IPopupItemClickedFromList {

    /**
     * Which is used to get the data from dialog list.
     * @param value - Item name
     * @param keyValue - Item key
     */
    void popupItemClicked(String value, int keyValue);

    /**
     * This method used to select the date from dialog
     */
    void dateSelected();
}
