package com.maram.myexample.View.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.maram.myexample.R;
import com.maram.myexample.View.customView.FaceOverlayView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Marimuthu on 10/12/17.
 */

public class FaceDetection extends AppCompatActivity {


    private static final String TAG = "FaceTest";
    public static String path;
    private static Camera camera = null;
    private static boolean safeToTakePicture = false;
    private static FaceOverlayView overlayView;
    ImageView imageViewFlash;
    boolean isFrontFlashOn = false;
    boolean hasFlash;
    private SurfaceView preview;
    private CameraListener cameraListener;
    private Camera.PictureCallback jpegCallback;
    private SurfaceHolder surfaceHolder;
    private File mPhotoFile;
    private int mNoOfFaces = 0;
    private boolean isFaceDetected = false;
    private int captureCountWithoutFD = 0;
    private SurfaceView overlay;
    private OverlayListener overlayListener;

    // FOR RESTRICT THE ROTATION
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
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

        camera.setDisplayOrientation(result);
        Camera.Parameters parameters = camera.getParameters();
        //parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        parameters.setRotation(rotation);

        camera.setParameters(parameters);

        overlayView.setDisplayOrientation(result);
        safeToTakePicture = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_face_detection);
        preview = (SurfaceView) findViewById(R.id.surfaceView);
        imageViewFlash = (ImageView) findViewById(R.id.image_front_flash);
        surfaceHolder = preview.getHolder();
        cameraListener = new CameraListener(preview);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        takePicture();
        overlay = (SurfaceView) findViewById(R.id.overlay);
        overlayListener = new OverlayListener(overlay);

        overlayView = new FaceOverlayView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        frameLayout.addView(overlayView);
        //buttonLogout.setVisibility(View.GONE);
        backFinish();
        takeAndCancelListner();

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            imageViewFlash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flashOnOrOff();
                }
            });
        } else {
            imageViewFlash.setEnabled(false);
            imageViewFlash.setImageDrawable(getResources().getDrawable(R.mipmap.ic_flashoff));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNightTime())
            showAlert(getString(R.string.photo_msg));
    }

    @Override
    protected void onStop() {
        super.onStop();
        //flashOnOrOff();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flashOnOrOff();
    }

    private void takePicture() {
        jpegCallback = new Camera.PictureCallback() {

            public void onPictureTaken(byte[] data, Camera camera) {

                boolean isNoSpance = false;
                if (data != null) {
                    int screenWidth = getResources().getDisplayMetrics().widthPixels;
                    int screenHeight = getResources().getDisplayMetrics().heightPixels;
                    Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);

                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        Bitmap scaled = Bitmap.createScaledBitmap(bm,
                                screenHeight, screenWidth, true);
                        int w = scaled.getWidth();
                        int h = scaled.getHeight();
                        Matrix mtx = new Matrix();
                        mtx.postRotate(-90);
                        bm = Bitmap.createBitmap(scaled, 0, 0, w, h, mtx, true);

                    } else {
                        Bitmap scaled = Bitmap.createScaledBitmap(bm,
                                screenWidth, screenHeight, true);
                        bm = scaled;
                    }

                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 80, bytes);

                    try {
                        mPhotoFile = new File(Environment.getExternalStorageDirectory()
                                + File.separator + "test.jpg");
                        mPhotoFile.createNewFile();
                        FileOutputStream fo = new FileOutputStream(mPhotoFile);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                        isNoSpance = true;
                    }
                }
                if (isNoSpance) {
                    Intent i = getIntent();
                    setResult(110, i);
                    finish();
                } else {
                    path = mPhotoFile.getAbsolutePath();
                    Intent i = getIntent();
                    i.putExtra("path", path);
                    setResult(102, i);
                    finish();
                }
            }
        };
    }

    private void takeAndCancelListner() {
        findViewById(R.id.capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* try {
                    if (camera != null && safeToTakePicture) {
                        camera.takePicture(null, null, jpegCallback);
                        safeToTakePicture = false;
                    }
                } catch (Exception e) {
                    Utils.showCustomToastMsg(NewFaceDetectionActivity.this, R.string.error_camera, false);

                }*/
                try {
                    if (!isFaceDetected && captureCountWithoutFD < 1) {
                        showAlert(getResources().getString(R.string.no_face_detected_more));
                        captureCountWithoutFD++;
                        return;
                    }
                    if (camera != null && safeToTakePicture && !isFaceDetected) {
                        camera.takePicture(null, null, jpegCallback);
                        safeToTakePicture = false;
                    } else {
                        if (camera != null && safeToTakePicture && mNoOfFaces == 1) {
                            camera.takePicture(null, null, jpegCallback);
                            safeToTakePicture = false;
                        } else if (mNoOfFaces == 0 && safeToTakePicture) {
                            showAlert(getResources().getString(R.string.no_face_detected));
                        } else if (mNoOfFaces > 1) {
                            showAlert(getResources().getString(R.string.more_face));
                        }
                    }
                } catch (Exception e) {
                    showAlert(getResources().getString(R.string.error_camera));

                }
            }
        });
        /*findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        preview.getHolder().addCallback(cameraListener);
        overlay.getHolder().addCallback(overlayListener);
    }

    private void backFinish() {
        /*backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                finish();
            }
        });*/
    }

    private void showAlert(String message) {
        //AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(FaceDetection.this, R.style.AppThemeDialogAlertGreen)).create();
        alertDialog.setTitle(getResources().getString(R.string.alert_title));
        alertDialog.setMessage(message);
        alertDialog.setIcon(R.mipmap.ic_launcher);

        alertDialog.setCancelable(true);
        alertDialog.setButton(getResources().getString(R.string.ok_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(FaceDetection.this, "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }

    public boolean isNightTime() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        //timeOfDay= 17;
        return timeOfDay >= 16 || timeOfDay < 7;
    }

    private void flashOnOrOff() {
        if (!isFrontFlashOn)
            turnOnFlash();
        else
            turnOffFlash();
    }

    private void turnOffFlash() {
        try {
            isFrontFlashOn = false;
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(p);
                imageViewFlash.setImageDrawable(null);
                imageViewFlash.setImageDrawable(getResources().getDrawable(R.mipmap.ic_flashoff));
                Toast.makeText(this, "Back Flash OFF", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception flashLightOff",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void turnOnFlash() {
        try {
            isFrontFlashOn = true;
            hasFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            if (hasFlash) {
                //camera = Camera.open();
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                //p.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                camera.setParameters(p);
                //camera.startPreview();
                imageViewFlash.setImageDrawable(null);
                imageViewFlash.setImageDrawable(getResources().getDrawable(R.mipmap.ic_flashon));
                Toast.makeText(this, "Back Flash ON", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog alert = new AlertDialog.Builder(this).create();
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
            Toast.makeText(this, "Exception flashLightOn()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class CameraListener implements SurfaceHolder.Callback, Camera.FaceDetectionListener {
        private SurfaceView surfaceView;
        private SurfaceHolder surfaceHolder;

        public CameraListener(SurfaceView surfaceView) {
            this.surfaceView = surfaceView;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            surfaceHolder = holder;
            safeToTakePicture = true;
            try {
                int cameraId = -1;
                Camera.CameraInfo info = new Camera.CameraInfo();
                for (int id = 0; id < Camera.getNumberOfCameras(); id++) {
                    Camera.getCameraInfo(id, info);
                    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        cameraId = id;
                        break;
                    }
                }
                camera = Camera.open(cameraId);
                camera.setPreviewDisplay(holder);
                camera.getParameters().setPreviewFpsRange(1, 20);
                //setCameraDisplayOrientation(90, camera);
                setCameraDisplayOrientation(FaceDetection.this, cameraId);
            } catch (Exception e) {
                if (camera == null) {
                    showAlert(getString(R.string.camera_permission_error));
                }
                Log.e(TAG, e.toString(), e);
            }
        }

//        public void setCameraDisplayOrientation(int degrees, Camera camera) {
//                Camera.CameraInfo info = new Camera.CameraInfo();
//                int result = (info.orientation + degrees) % 360;
//                camera.setDisplayOrientation(result);
//                overlayView.setDisplayOrientation(result);
//                safeToTakePicture = true;
//        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format,
                                   int width, int height) {
            try {
                surfaceHolder = holder;
                camera.startPreview();
                camera.setFaceDetectionListener(cameraListener);
                camera.startFaceDetection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.setFaceDetectionListener(null);
                camera.release();
                camera = null;
            }
        }

        @Override
        public void onFaceDetection(Camera.Face[] faces, Camera camera) {

            isFaceDetected = true;
            int length = faces.length;
            if (length == 0) {
                mNoOfFaces = faces.length;
                return;
            }
            Camera.Face face = faces[0];
            overlayListener.drawFace(faceRect2PixelRect(face), Color.RED);
            overlayView.setFaces(faces);
            mNoOfFaces = faces.length;
            Log.e("face", "No of face =" + faces.length);
        }

        private Rect faceRect2PixelRect(Camera.Face face) {
            int w = surfaceView.getWidth();
            int h = surfaceView.getHeight();
            Rect rect = new Rect();

            // Left and right inversion because the front camera, portrait so coordinate axis inversion
            rect.left = w * (-face.rect.top + 1000) / 2000;
            rect.right = w * (-face.rect.bottom + 1000) / 2000;
            rect.top = h * (-face.rect.left + 1000) / 2000;
            rect.bottom = h * (-face.rect.right + 1000) / 2000;
            //Log.d(TAG, "rect=" + face.rect + "=>" + rect);
            return rect;
        }
    }

    private class OverlayListener implements SurfaceHolder.Callback {
        private SurfaceView surfaceView;
        private SurfaceHolder surfaceHolder;

        private Paint paint = new Paint();

        public OverlayListener(SurfaceView surfaceView) {
            this.surfaceView = surfaceView;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            surfaceHolder = holder;
            surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(surfaceView.getWidth() / 100);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            surfaceHolder = holder;
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // nop.
        }

        public void drawFace(Rect rect1, int color) {
            try {
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    try {
                        canvas.drawColor(Color.RED, PorterDuff.Mode.CLEAR);
                        paint.setColor(color);
                        //canvas.drawRect(rect1, paint);
                        canvas.drawRect(rect1, paint);
                    } finally {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            } catch (IllegalArgumentException e) {
                Log.w(TAG, e.toString());
            }
        }
    }

}
