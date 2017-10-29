package com.maram.myexample.View.Converter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Marimuthu on 10/29/17.
 */

public class TextHelper {

    private static Typeface typeface = null;

    public static void setTypeface(Context context, TextView textview) {
        if (TextHelper.typeface == null) {
            TextHelper.typeface = Typeface.createFromAsset(context.getAssets(), "mymm.ttf");
        }
        textview.setText(MMText.processText(textview.getText().toString(), MMText.TEXT_UNICODE, true, true));
        textview.setTypeface(typeface);
    }
}
