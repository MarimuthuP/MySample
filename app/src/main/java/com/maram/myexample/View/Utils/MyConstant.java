package com.maram.myexample.View.Utils;

/**
 * Created by Marimuthu on 8/17/17.
 */

public class MyConstant {
    public static int AMOUNT_KEY = 1;
    public static String DIALOG_TEXT = "dialog_parameters";

    public static class NavigateScreen{
        public static int INPUT_FIELD_KEY = 10;
        public static int POPUP_TYPE_KEY = 11;
        public static int TOAST_TYPE_KEY = 12;
    }

    public static class ListType{
        public static int OWNER_TYPE = 100;
        public static int ADDRESS_TYPE = 101;
    }

    public enum ScreenNumber{
        INPUTFIELDS, POPUPTYPE, TOASTTYPE;
    }
}
