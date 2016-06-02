package com.example.manuelfleivar.skynventory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReparacion extends Fragment {


    public FragmentReparacion() {
        // Required empty public constructor
    }
    public static FragmentReparacion newInstance(){

        FragmentReparacion fragment=new FragmentReparacion();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reparacion, container, false);
    }

}
