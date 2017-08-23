package com.maram.myexample.Presenter;

/**
 * Created by Marimuthu on 8/21/17.
 */

public interface IResponseData {

    /**
     * Which is used to send the amount to model for calculation.
     * @param amountValue
     * @param keyValue
     */
    void IAmountFormatPresenterToModel(String amountValue, int keyValue);

    /**
     *  Which is used to once calculation done.. get it from model
     * @param amountValue
     * @param keyValue
     */
    void IAmountFormatModelToPresenter(String amountValue, int keyValue);
}
