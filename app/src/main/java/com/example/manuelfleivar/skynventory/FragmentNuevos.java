package com.example.manuelfleivar.skynventory;


import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNuevos extends Fragment {
ListView lista;
DBManager mn;
    private int total;

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
        View v=inflater.inflate(R.layout.fragment_nuevos, container, false);
        mn=new DBManager(getContext());
   Estado st=new Estado();

        final Cursor articulosLista;
        articulosLista=mn.ObtenerArticulosc(1);
        total=articulosLista.getCount();


        CursorAdapter adapter=new SimpleCursorAdapter(getContext(),android.R.layout.simple_expandable_list_item_2,articulosLista,new String[]{"codigo","nombre"},new int[]{android.R.id.text1,android.R.id.text2});
        lista=(ListView)v.findViewById(R.id.listView2);
        lista.setAdapter(adapter);

        return v;
    }
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onResume();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }

        Estado mainActivity = (Estado) getActivity();
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Total de Articulos Nuevos: "+total, Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show();
            }
        });
    }

}
