package com.maram.myexample.Presenter;

/**
 * Created by Marimuthu on 8/21/17.
 */

public interface IRequestData {

    /**
     * Which is used to request the amount to apply the format.
     * @param amtValue
     * @param keyValue
     */
    void IAmountFromViewToPresenter(String amtValue, int keyValue);

    /**
     * This is used to set the formatted amount to view..
     * @param amtValue
     * @param keyValue
     */
    void IAmountFromPresenterToView(String amtValue, int keyValue);
}
