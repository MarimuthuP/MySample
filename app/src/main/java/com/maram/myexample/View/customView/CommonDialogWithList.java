package com.maram.myexample.View.customView;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.myexample.Presenter.CalculationPresenter;
import com.maram.myexample.Presenter.IPopupCommunicatorFromList;
import com.maram.myexample.Presenter.IPopupItemClickedFromList;
import com.maram.myexample.R;
import com.maram.myexample.View.Activity.MainActivity;
import com.maram.myexample.View.Pojo.PojoAlertMessage;
import com.maram.myexample.View.Utils.MyConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marimuthu on 8/24/17.
 */

public class CommonDialogWithList extends DialogFragment implements View.OnClickListener {

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
     * This is Alert Message1 Textview
     */
    TextView textViewAlertMessage1;

    /**
     * This is Alert Message2 Textview
     */
    TextView textViewAlertMessage2;

    /**
     * This is Alert Message3 Textview
     */
    TextView textViewAlertMessage3;

    /**
     * This is Bottom Button layout
     */
    LinearLayout linearLayoutBottomButton;

    /**
     * This is OK button
     */
    Button buttonOk;

    /**
     * This is Cancel button
     */
    Button buttonCancel;

    /**
     * Duration of the Animating view
     */
    int DURATION;

    /**
     * Arraylist keeps owner list
     */
    ArrayList<String> stringArrayListOwner;

    /**
     * recyclerview list
     */
    RecyclerView recyclerViewList;

    /**
     * recyclerview layout manager
     */
    RecyclerView.LayoutManager layoutManager;

    /**
     * edittext for popup custom amount
     */
    EditText editTextCustomAmount;

    IPopupCommunicatorFromList iPopupCommunicatorFromList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iPopupCommunicatorFromList = ((IPopupCommunicatorFromList) activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        DURATION = 500;
        startAnimationWhenDialogOpen();
    }

