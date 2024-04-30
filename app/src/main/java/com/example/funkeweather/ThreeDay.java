// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ThreeDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_day);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        WeatherReportAdapter reportAdapter=new WeatherReportAdapter(this,new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(reportAdapter);
        WeatherReportDownloader reportDownloader=new WeatherReportDownloader(reportAdapter);
        reportDownloader.execute(cityName);
    }
}