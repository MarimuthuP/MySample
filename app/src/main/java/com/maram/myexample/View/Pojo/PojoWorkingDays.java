package com.maram.myexample.View.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marimuthu on 8/28/17.
 */

public class PojoWorkingDays{

    /**
     *  name of the day
     */
    String dayName;

    /**
     * id of the day
     */
    int dayId;

    /**
     * value of the day
     */
    int dayValue;

    public PojoWorkingDays() {
    }

    public PojoWorkingDays(String dayName, int dayId, int dayValue) {
        this.dayName = dayName;
        this.dayId = dayId;
        this.dayValue = dayValue;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }

}
