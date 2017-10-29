package com.maram.myexample.View.Converter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Marimuthu on 10/29/17.
 */

@SuppressLint("AppCompatCustomView")
public class MyanmaTextView extends TextView {

    public MyanmaTextView(Context context) {
        super(context);
        TextHelper.setTypeface(context, this);
    }

    public MyanmaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TextHelper.setTypeface(context, this);
    }

    public MyanmaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TextHelper.setTypeface(context, this);
    }

    public CharSequence getMyanmarText() {
        return MMText.getMMText(this);
    }

    public void setMyanmarText(String st) {
        setText(MMText.processText(st, MMText.TEXT_UNICODE, true, true));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
