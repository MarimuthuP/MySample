package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;


/**
 * Created by Marimuthu on 8/23/17.
 */

public class PopupTypeFragment extends Fragment implements View.OnClickListener{

    /**
     * simple default popup
     */
    TextView textViewSimpleDefaultPopup;

    /**
     * Customized popup
     */
    TextView textViewCustomPopup;

    /**
     *  Common popup
     */
    TextView textViewCommonPopup;

    /**
     * fragment layout view
     */
    View viewFragment;

    /**
     * Interface obj
     */
    IMainCommunicator iMainCommunicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popuptypes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_popup));
        initFragments();
    }

    /**
     *  Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        textViewSimpleDefaultPopup = (TextView)viewFragment.findViewById(R.id.tv_first_popup);
        textViewCustomPopup = (TextView)viewFragment.findViewById(R.id.tv_second_popup);
        textViewCommonPopup = (TextView)viewFragment.findViewById(R.id.tv_third_popup);

        setOnClickEvent();
    }

    /**
     *  This is used to invoke the onclick event listener
     */
    private void setOnClickEvent() {
        textViewSimpleDefaultPopup.setOnClickListener(this);
        textViewCustomPopup.setOnClickListener(this);
        textViewCommonPopup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_first_popup:
                callDefaultAlertDialog();
                break;
            case R.id.tv_second_popup:
                break;
            case R.id.tv_third_popup:
                break;
            default:
                break;
        }
    }

    /**
     *  This is used to show default alert dialog
     */
    private void callDefaultAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("Welcome to dear user.");
        alertDialog.setIcon(R.mipmap.ic_launcher);

        alertDialog.setCancelable(true);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });


        alertDialog.show();
    }

    /**
     * This method is used to made and show the custom toast
     */
    public void customToast(){
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View viewtoast = layoutInflater.inflate(R.layout.fragment_popuptypes,null);
        Toast myToast = new Toast(getActivity());
        myToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(viewtoast);
        myToast.show();
    }
}