    /**
     * Which method is used to start the Animation when the popup open
     */
    private void startAnimationWhenDialogOpen() {
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
        return inflater.inflate(R.layout.dialog_custom_list_layout, container, false);
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

            PojoAlertMessage pojoAlertMessage = (PojoAlertMessage) getArguments().getSerializable(MyConstant.DIALOG_TEXT);

            if (!pojoAlertMessage.isAlertIcon()) {
                viewPopup.findViewById(R.id.iv_alert_img).setVisibility(View.GONE);
            }
            if (!pojoAlertMessage.isAlertTitle()) {
                viewPopup.findViewById(R.id.tv_alert_title).setVisibility(View.GONE);
            }
            /*if(!pojoAlertMessage.isAlertMessage1()){
                viewPopup.findViewById(R.id.tv_note_header1).setVisibility(View.GONE);
            }
            if(!pojoAlertMessage.isAlertMessage2()){
                viewPopup.findViewById(R.id.tv_note_content1).setVisibility(View.GONE);
            }
            if(!pojoAlertMessage.isAlertMessage3()){
                viewPopup.findViewById(R.id.tv_note_content2).setVisibility(View.GONE);
            }*/
            if (!pojoAlertMessage.isOkButton()) {
                viewPopup.findViewById(R.id.btn_ok).setVisibility(View.GONE);
            }
            if (!pojoAlertMessage.isCancelButton()) {
                viewPopup.findViewById(R.id.btn_cancel).setVisibility(View.GONE);
            }
            if (pojoAlertMessage.isRecyclerList()) {
                viewPopup.findViewById(R.id.linearlayout_recyclerview).setVisibility(View.VISIBLE);
                stringArrayListOwner = getArguments().getStringArrayList("OwnerList");
                recyclerViewList = (RecyclerView) viewPopup.findViewById(R.id.recyclerview_list);
                recyclerViewList.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerViewList.setLayoutManager(layoutManager);
                recyclerViewList.addItemDecoration(new CustomDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.divider_horizontal)));
                RecyclerView.Adapter recyclerAdapter = new CustomPopupListAdapter(getActivity(), stringArrayListOwner, MyConstant.ListType.OWNER_TYPE);
                recyclerViewList.setAdapter(recyclerAdapter);
            }
            if (!pojoAlertMessage.isEnterAmount()) {
                viewPopup.findViewById(R.id.lineanlayout_enteramt).setVisibility(View.GONE);
            }

            textViewAlertTitle.setText(pojoAlertMessage.getAlertTitle());

            imageViewAlertTitleIcon.setImageResource(pojoAlertMessage.getAlertIcon());

            //textViewAlertMessage1.setText(pojoAlertMessage.getAlertMessage1());
            //textViewAlertMessage2.setText(pojoAlertMessage.getAlertMessage2());
            //textViewAlertMessage3.setText(pojoAlertMessage.getAlertMessage3());

            buttonOk.setText(pojoAlertMessage.getOkButtonText());
            buttonCancel.setText(pojoAlertMessage.getCancelButtonText());
        }
    }

    /**
     * Which method is used to initiate the field for the dialog
     */
    private void initiatePopup() {
        textViewAlertTitle = (TextView) viewPopup.findViewById(R.id.tv_alert_title);
        textViewAlertMessage1 = (TextView) viewPopup.findViewById(R.id.tv_note_header1);
        textViewAlertMessage2 = (TextView) viewPopup.findViewById(R.id.tv_note_content1);
        textViewAlertMessage3 = (TextView) viewPopup.findViewById(R.id.tv_note_content2);
        editTextCustomAmount = (EditText) viewPopup.findViewById(R.id.edt_custom_amt);

        imageViewAlertTitleIcon = (ImageView) viewPopup.findViewById(R.id.iv_alert_img);

        buttonOk = (Button) viewPopup.findViewById(R.id.btn_ok);
        buttonCancel = (Button) viewPopup.findViewById(R.id.btn_cancel);
        setOnClickEvent();
    }

    /**
     * Which method is used to invoke the click event.
     */
    private void setOnClickEvent() {
        buttonOk.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                startAnimationWhenDialogClose();
                Toast.makeText(getActivity(), "Yes Clicked", Toast.LENGTH_SHORT).show();
                iPopupCommunicatorFromList.callToDismissDialog(editTextCustomAmount.getText().toString(), 25);
                startAnimationWhenDialogClose();
                break;
            case R.id.btn_cancel:
                startAnimationWhenDialogClose();
                Toast.makeText(getActivity(), "No Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * Which method is used to invoke when dismiss the popup
     */
    public void startAnimationWhenDialogClose() {
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
                manualDismissDialog();
                //dismiss();
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

    /**
     * Which method is used to dismiss the dialog
     */
    private void manualDismissDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("CommonDialogInput");
        if (prev != null) {
            ft.remove(prev);
            ft.commitAllowingStateLoss();
            getFragmentManager().popBackStack("CommonDialogInput",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }
    }

    /**
     * This is the inner Adapter class for showing List items in
     * recycler view. when user select the particular item from
     * the list. need to close the popup with animation effect.
     * and also set the selected value in the corresponding
     * field in the fragment.
     */
    public class CustomPopupListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /**
         * This is the context of dialog fragment
         */
        Context context;

        /**
         * string array of items
         */
        List<String> listname;

        /**
         * This is key value for the type
         */
        int typeId;

        /**
         * This is the interface object to get the selected value & set to fragment field.
         */
        IPopupCommunicatorFromList iPopupCommunicatorFromList;

        /**
         * constructor method
         *
         * @param context
         * @param listname
         * @param type_Id
         */
        public CustomPopupListAdapter(Context context, List<String> listname, int type_Id) {
            this.context = context;
            this.listname = listname;
            this.typeId = type_Id;
            iPopupCommunicatorFromList = ((MainActivity) context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ViewGroup viewGroupRow = null;
            switch (viewType) {
                case 100:
                    viewGroupRow = (ViewGroup) layoutInflater.inflate(R.layout.row_list_layout, parent, false);
                    if (viewGroupRow != null) {
                        OwnerTypeViewHolder ownerTypeViewHolder = new OwnerTypeViewHolder(viewGroupRow);
                        return ownerTypeViewHolder;
                    } else {
                        return null;
                    }
                case 101:
                    viewGroupRow = (ViewGroup) layoutInflater.inflate(R.layout.row_list_layout, parent, false);
                    if (viewGroupRow != null) {
                        AddressTypeViewHolder addressTypeViewHolder = new AddressTypeViewHolder(viewGroupRow);
                        return addressTypeViewHolder;
                    } else {
                        return null;
                    }
            }
            return null;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof OwnerTypeViewHolder) {
                ((OwnerTypeViewHolder) viewHolder).textViewItemName.setText(listname.get(position));
                ((OwnerTypeViewHolder) viewHolder).textViewItemName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String value = ((OwnerTypeViewHolder) viewHolder).textViewItemName.getText().toString();
                        Toast.makeText(context, "Clicked " + value, Toast.LENGTH_SHORT).show();
                        //CommonDialogWithList.getInstanceObj().dismissDialog();
                        iPopupCommunicatorFromList.callToDismissDialog(value, 25);
                        startAnimationWhenDialogClose();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return listname.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (typeId == MyConstant.ListType.OWNER_TYPE) {
                typeId = MyConstant.ListType.OWNER_TYPE;
            } else if (typeId == MyConstant.ListType.ADDRESS_TYPE) {
                typeId = MyConstant.ListType.ADDRESS_TYPE;
            }
            return typeId;
        }

        /**
         * This is the viewholder of owner type
         */
        public class OwnerTypeViewHolder extends RecyclerView.ViewHolder {

            /**
             * Item name of textview
             */
            TextView textViewItemName;

            public OwnerTypeViewHolder(View itemView) {
                super(itemView);
                textViewItemName = (TextView) itemView.findViewById(R.id.textview_row_name);
            }
        }

        /**
         * Which viewholder for the Address type
         */
        public class AddressTypeViewHolder extends RecyclerView.ViewHolder {

            /**
             * name of address textview
             */
            TextView textViewAddressName;

            /**
             * name of address textview
             */
            TextView textViewItemName;

            public AddressTypeViewHolder(View itemView) {
                super(itemView);
                textViewAddressName = (TextView) itemView.findViewById(R.id.textview_row_name);
                textViewItemName = (TextView) itemView.findViewById(R.id.textview_row_name);
            }
        }
    }
}
