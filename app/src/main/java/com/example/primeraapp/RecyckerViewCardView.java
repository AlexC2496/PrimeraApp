package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyckerViewCardView extends AppCompatActivity {
    List<listElement> elementList;
    RecyclerView recyckerViewCardView;

    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycker_view_card_view);
        recyckerViewCardView=(RecyclerView)findViewById(R.id.listRV);
        recyckerViewCardView.setLayoutManager(new LinearLayoutManager(this));

        AdminSQLiteOpenHelper adminSQLiteOpenHelper=new AdminSQLiteOpenHelper(getApplicationContext());

        listAdapter = new ListAdapter(adminSQLiteOpenHelper.mostarEjercicios());
        recyckerViewCardView.setAdapter(listAdapter);


    }

}
