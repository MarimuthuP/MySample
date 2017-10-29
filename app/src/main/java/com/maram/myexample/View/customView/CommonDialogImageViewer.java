package com.maram.myexample.View.customView;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.R;
import com.maram.myexample.View.Pojo.PojoAlertMessage;
import com.maram.myexample.View.Utils.MyConstant;

/**
 * Created by Marimuthu on 8/24/17.
 */

public class CommonDialogImageViewer extends DialogFragment implements View.OnClickListener {

    /**
     * This is the popup view
     */
    View viewPopup;

    /**
     * This is Alert Title Textview
     */
    TextView textViewAlertTitle;

    /**
     * This is Alert Title Icon
     */
    ImageView imageViewAlertTitleIcon;

    /**
     * This is Image Viewer
     */
    ImageView imageViewerBig;

    /**
     * This is Bottom Button layout
     */
    LinearLayout linearLayoutBottomButton;

    /**
     * This is OK button
     */
    Button buttonOk;

    /**
     * Duration set for Animation
     */
    int DURATION;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        DURATION = 500;
        startAnimation();
    }

    /**
     * Which method is used to start the Animation when the popup open
     */
    private void startAnimation() {
        final View decorView = getDialog()
                .getWindow()
                .getDecorView();

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,
                PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f));
        scaleDown.setDuration(DURATION);
        scaleDown.start();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_white));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_custom_image_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPopup = view;
        initiatePopup();
        setData();
    }

    /**
     * set the data into the fields whichever you get from previous
     */
    private void setData() {
        if (getArguments() != null) {


            Bitmap bitmap = getArguments().getParcelable("BitmapImage");
            PojoAlertMessage pojoAlertMessage = (PojoAlertMessage) getArguments().getSerializable(MyConstant.DIALOG_TEXT);
            System.out.println("NAME - " + pojoAlertMessage.getAlertTitle());

            if (!pojoAlertMessage.isAlertIcon()) {
                viewPopup.findViewById(R.id.iv_alert_img).setVisibility(View.GONE);
            }
            if (!pojoAlertMessage.isAlertTitle()) {
                viewPopup.findViewById(R.id.tv_alert_title).setVisibility(View.GONE);
            }

            if (!pojoAlertMessage.isOkButton()) {
                viewPopup.findViewById(R.id.btn_ok).setVisibility(View.GONE);
            }


            imageViewerBig.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
            textViewAlertTitle.setText(pojoAlertMessage.getAlertTitle());
            imageViewAlertTitleIcon.setImageResource(pojoAlertMessage.getAlertIcon());
            buttonOk.setText(pojoAlertMessage.getOkButtonText());
        }
    }

    /**
     * Which method is used to initiate the field for the dialog
     */
    private void initiatePopup() {
        textViewAlertTitle = viewPopup.findViewById(R.id.tv_alert_title);
        imageViewAlertTitleIcon = viewPopup.findViewById(R.id.iv_alert_img);
        imageViewerBig = viewPopup.findViewById(R.id.image_actual);
        buttonOk = viewPopup.findViewById(R.id.btn_ok);
        setOnClickEvent();
    }

    /**
     * Which method is used to invoke the click event.
     */
    private void setOnClickEvent() {
        buttonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                applyAnimation();
                Toast.makeText(getActivity(), "Yes Clicked", Toast.LENGTH_SHORT).show();
                //dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * Which method is used to invoke when dismiss the popup
     */
    private void applyAnimation() {
        final View decorView = getDialog()
                .getWindow()
                .getDecorView();

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,
                PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f),
                PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f),
                PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f));
        scaleDown.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                dismiss();
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        scaleDown.setDuration(DURATION);
        scaleDown.start();
    }
}
