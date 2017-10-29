package com.maram.myexample.View.customView;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomEdittext extends AppCompatEditText {
    private Drawable dRight;
    private Rect rBounds;
    private Context context;

    public CustomEdittext(Context context) {
        super(context);
        this.context = context;
        setEditTextFilter();
    }


    public CustomEdittext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setEditTextFilter();
    }

    public CustomEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setEditTextFilter();
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top,
                                     Drawable right, Drawable bottom) {
        if (right != null) {
            dRight = right;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && dRight != null) {
            rBounds = dRight.getBounds();
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            if (x >= (this.getRight() - rBounds.width()) && x <= (this.getRight() - this.getPaddingRight())
                    && y >= this.getPaddingTop() && y <= (this.getHeight() - this.getPaddingBottom())) {
                event.setAction(MotionEvent.ACTION_CANCEL);//use this to prevent the keyboard from coming up
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        dRight = null;
        rBounds = null;
        super.finalize();
    }

    public void setEditTextFilter() {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    String character = String.valueOf(source.charAt(i));
                    if (character.equalsIgnoreCase("%") ||
                            character.equalsIgnoreCase("/") ||
                            character.equalsIgnoreCase("#") ||
                            character.equalsIgnoreCase("&") ||
                            character.equalsIgnoreCase(";") ||
                            character.equalsIgnoreCase("<") ||
                            character.equalsIgnoreCase(">") ||
                            character.equalsIgnoreCase("?")) {
                        return "";
                    }
                }
                return null;
            }
        };
        setFilters(new InputFilter[]{filter});
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        setSelection(this.length());
    }
}


