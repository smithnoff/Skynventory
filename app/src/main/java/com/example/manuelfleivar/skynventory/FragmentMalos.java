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
public class FragmentMalos extends Fragment {


    private DBManager mn;
    private ListView lista;
    private int total;

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
        View v=inflater.inflate(R.layout.fragment_malos, container, false);
        mn=new DBManager(getContext());
        final Cursor articulosLista;
        articulosLista=mn.ObtenerArticulosc(3);
        total=articulosLista.getCount();

        CursorAdapter adapter=new SimpleCursorAdapter(getContext(),android.R.layout.simple_expandable_list_item_2,articulosLista,new String[]{"codigo","nombre"},new int[]{android.R.id.text1,android.R.id.text2});
        lista=(ListView)v.findViewById(R.id.listView);
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
                Snackbar.make(v, "Total de Articulos en Mal Estado: "+total, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
