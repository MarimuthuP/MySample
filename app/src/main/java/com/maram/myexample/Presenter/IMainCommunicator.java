package com.maram.myexample.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Marimuthu on 8/21/17.
 */

public interface IMainCommunicator {
    void openNextScreen(int keyValue);
    void setupToolBarTitle(String toolBarName);
}
