package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;

/**
 * Created by Marimuthu on 8/23/17.
 */

public class WebViewFragment extends Fragment implements Html.ImageGetter {

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
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_webview));
        initFragments();
    }

    /**
     * Which is used to initiate the fragment fields.
     */
    private void initFragments() {
        textViewWebContent = viewFragment.findViewById(R.id.text_webcontent);
        setWebContent();
    }

    private void setWebContent() {
        /*String content = "<p><center><b><h3>First, </h3></b><center><br/>" +
                "Please press the <img src ='leonardo.bmp'> button beside the to insert a new event.</p>" +
                "<p><b>Second,</b><br/>" +
                "Please insert the details of the event.</p>"+
                "<p>The icon of the is show the level of the event.<br/>" +
                "eg: <img src = 'tu1.png' > is easier to do.</p></td>";*/
        String content = "<p><center><b><h3>First, </h3></b><center><br/>" +
                "<h3><ul><li>Please press the button beside the to insert a new event.</li>" +
                "<li>Please press beside the to insert a new event.</li></ul></h3></p>" +
                "<p><b>Second,</b><br/>" +
                "Please insert the details of the event.</p>" +
                "<p>The icon of the is show the level of the event.<br/>" +
                "eg: <img src = 'tu1.png' > is easier to do.</p></td>";
        Spanned spanned = Html.fromHtml(content, this, null);
        textViewWebContent.setText(spanned);
    }

    @Override
    public Drawable getDrawable(String s) {
        int id = 0;

        if (s.equals("leonardo.bmp")) {
            id = R.drawable.leonardo;
        }

        if (s.equals("leonardo.bmp")) {
            id = R.drawable.leonardo;
        }
        /*LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(id);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());*/

        //return d;
        return getResources().getDrawable(R.drawable.leonardo);
    }
}
