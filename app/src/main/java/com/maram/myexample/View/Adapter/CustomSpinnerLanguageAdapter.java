package com.maram.myexample.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maram.myexample.R;

/**
 * Created by Marimuthu on 9/19/17.
 */

public class CustomSpinnerLanguageAdapter extends BaseAdapter{

    Context context;
    int flagIcons[];
    String accountTypeNames[];
    int column;
    int selectedPosition;

    public CustomSpinnerLanguageAdapter(Context context, int[] accountIcons, int keyCode, int position) {
        this.context = context;
        this.flagIcons = accountIcons;
        column = keyCode;
        selectedPosition = position;
    }


    @Override
    public int getCount() {
        return flagIcons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (position == selectedPosition) {
            ImageView imageView = new ImageView(context);
            imageView.setVisibility(View.INVISIBLE);
            v = imageView;
        } else {
            v = super.getDropDownView(position, null, parent);
        }
        return v;
    }*/

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = LayoutInflater.from(context);
        view = inflator.inflate(R.layout.row_flag_item,viewGroup,false);
        ImageView imageViewIcon = (ImageView)view.findViewById(R.id.imageView_icon);
        imageViewIcon.setImageResource(flagIcons[i]);
        return view;
    }
}
