package com.maram.myexample.View.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        setTypeface(getFont(context));

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setTypeface(getFont(context));
    }

    public CustomTextView(Context context, AttributeSet attrs) throws RuntimeException {
        super(context, attrs);
        setTypeface(getFont(context));


    }

    public static Typeface getFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "ZawgyiOne.ttf");
    }

//
//    @Override
//    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
//        if(focused)
//            super.onFocusChanged(focused, direction, previouslyFocusedRect);
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean focused) {
//        if(focused)
//            super.onWindowFocusChanged(focused);
//    }
//
//
//    @Override
//    public boolean isFocused() {
//        return true;
//    }

//    @Override
//    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
//        if(focused)
//            super.onFocusChanged(focused, direction, previouslyFocusedRect);
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean focused) {
//        if(focused)
//            super.onWindowFocusChanged(focused);
//    }
//
//
//    @Override
//    public boolean isFocused() {
//        return true;
//    }


}
