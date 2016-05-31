package com.example.manuelfleivar.skynventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Buscar extends AppCompatActivity {

    private EditText filterText;
    TextView emptyText;

    private ArrayAdapter<String> listAdapter;
    DBManager mn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        mn=new DBManager(Buscar.this);
        filterText = (EditText)findViewById(R.id.editText);
        emptyText= (TextView)findViewById(R.id.noart);

        ListView itemList = (ListView)findViewById(R.id.lvab_lista);

        String [] listViewAdapterContent = {"School", "House", "Building", "Food", "Sports", "Dress", "Ring"};
        List<String> articulosLista;
        articulosLista=mn.ObtenerArticulos();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, articulosLista);
        itemList.setEmptyView(emptyText);
        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// make Toast when click
                Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
            }
        });
        filterText.addTextChangedListener(new TextWatcher() {

            //un comentario
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Buscar.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }




}
