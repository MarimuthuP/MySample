package com.maram.myexample.View.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.ICommunicateClick;
import com.maram.myexample.Presenter.IEnteredAmountValidation;
import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.Presenter.IPopupCommunicatorFromList;
import com.maram.myexample.Presenter.IPopupItemClickedFromList;
import com.maram.myexample.Presenter.IReceiveAmount;
import com.maram.myexample.R;
import com.maram.myexample.View.Fragment.CheckBoxTypeFragment;
import com.maram.myexample.View.Fragment.FaceDetectionFragment;
import com.maram.myexample.View.Fragment.FlashFragment;
import com.maram.myexample.View.Fragment.InputFieldListFragment;
import com.maram.myexample.View.Fragment.MenuFragment;
import com.maram.myexample.View.Fragment.PopupTypeFragment;
import com.maram.myexample.View.Fragment.RadioButtonTypeFragment;
import com.maram.myexample.View.Fragment.SimpleRecyclerView;
import com.maram.myexample.View.Fragment.ToastTypeFragment;
import com.maram.myexample.View.Fragment.ToggleButtonTypeFragment;
import com.maram.myexample.View.Fragment.WebViewFragment;
import com.maram.myexample.View.Utils.MyConstant;

import java.io.File;

public class MainActivity extends AppCompatActivity implements IEnteredAmountValidation, IMainCommunicator, IPopupCommunicatorFromList, SearchView.OnQueryTextListener {

