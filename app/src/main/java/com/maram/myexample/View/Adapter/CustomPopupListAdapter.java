package com.maram.myexample.View.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IPopupCommunicatorFromList;
import com.maram.myexample.Presenter.IPopupItemClickedFromList;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.MainActivity;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.customView.CommonDialogWithList;

import java.util.List;

/**
 * Created by Marimuthu on 8/25/17.
 */

public class CustomPopupListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> listname;
    int type_Id;
    IPopupCommunicatorFromList iPopupCommunicatorFromList;

    public CustomPopupListAdapter(Context context, List<String> listname, int type_Id) {
        this.context = context;
        this.listname = listname;
        this.type_Id = type_Id;
        iPopupCommunicatorFromList = ((MainActivity)context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewGroupRow = null;
        switch (viewType){
            case 100:
                viewGroupRow = (ViewGroup)layoutInflater.inflate(R.layout.row_list_layout,parent,false);
                if(viewGroupRow!=null){
                    OwnerTypeViewHolder ownerTypeViewHolder = new OwnerTypeViewHolder(viewGroupRow);
                    return ownerTypeViewHolder;
                }
                else {
                    return null;
                }
            case 101:
                viewGroupRow = (ViewGroup)layoutInflater.inflate(R.layout.row_list_layout,parent,false);
                if(viewGroupRow!=null){
                    AddressTypeViewHolder addressTypeViewHolder = new AddressTypeViewHolder(viewGroupRow);
                    return addressTypeViewHolder;
                }
                else{
                    return null;
                }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof OwnerTypeViewHolder){
            ((OwnerTypeViewHolder)viewHolder).textViewItemName.setText(listname.get(position));
            ((OwnerTypeViewHolder)viewHolder).textViewItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String value = ((OwnerTypeViewHolder)viewHolder).textViewItemName.getText().toString();
                    Toast.makeText(context, "Clicked "+value, Toast.LENGTH_SHORT).show();
                    //CommonDialogWithList.getInstanceObj().dismissDialog();
                    iPopupCommunicatorFromList.callToDismissDialog(value,25);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listname.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(type_Id == MyConstant.ListType.OWNER_TYPE){
            type_Id = MyConstant.ListType.OWNER_TYPE;
        }
        else if(type_Id == MyConstant.ListType.ADDRESS_TYPE){
            type_Id = MyConstant.ListType.ADDRESS_TYPE;
        }
        return type_Id;
    }

    public class OwnerTypeViewHolder extends RecyclerView.ViewHolder{

        /**
         * Item name of textview
         */
        TextView textViewItemName;
        public OwnerTypeViewHolder(View itemView) {
            super(itemView);
            textViewItemName = (TextView)itemView.findViewById(R.id.textview_row_name);
        }
    }

    public class AddressTypeViewHolder extends RecyclerView.ViewHolder{

        /**
         * name of address textview
         */
        TextView textViewAddressName;

        /**
         * name of address textview
         */
        TextView textViewItemName;

        public AddressTypeViewHolder(View itemView) {
            super(itemView);
            textViewAddressName = (TextView)itemView.findViewById(R.id.textview_row_name);
            textViewItemName = (TextView)itemView.findViewById(R.id.textview_row_name);
        }
    }
}
