package com.maram.myexample.View.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maram.myexample.R;
import com.maram.myexample.View.Pojo.PojoAlertMessage;
import com.maram.myexample.View.Utils.MyConstant;
import com.maram.myexample.View.customView.CommonDialogImageViewer;

import java.io.IOException;

/**
 * Created by Marimuthu on 10/21/17.
 */

public class TakePicture extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {

    /**
     * Toolbar field
     */
    Toolbar toolBarTop;
    /**
     * Toolbar Title TextView
     */
    TextView tvToolBarTitle;
    CardView cardViewFlag;
    //a variable to store a reference to the Image View at the main.xml file
    private ImageView imageView;
    //a variable to store a reference to the Surface View at the main.xml file
    private SurfaceView surfaceView;
    //a bitmap to display the captured image
    private Bitmap bitmap;
    //Camera variables
    //a surface holder
    private SurfaceHolder surfaceHolder;
    //a variable to control the camera
    private Camera mCamera;
    //the camera parameters
    private Camera.Parameters parameters;

    public static Bitmap rotateImages(Bitmap bmp, String imageUrl) {
        if (bmp != null) {
            ExifInterface ei;
            int orientation = 0;
            try {
                ei = new ExifInterface(imageUrl);
                orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL);

            } catch (IOException e) {
                e.printStackTrace();
            }
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();
            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_UNDEFINED:
                    matrix.postRotate(270);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    break;
                default:
                    break;
            }
            Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, bmpWidth,
                    bmpHeight, matrix, true);
            return resizedBitmap;
        } else {
            return bmp;
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        cardViewFlag = (CardView) findViewById(R.id.cardview_language_flag);
        toolBarTop = (Toolbar) findViewById(R.id.toolbar_layout);
        tvToolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolBar();

        imageView = (ImageView) findViewById(R.id.image_main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(this);

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        imageView.setOnClickListener(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        //get camera parameters
        parameters = mCamera.getParameters();

        //set camera parameters
        parameters.set("jpeg-quality", 100);
        parameters.set("orientation", "portrait");
        parameters.set("rotation", 270);
        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);

        mCamera.startPreview();

        //sets what code should be executed after the picture is taken
        Camera.PictureCallback mCall = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                //decode the data obtained by the camera into a Bitmap
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                bitmap = rotateImages(bitmap, "");

                //set the imageView
                surfaceView.setVisibility(View.GONE);
                imageView.setImageBitmap(bitmap);
            }
        };

        mCamera.takePicture(null, null, mCall);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        int cameraId = -1;
        try {
            Camera.CameraInfo info = new Camera.CameraInfo();
            for (int id = 0; id < Camera.getNumberOfCameras(); id++) {
                Camera.getCameraInfo(id, info);
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    cameraId = id;
                    break;
                }
            }
            mCamera = Camera.open(cameraId);
            mCamera.setPreviewDisplay(holder);
            //setCameraDisplayOrientation(this, cameraId);

        } catch (Exception e) {
            if (mCamera == null) {
            }
            mCamera.release();
            mCamera = null;
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //stop the preview
        mCamera.stopPreview();
        //release the camera
        mCamera.release();
        //unbind the camera from this object
        mCamera = null;
    }

    // FOR RESTRICT THE ROTATION
    public void setCameraDisplayOrientation(Activity activity, int cameraId) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        System.out.println("rotation 1" + rotation);
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }

        System.out.println("rotation result" + result);
        System.out.println("rotation result" + rotation);

        Camera.Parameters parameters = mCamera.getParameters();
        //parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        mCamera.setParameters(parameters);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_main:
                callCustomCommonDialog();
                break;
        }
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
        dialogFragment.show(getSupportFragmentManager(), "CommonDialog");
    }

    /**
     * This method used to set the toolbar
     */
    private void setupToolBar() {
        //toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back));
        cardViewFlag.setVisibility(View.GONE);
        setSupportActionBar(toolBarTop);                                            // Setting/replace toolbar as the ActionBar
        toolBarTop.setTitle("");
        tvToolBarTitle.setText("Hidden Camera");                                      // set the custom textview for the Toolbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);                    // hide the current title from the Toolbar

        toolBarTop.setLogoDescription("LOGO");                                      // set description for the logo

        // Back button going to hide.. will make it like menu
        toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back)); // set icon for navigation button

        //toolBarTop.setSubtitle("Surfaceview");                                       // set subtitle for toolbar

        //toolBarTop.setNavigationContentDescription("Navigation content");           // set the navigation content string.
        tvToolBarTitle.setTextColor(getResources().getColor(R.color.white));  // set text color for Toolbar title. subtitle color also can change.

        toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
