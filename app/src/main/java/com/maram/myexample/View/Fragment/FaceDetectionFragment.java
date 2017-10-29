package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;


/**
 * Created by Marimuthu on 8/23/17.
 */

public class FaceDetectionFragment extends Fragment {

    /**
     * fragment layout view
     */
    View viewFragment;

    TextView textViewWebContent;

    IMainCommunicator iMainCommunicator;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator) activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_face_detection, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_facecamera));
        initFragments();
    }

    /**
     * Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        textViewWebContent = viewFragment.findViewById(R.id.text_webcontent);

    }


}


