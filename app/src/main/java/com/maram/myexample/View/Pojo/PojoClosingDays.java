package com.maram.myexample.View.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marimuthu on 8/28/17.
 */

public class PojoClosingDays{

    /**
     * name of the day
     */
    String dayName;

    /**
     * number of the day
     */
    int dayNumber;

    public PojoClosingDays() {
    }

    public PojoClosingDays(String dayName, int dayNumber) {
        this.dayName = dayName;
        this.dayNumber = dayNumber;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }
}
