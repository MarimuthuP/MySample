package com.maram.myexample.Presenter;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Marimuthu on 8/21/17.
 */

public class CalculationPresenter implements IRequestData, IResponseData{

    public IPopupItemClickedFromList iPopupItemClickedFromList;

    /*public CalculationPresenter() {
    }

    public CalculationPresenter(IPopupItemClickedFromList iPopupItemClickedFromList) {
        this.iPopupItemClickedFromList = iPopupItemClickedFromList ;
    }*/

    @Override
    public void IAmountFromViewToPresenter(String amtValue, int keyValue) {
        /*if(keyValue == 30){
            iPopupItemClickedFromList.popupItemClicked(amtValue,keyValue);
        }
        if(keyValue == 25){
            iPopupItemClickedFromList.popupItemClicked(amtValue,keyValue);
        }*/
    }

    @Override
    public void IAmountFromPresenterToView(String amtValue, int keyValue) {

    }

    @Override
    public void IAmountFormatPresenterToModel(String amountValue, int keyValue) {

    }

    @Override
    public void IAmountFormatModelToPresenter(String amountValue, int keyValue) {

    }
}
