package com.example.manuelfleivar.skynventory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNuevos extends Fragment {


    public FragmentNuevos() {
        // Required empty public constructor
    }
    public static FragmentNuevos newInstance(){

        FragmentNuevos fragment=new FragmentNuevos();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevos, container, false);
    }

}
