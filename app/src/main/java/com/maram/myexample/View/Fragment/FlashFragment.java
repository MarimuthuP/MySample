package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.CamerService;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class FlashFragment extends Fragment implements View.OnClickListener {

    public static Camera cam = null;
    /**
     * fragment layout view
     */
    View viewFragment;
    LinearLayout linearLayoutFrontFlash;
    LinearLayout linearLayoutBackFlash;
    IMainCommunicator iMainCommunicator;
    ImageView imageViewBackCamera;

    ImageView imageViewFrontCamera;

    boolean isBackFlashOn = false;

    boolean isFrontFlashOn = false;

    boolean hasFlash;

    private Camera.Parameters mParameters = null;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator) activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flashcamera, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_flash_camera));
        initFragments();
    }

    /**
     * Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        linearLayoutFrontFlash = viewFragment.findViewById(R.id.ll_front_flash);
        linearLayoutBackFlash = viewFragment.findViewById(R.id.ll_back_flash);
        imageViewBackCamera = viewFragment.findViewById(R.id.image_back_on);
        imageViewFrontCamera = viewFragment.findViewById(R.id.image_front_on);

        linearLayoutFrontFlash.setOnClickListener(this);
        linearLayoutBackFlash.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_front_flash:
                frontFlashOnOrOff();
                break;
            case R.id.ll_back_flash:
                backFlashOnOrOff();
                break;
        }
    }

    private void frontFlashOnOrOff() {
        Toast.makeText(getActivity(), "Front Flash ON", Toast.LENGTH_SHORT).show();
        Intent front_translucent = new Intent(getActivity()
                .getApplicationContext(), CamerService.class);
        front_translucent.putExtra("Front_Request", true);
        //front_translucent.putExtra("Quality_Mode", camCapture.getQuality());
        getActivity().getApplicationContext().startService(
                front_translucent);
    }


    private void backFlashOnOrOff() {
        if (!isBackFlashOn)
            turnOnBackFlash();
        else
            turnOffBackFlash();
    }

    private void turnOffBackFlash() {
        try {
            isBackFlashOn = false;
            if (getActivity().getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopPreview();
                cam.release();
                cam = null;
                imageViewBackCamera.setImageDrawable(getResources().getDrawable(R.mipmap.ic_flashoff));
                Toast.makeText(getActivity(), "Back Flash OFF", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Exception flashLightOff",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void turnOnBackFlash() {
        try {
            isBackFlashOn = true;
            hasFlash = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            if (hasFlash) {
                cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
                imageViewBackCamera.setImageDrawable(getResources().getDrawable(R.mipmap.ic_flashon));
                Toast.makeText(getActivity(), "Back Flash ON", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("Error");
                alert.setMessage("Sorry, your device doesn't support flash light!");
                alert.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                    }
                });
                alert.show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Exception flashLightOn()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        backFlashOnOrOff();
    }
}
