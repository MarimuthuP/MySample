package com.maram.myexample.View.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.Presenter.IEnteredAmountValidation;
import com.maram.myexample.Presenter.IPopupCommunicatorFromList;
import com.maram.myexample.Presenter.IPopupItemClickedFromList;
import com.maram.myexample.Presenter.IReceiveAmount;
import com.maram.myexample.R;
import com.maram.myexample.View.Fragment.InputFieldListFragment;
import com.maram.myexample.View.Fragment.MenuFragment;
import com.maram.myexample.View.Fragment.PopupTypeFragment;
import com.maram.myexample.View.Utils.MyConstant;

public class MainActivity extends AppCompatActivity implements IEnteredAmountValidation, IMainCommunicator, IPopupCommunicatorFromList {

    /**
     * This is the framelayout variable to get the details.
     */
    FrameLayout mainFrame;

    /**
     *  This is the interface variable to access their method.
     */
    public IReceiveAmount iReceiveAmount;

    /**
     * Toolbar field
     */
    Toolbar toolBarTop;

    /**
     * Toolbar Title TextView
     */
    TextView tvToolBarTitle;

    /**
     * Drawer Layout
     */
    DrawerLayout drawerLayoutLeftMenu;

    public IPopupItemClickedFromList iPopupItemClickedFromList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
        setupToolBar();
        AttachFragment(savedInstanceState);
    }

    /**
     *  This method used to set the toolbar
     */
    private void setupToolBar() {
        //toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back));
        setSupportActionBar(toolBarTop);                                            // Setting/replace toolbar as the ActionBar
        toolBarTop.setTitle("");
        tvToolBarTitle.setText("Menu Option");                                      // set the custom textview for the Toolbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);                    // hide the current title from the Toolbar
        //toolBarTop.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));       // setting a logo in Toolbar
        //toolBarTop.setLogo(R.mipmap.ic_launcher);
        toolBarTop.setLogoDescription("LOGO");                                      // set description for the logo

        // Back button going to hide.. will make it like menu
        toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back)); // set icon for navigation button
        //toolBarTop.setSubtitle("Click here");                                       // set subtitle for toolbar
        toolBarTop.setNavigationContentDescription("Navigation content");           // set the navigation content string.
        //tvToolBarTitle.setTextColor(getResources().getColor(R.color.colorAccent));  // set text color for Toolbar title. subtitle color also can change.


        toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /**
         * Navigation Menu
         */
        /*toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_navimenu));
        toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutLeftMenu.openDrawer(Gravity.LEFT);
            }
        });*/
    }

    /**
     *  Which is used to attach the fragment when first execute..
     * @param savedInstanceState
     */
    private void AttachFragment(Bundle savedInstanceState) {
        Fragment fragment;
        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragment = new MenuFragment();
            fragmentTransaction.replace(mainFrame.getId(),fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     *  which is used to initiate the activiy.
     */
    private void initActivity() {
        mainFrame = (FrameLayout)findViewById(R.id.main_container);
        toolBarTop = (Toolbar)findViewById(R.id.toolbar_layout);
        tvToolBarTitle = (TextView)findViewById(R.id.toolbar_title);
        drawerLayoutLeftMenu = (DrawerLayout)findViewById(R.id.newdrawerlayout);
    }

    @Override
    public void onEnteredAmountWithComma(String amountValue, int keyValue) {
        iReceiveAmount.ReceiveTheAmountValue(amountValue, keyValue);
    }

    @Override
    public void openNextScreen(int keyValue) {
        Fragment fragmentObj;

        if(keyValue == MyConstant.NavigateScreen.INPUT_FIELD_KEY){
            fragmentObj = new InputFieldListFragment();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(mainFrame.getId(),fragmentObj,"INPUT_FIELD_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }
        if(keyValue == MyConstant.NavigateScreen.POPUP_TYPE_KEY){
            fragmentObj = new PopupTypeFragment();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(mainFrame.getId(),fragmentObj,"POPUP_TYPE_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void setupToolBarTitle(String toolBarName) {
        tvToolBarTitle.setText(toolBarName);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            if(fragment instanceof InputFieldListFragment){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
            }

            if(fragment instanceof PopupTypeFragment){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
            }
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_viewlist:
                drawerLayoutLeftMenu.openDrawer(Gravity.RIGHT);
                break;
            case R.id.action_new:
                Toast.makeText(this, "New Option Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh Option Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Option Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callToDismissDialog(String valueText, int keyValue) {
        iPopupItemClickedFromList.popupItemClicked(valueText,keyValue);
    }
}
