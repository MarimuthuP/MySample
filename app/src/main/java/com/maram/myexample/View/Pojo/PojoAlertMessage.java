package com.maram.myexample.View.Pojo;

import java.io.Serializable;

/**
 * Created by Marimuthu on 8/24/17.
 */

public class PojoAlertMessage implements Serializable{


    String alertTitle;
    int alertIcon;
    String alertMessage1;
    String alertMessage2;
    String alertMessage3;
    String okButtonText;
    String cancelButtonText;
    boolean isAlertTitle;
    boolean isAlertIcon;
    boolean isAlertMessage1;
    boolean isAlertMessage2;
    boolean isAlertMessage3;
    boolean isOkButton;
    boolean isCancelButton;
    boolean isRecyclerList;
    boolean isEnterAmount;

    public PojoAlertMessage(String alertTitle, int alertIcon, String alertMessage1, String alertMessage2, String alertMessage3, String okButtonText, String cancelButtonText, boolean isAlertTitle, boolean isAlertIcon, boolean isAlertMessage1, boolean isAlertMessage2, boolean isAlertMessage3, boolean isOkButton, boolean isCancelButton, boolean isRecyclerList, boolean isEnterAmount) {
        this.alertTitle = alertTitle;
        this.alertIcon = alertIcon;
        this.alertMessage1 = alertMessage1;
        this.alertMessage2 = alertMessage2;
        this.alertMessage3 = alertMessage3;
        this.okButtonText = okButtonText;
        this.cancelButtonText = cancelButtonText;
        this.isAlertTitle = isAlertTitle;
        this.isAlertIcon = isAlertIcon;
        this.isAlertMessage1 = isAlertMessage1;
        this.isAlertMessage2 = isAlertMessage2;
        this.isAlertMessage3 = isAlertMessage3;
        this.isOkButton = isOkButton;
        this.isCancelButton = isCancelButton;
        this.isRecyclerList = isRecyclerList;
        this.isEnterAmount = isEnterAmount;
    }

    public PojoAlertMessage(String alertTitle, int alertIcon, String alertMessage1, String alertMessage2, String alertMessage3, String okButtonText, String cancelButtonText, boolean isAlertTitle, boolean isAlertIcon, boolean isAlertMessage1, boolean isAlertMessage2, boolean isAlertMessage3, boolean isOkButton, boolean isCancelButton, boolean isRecyclerList) {
        this.alertTitle = alertTitle;
        this.alertIcon = alertIcon;
        this.alertMessage1 = alertMessage1;
        this.alertMessage2 = alertMessage2;
        this.alertMessage3 = alertMessage3;
        this.okButtonText = okButtonText;
        this.cancelButtonText = cancelButtonText;
        this.isAlertTitle = isAlertTitle;
        this.isAlertIcon = isAlertIcon;
        this.isAlertMessage1 = isAlertMessage1;
        this.isAlertMessage2 = isAlertMessage2;
        this.isAlertMessage3 = isAlertMessage3;
        this.isOkButton = isOkButton;
        this.isCancelButton = isCancelButton;
        this.isRecyclerList = isRecyclerList;
    }

    public PojoAlertMessage(String alertTitle, int alertIcon, String alertMessage1, String alertMessage2, String alertMessage3, String okButtonText, String cancelButtonText, boolean isAlertTitle, boolean isAlertIcon, boolean isAlertMessage1, boolean isAlertMessage2, boolean isAlertMessage3, boolean isOkButton, boolean isCancelButton) {
        this.alertTitle = alertTitle;
        this.alertIcon = alertIcon;
        this.alertMessage1 = alertMessage1;
        this.alertMessage2 = alertMessage2;
        this.alertMessage3 = alertMessage3;
        this.okButtonText = okButtonText;
        this.cancelButtonText = cancelButtonText;
        this.isAlertTitle = isAlertTitle;
        this.isAlertIcon = isAlertIcon;
        this.isAlertMessage1 = isAlertMessage1;
        this.isAlertMessage2 = isAlertMessage2;
        this.isAlertMessage3 = isAlertMessage3;
        this.isOkButton = isOkButton;
        this.isCancelButton = isCancelButton;
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    public int getAlertIcon() {
        return alertIcon;
    }

    public void setAlertIcon(int alertIcon) {
        this.alertIcon = alertIcon;
    }

    public String getAlertMessage1() {
        return alertMessage1;
    }

    public void setAlertMessage1(String alertMessage1) {
        this.alertMessage1 = alertMessage1;
    }

    public String getAlertMessage2() {
        return alertMessage2;
    }

    public void setAlertMessage2(String alertMessage2) {
        this.alertMessage2 = alertMessage2;
    }

    public String getAlertMessage3() {
        return alertMessage3;
    }

    public void setAlertMessage3(String alertMessage3) {
        this.alertMessage3 = alertMessage3;
    }

    public String getOkButtonText() {
        return okButtonText;
    }

    public void setOkButtonText(String okButtonText) {
        this.okButtonText = okButtonText;
    }

    public String getCancelButtonText() {
        return cancelButtonText;
    }

    public void setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
    }

    public boolean isAlertTitle() {
        return isAlertTitle;
    }

    public void setAlertTitle(boolean alertTitle) {
        isAlertTitle = alertTitle;
    }

    public boolean isAlertIcon() {
        return isAlertIcon;
    }

    public void setAlertIcon(boolean alertIcon) {
        isAlertIcon = alertIcon;
    }

    public boolean isAlertMessage1() {
        return isAlertMessage1;
    }

    public void setAlertMessage1(boolean alertMessage1) {
        isAlertMessage1 = alertMessage1;
    }

    public boolean isAlertMessage2() {
        return isAlertMessage2;
    }

    public void setAlertMessage2(boolean alertMessage2) {
        isAlertMessage2 = alertMessage2;
    }

    public boolean isAlertMessage3() {
        return isAlertMessage3;
    }

    public void setAlertMessage3(boolean alertMessage3) {
        isAlertMessage3 = alertMessage3;
    }

    public boolean isOkButton() {
        return isOkButton;
    }

    public void setOkButton(boolean okButton) {
        isOkButton = okButton;
    }

    public boolean isCancelButton() {
        return isCancelButton;
    }

    public void setCancelButton(boolean cancelButton) {
        isCancelButton = cancelButton;
    }

    public boolean isRecyclerList() {
        return isRecyclerList;
    }

    public void setRecyclerList(boolean recyclerList) {
        isRecyclerList = recyclerList;
    }

    public boolean isEnterAmount() {
        return isEnterAmount;
    }

    public void setEnterAmount(boolean enterAmount) {
        isEnterAmount = enterAmount;
    }
}