    /**
     *  This is the interface variable to access their method.
     */
    public IReceiveAmount iReceiveAmount;
    public IPopupItemClickedFromList iPopupItemClickedFromList;
    public ICommunicateClick iCommunicateClick;
    /**
     * This is the framelayout variable to get the details.
     */
    FrameLayout mainFrame;
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
    NavigationView navigationViewDrawer;
    ImageView ivHeaderPhoto;
    Menu menu;
    MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
        setupToolBar();
        AttachFragment(savedInstanceState);
        setupDrawerContent(navigationViewDrawer);
        //showOverflowMenu(true);
    }

    private void setupDrawerContent(NavigationView navigationViewDrawer) {
        navigationViewDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()){
            case R.id.menu_home:
                fragmentClass = MenuFragment.class;
                break;
            case R.id.menu_checkbox:
                fragmentClass = CheckBoxTypeFragment.class;
                break;
            case R.id.menu_radio_button:
                fragmentClass = RadioButtonTypeFragment.class;
                break;
            case R.id.menu_toggle:
                fragmentClass = ToggleButtonTypeFragment.class;
                break;
            case R.id.menu_snackbar:
                fragmentClass = ToastTypeFragment.class;
                break;
            case R.id.menu_sub1:
                fragmentClass = ToastTypeFragment.class;
                break;
            case R.id.menu_sub2:
                fragmentClass = ToastTypeFragment.class;
                break;
            default:
                fragmentClass = InputFieldListFragment.class;
        }

        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container,fragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayoutLeftMenu.closeDrawers();
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
        //toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back)); // set icon for navigation button

        //toolBarTop.setSubtitle("Click here");                                       // set subtitle for toolbar

        //toolBarTop.setNavigationContentDescription("Navigation content");           // set the navigation content string.
        //tvToolBarTitle.setTextColor(getResources().getColor(R.color.colorAccent));  // set text color for Toolbar title. subtitle color also can change.


        /*toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });*/

        /**
         * Navigation Menu
         */
        toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_navimenu));
        toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutLeftMenu.openDrawer(Gravity.START);
            }
        });

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
        navigationViewDrawer = (NavigationView)findViewById(R.id.navigationview_menu);

        //View headerLayout = navigationViewDrawer.inflateHeaderView(R.layout.navigation_header); // Inflate the header view at runtime
        //ivHeaderPhoto = headerLayout.findViewById(R.id.profile_image_view); // We can now look up items within the header if needed

        // There is usually only 1 header view.
        // Multiple header views can technically be added at runtime.
        // We can use navigationView.getHeaderCount() to determine the total number.
        View headerLayout = navigationViewDrawer.getHeaderView(0);
        ivHeaderPhoto = headerLayout.findViewById(R.id.profile_image_view);
        ivHeaderPhoto.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        ivHeaderPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ivHeaderPhoto.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_help));
                Toast.makeText(MainActivity.this, "Clicked photo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onEnteredAmountWithComma(String amountValue, int keyValue) {
        iReceiveAmount.ReceiveTheAmountValue(amountValue, keyValue);
    }

    @Override
    public void openNextScreen(int keyValue) {
        Fragment fragmentObj;

        if(keyValue == MyConstant.NavigateScreen.INPUT_FIELD_KEY){
            showOverflowMenu(true);
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
            showOverflowMenu(true);
            fragmentObj = new PopupTypeFragment();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(mainFrame.getId(),fragmentObj,"POPUP_TYPE_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }

        if(keyValue == MyConstant.NavigateScreen.TOAST_TYPE_KEY){
            showOverflowMenu(true);
            fragmentObj = new ToastTypeFragment();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(mainFrame.getId(),fragmentObj,"POPUP_TYPE_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }
        if(keyValue == MyConstant.NavigateScreen.SEARCHVIEW_TYPE_KEY){

            showOverflowMenu(true);
            fragmentObj = new SimpleRecyclerView();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(mainFrame.getId(),fragmentObj,"POPUP_TYPE_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }
        if(keyValue == MyConstant.NavigateScreen.WEBVIEW_TYPE_KEY){

            showOverflowMenu(true);
            fragmentObj = new WebViewFragment();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(mainFrame.getId(),fragmentObj,"POPUP_TYPE_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }
        if (keyValue == MyConstant.NavigateScreen.CAMERA_TYPE_KEY) {

            startActivity(new Intent(this, GooglePlaceAutoComplete.class));
        }

        if (keyValue == MyConstant.NavigateScreen.FACE_TYPE_KEY) {

            Intent cameraIntent = new Intent(this, FaceDetection.class);
            startActivityForResult(cameraIntent, 102);
        }
        if (keyValue == MyConstant.NavigateScreen.FLASH_TYPE_KEY) {

            startActivity(new Intent(this, TakePicture.class));

        }
    }

    /**
     * which is used to showing the action bar menu
     *
     * @param isVisible used to show and hide by this value
     */
    private void showOverflowMenu(boolean isVisible) {
        if(menu == null)
            return;
        menu.setGroupVisible(R.id.main_menu_group, isVisible);
        menu.setGroupVisible(R.id.search_group, !isVisible);
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
                showOverflowMenu(true);
            }

            if(fragment instanceof PopupTypeFragment){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
                showOverflowMenu(true);
            }
            if(fragment instanceof ToastTypeFragment){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
                showOverflowMenu(true);
            }
            if(fragment instanceof SimpleRecyclerView){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
                showOverflowMenu(true);
            }
            if(fragment instanceof WebViewFragment){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
                showOverflowMenu(true);
            }
            if (fragment instanceof FaceDetectionFragment) {
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
                showOverflowMenu(true);
            }
            if (fragment instanceof FlashFragment) {
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.title_menu));
                showOverflowMenu(true);
            }
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main,menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.setVisible(false);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                drawerLayoutLeftMenu.openDrawer(Gravity.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callToDismissDialog(String valueText, int keyValue) {
        iPopupItemClickedFromList.popupItemClicked(valueText,keyValue);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            try {
                String path = data.getExtras().getString("path");
                if (data != null) {
                    Bitmap bitmapImage = BitmapFactory.decodeFile(path);
                    int nh = (int) (bitmapImage.getHeight() * (150.0 / bitmapImage.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 200, nh, true);
                    //mUserPic.setImageDrawable(new BitmapDrawable(getResources(), scaled));
                    //imageViewProfilePic.setImageDrawable(new BitmapDrawable(getResources(), scaled));
                    iCommunicateClick.setImage(scaled);
                    ivHeaderPhoto.setImageDrawable(new BitmapDrawable(getResources(), scaled));

                    File f = new File(path);
                    if (f.exists()) {
                        f.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
