package com.stephany.potentiostat;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Graphing extends AppCompatActivity implements OnChartValueSelectedListener {

    LineChart mpLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphing);

        mpLineChart=(LineChart) findViewById(R.id.line_chart);

        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");

        LineDataSet lineDataSet1;
        LineDataSet lineDataSet2;

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        if(type!="CyclicVoltammetry")      {
            lineDataSet1 = new LineDataSet(readCSV(),"First Run");
            dataSets.add(lineDataSet1);
        }else {
            lineDataSet1 = new LineDataSet(read_cv_CSV().get(0),"First Run");
            lineDataSet2 = new LineDataSet(read_cv_CSV().get(1),"Second Run");
            dataSets.add(lineDataSet1);
            dataSets.add(lineDataSet2);
        }

        lineDataSet1.setLineWidth(2);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(R.color.shrine_pink_500);
        lineDataSet1.setCircleColor(Color.MAGENTA);

        mpLineChart.setBackgroundColor(Color.WHITE);
        mpLineChart.setNoDataText("No Data");
        mpLineChart.setNoDataTextColor(Color.BLUE);
        mpLineChart.setDrawGridBackground(true); //draw grid
        mpLineChart.setDrawBorders(true);  //borders
        mpLineChart.setBorderWidth(2);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.animateX(2000);
        mpLineChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.e("LABEL", String.valueOf(e.getX()));
    }

    @Override
    public void onNothingSelected() {

    }

    public ArrayList<Entry> readCSV(){

        ArrayList<Entry> dataVals = new ArrayList<Entry>();

        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");

        InputStream is;

        switch (type){
            case "Amperometry":
                is = getResources().openRawResource(R.raw.amperometry);
                break;
            case "CyclicVoltammetry":
                is = getResources().openRawResource(R.raw.cv);
                break;
            case "DirectPulseVoltammetry":
                is = getResources().openRawResource(R.raw.dpv);
                break;
            case "ImpedanceSpectroscopy":
                is = getResources().openRawResource(R.raw.is);
                break;
            default:
                is = getResources().openRawResource(R.raw.test);
                break;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                float f1 = Float.parseFloat(tokens[0]);
                float f2 = Float.parseFloat(tokens[1]);

                dataVals.add(new Entry(f1, f2));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(type == "ImpedanceSpectroscopy"){
            Collections.reverse(dataVals);
        }

        return dataVals;
    }

    public ArrayList<ArrayList<Entry>> read_cv_CSV(){

        ArrayList<Entry> dataVals1 = new ArrayList<Entry>();
        ArrayList<Entry> dataVals2 = new ArrayList<Entry>();

        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");

        InputStream is;

        switch (type){
            case "Amperometry":
                is = getResources().openRawResource(R.raw.amperometry);
                break;
            case "CyclicVoltammetry":
                is = getResources().openRawResource(R.raw.cv);
                break;
            case "DirectPulseVoltammetry":
                is = getResources().openRawResource(R.raw.dpv);
                break;
            case "ImpedanceSpectroscopy":
                is = getResources().openRawResource(R.raw.is);
                break;
            default:
                is = getResources().openRawResource(R.raw.test);
                break;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            int i = 0;
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                if(i%2 == 1){
                    dataVals1.add(new Entry(Float.parseFloat(tokens[0]),Float.parseFloat(tokens[1])));
                }
                else{
                    dataVals2.add(new Entry(Float.parseFloat(tokens[0]),Float.parseFloat(tokens[1])));
                }


            }
        }
        catch (IOException e){
        }

        ArrayList<ArrayList<Entry>> vals = new ArrayList<ArrayList<Entry>>();
        vals.set(0, dataVals1);
        vals.set(1, dataVals2);

        return vals;
    }
}