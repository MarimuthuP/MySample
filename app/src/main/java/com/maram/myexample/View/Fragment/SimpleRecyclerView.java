package com.maram.myexample.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maram.myexample.Presenter.IMainCommunicator;
import com.maram.myexample.R;
import com.maram.myexample.View.Adapter.MyCustomAdapter;
import com.maram.myexample.View.customView.CustomDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marimuthu on 9/27/17.
 */

public class SimpleRecyclerView extends Fragment{

    View mainView;
    RecyclerView recyclerViewList;
    RecyclerView.LayoutManager layoutManager;
    MyCustomAdapter myCustomAdapter;
    ArrayList<String> arrayListCommissionRate = new ArrayList<>(
            Arrays.asList(  "Convenience Store",
                            "Supermarkets or Grocery Stores",
                            "Shopping Centres",
                            "Department Stores",
                            "Clothing & Accessories",
                            "Fabric Stores",
                            "Fashion Shop",
                            "Cosmetics & Beauty Retailers",
                            "Eyewear Retailers",
                            "Watch Retailers",
                            "Jewellery Retailers",
                            "Bags/Luggage Retailers",
                            "Shoe Stores",
                            "Leather Goods Retailers"));
    IMainCommunicator iMainCommunicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simplelist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.title_simple_list));
        initializeFragment();
    }

    private void initializeFragment() {
        recyclerViewList = (RecyclerView)mainView.findViewById(R.id.recyclerViewlist);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewList.setLayoutManager(layoutManager);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.addItemDecoration(new CustomDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.divider_horizontal)));
        setAdapter();
    }

    private void setAdapter() {
        myCustomAdapter = new MyCustomAdapter(getActivity(),arrayListCommissionRate);
        recyclerViewList.setAdapter(myCustomAdapter);
    }
}
