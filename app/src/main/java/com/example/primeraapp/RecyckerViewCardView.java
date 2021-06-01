package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyckerViewCardView extends AppCompatActivity {
    List<listElement> elementList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycker_view_card_view);

        init();
    }
    public void init(){
        elementList = new ArrayList<>();
        elementList.add(new listElement(R.drawable.boxeo,"Hombro","Serie de ejercicios para fortalecer el hombro"));
        elementList.add(new listElement(R.drawable.boxeo,"Espalda","Serie de ejercicios para fortalecer la espalda"));
        elementList.add(new listElement(R.drawable.boxeo,"Pecho","Serie de ejercicios para fortalecer el pecho"));
        elementList.add(new listElement(R.drawable.boxeo,"Abdominal","Serie de ejercicios para fortalecer los abdominales"));
        elementList.add(new listElement(R.drawable.boxeo,"Brazos","Serie de ejercicios para fortalecer los brazos"));
        ListAdapter listAdapter = new ListAdapter(elementList,this);
        RecyclerView recyclerView = findViewById(R.id.listRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}