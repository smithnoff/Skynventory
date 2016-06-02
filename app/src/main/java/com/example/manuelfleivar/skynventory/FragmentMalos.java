package com.example.manuelfleivar.skynventory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMalos extends Fragment {


    public FragmentMalos() {
        // Required empty public constructor
    }
    public static FragmentMalos newInstance(){

        FragmentMalos fragment=new FragmentMalos();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_malos, container, false);
    }

}
