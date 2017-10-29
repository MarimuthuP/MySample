package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maram.myexample.Presenter.ICommunicateClick;
import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.MainActivity;
import com.maram.myexample.View.Pojo.PojoAlertMessage;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.customView.CircleImageView;
import com.maram.myexample.View.customView.CommonDialogImageViewer;


/**
 * Created by Marimuthu on 8/21/17.
 */

public class MenuFragment extends Fragment implements ICommunicateClick, View.OnClickListener {

    public static String TAG = MenuFragment.class.getSimpleName();
    /**
     * which is layout object to access the id's
     */
    View viewFragment;
    /**
     * First menu option textview
     */
    TextView textView_first;
    /**
     * Second menu option textview
     */
    TextView textView_second;
    /**
     * Third menu option textview
     */
    TextView textView_third;
    /**
     * Four menu option textview
     */
    TextView textView_four;
    /**
     * Five menu option textview
     */
    TextView textView_five;
    /**
     * Six menu option textview
     */
    TextView textView_six;
    /**
     * Seven menu option textview
     */
    TextView textView_seven;
    /**
     * Eight menu option textview
     */
    TextView textView_eight;
    /**
     * which is used to access the activity methods
     */
    IMainCommunicator iMainCommunicator;
    CircleImageView userPicture;
    Bitmap bitmap;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity ;
        ((MainActivity) activity).iCommunicateClick = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        initFragments();
    }

    /**
     * Initialize the fragments fields
     */
    public void initFragments() {
        textView_first = viewFragment.findViewById(R.id.tv_first_option);
        textView_second = viewFragment.findViewById(R.id.tv_second_option);
        textView_third = viewFragment.findViewById(R.id.tv_third_option);
        textView_four = viewFragment.findViewById(R.id.tv_four_option);
        textView_five = viewFragment.findViewById(R.id.tv_five_option);
        textView_six = viewFragment.findViewById(R.id.tv_six_option);
        textView_seven = viewFragment.findViewById(R.id.tv_seven_option);
        textView_eight = viewFragment.findViewById(R.id.tv_eight_option);
        userPicture = viewFragment.findViewById(R.id.iv_profile_image);
        setOnClickListener();
    }

    /**
     * which is used to invoke the onclick listener
     */
    public void setOnClickListener() {
        textView_first.setOnClickListener(this);
        textView_second.setOnClickListener(this);
        textView_third.setOnClickListener(this);
        textView_four.setOnClickListener(this);
        textView_five.setOnClickListener(this);
        textView_six.setOnClickListener(this);
        textView_seven.setOnClickListener(this);
        textView_eight.setOnClickListener(this);
        userPicture.setOnClickListener(this);
        Log.d(TAG, "setOnClickListener: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.INPUT_FIELD_KEY);
                break;
            case R.id.tv_second_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.POPUP_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_third_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.TOAST_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_four_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.SEARCHVIEW_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_five_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.WEBVIEW_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_six_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.CAMERA_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_seven_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.FACE_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_eight_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.FLASH_TYPE_KEY);
                //viewFragment.findViewById(R.id.cardview_first).setVisibility(View.VISIBLE);
                break;
            case R.id.iv_profile_image:
                callCustomCommonDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void setImage(Bitmap image) {
        userPicture.setImageDrawable(new BitmapDrawable(getResources(), image));
        bitmap = image;
    }

    /**
     * This is the method for invoke custom common dialog
     */
    private void callCustomCommonDialog() {
        DialogFragment dialogFragment;
        dialogFragment = new CommonDialogImageViewer();
        Bundle bundle = new Bundle();
        bundle.putParcelable("BitmapImage", bitmap);
        PojoAlertMessage pojoModel = new PojoAlertMessage(getResources().getString(R.string.alert_title), R.mipmap.ic_launcher,
                getResources().getString(R.string.ok_text),
                true, true, true);
        bundle.putSerializable(MyConstant.DIALOG_TEXT, pojoModel);
        dialogFragment.setArguments(bundle);
        dialogFragment.setCancelable(true);
        dialogFragment.show(getFragmentManager(), "CommonDialog");
    }
}
