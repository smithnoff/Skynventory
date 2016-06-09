package com.example.manuelfleivar.skynventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Ayuda extends AppCompatActivity {
       TextView t1,t2,t3,t4,t5;
    EditText e1,e2,e3,e4,e5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);


        t1=(TextView)findViewById(R.id.textView11);
        t2=(TextView)findViewById(R.id.textView12);
        t3=(TextView)findViewById(R.id.textView13);
        t4=(TextView)findViewById(R.id.textView14);
        t5=(TextView)findViewById(R.id.textView15);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);
        e4=(EditText)findViewById(R.id.editText5);
        e5=(EditText)findViewById(R.id.editText6);


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getVisibility()==View.GONE)
                e1.setVisibility(View.VISIBLE);
                else
                    e1.setVisibility(View.GONE);

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e2.getVisibility()==View.GONE)
                    e2.setVisibility(View.VISIBLE);
                else
                    e2.setVisibility(View.GONE);
            }
        });t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e3.getVisibility()==View.GONE)
                    e3.setVisibility(View.VISIBLE);
                else
                    e3.setVisibility(View.GONE);            }
        });t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e4.getVisibility()==View.GONE)
                    e4.setVisibility(View.VISIBLE);
                else
                    e4.setVisibility(View.GONE);            }
        });t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e5.getVisibility()==View.GONE)
                    e5.setVisibility(View.VISIBLE);
                else
                    e5.setVisibility(View.GONE);            }
        });




    }
}
