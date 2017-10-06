package com.maram.myexample.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
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

public class CustomSpinnerAdapter extends BaseAdapter{

    Context context;
    int accountIcons[];
    String accountTypeNames[];
    int column;
    int position;

    public CustomSpinnerAdapter(Context context, String[] accountTypes, int[] accountIcons, int keyCode, int position) {
        this.context = context;
        this.accountIcons = accountIcons;
        this.accountTypeNames = accountTypes;
        column = keyCode;
        this.position = position;
    }


    @Override
    public int getCount() {
        return accountIcons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = LayoutInflater.from(context);
        view = inflator.inflate(R.layout.row_spinner_item,viewGroup,false);
        ImageView imageViewIcon = (ImageView)view.findViewById(R.id.imageView_icon);
        TextView textViewName = (TextView)view.findViewById(R.id.textView_name);
        if(column == 2){
            imageViewIcon.setImageResource(accountIcons[i]);
            textViewName.setText(accountTypeNames[i]);
        }
        else {
            imageViewIcon.setImageResource(accountIcons[i]);
            textViewName.setText("");
        }
        return view;
    }
}
