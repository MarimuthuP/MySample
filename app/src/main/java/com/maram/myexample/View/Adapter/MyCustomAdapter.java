package com.maram.myexample.View.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.R;

import java.util.ArrayList;

/**
 * Created by Marimuthu on 9/27/17.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> myNameList;

    public MyCustomAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        myNameList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_list_layout,parent,false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.rowName.setText(myNameList.get(position));
        holder.rowName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked - "+holder.rowName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rowName;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowName = (TextView)itemView.findViewById(R.id.textview_row_name);
        }
    }

    public ArrayList<String> updateData(ArrayList<String> datas, String text) {

        ArrayList<String> newNames = new ArrayList<>();
        for (String name : datas) {

            if (name.contains(text)) {
                newNames.add(name);
            }
        }
        return newNames;
    }
}
