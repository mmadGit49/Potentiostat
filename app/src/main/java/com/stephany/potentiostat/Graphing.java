package com.stephany.potentiostat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graphing extends AppCompatActivity {

    LineChart mpLineChart;
    private Button btnTesting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphing);
        btnTesting=findViewById(R.id.btnTesting);

        btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCSV("/dpv.csv");
            }
        });

        mpLineChart=(LineChart) findViewById(R.id.line_chart);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"First Run");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        lineDataSet1.setLineWidth(2);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setColor(R.color.shrine_pink_500);
        lineDataSet1.setCircleColor(Color.MAGENTA);
        dataSets.add(lineDataSet1);
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);

        mpLineChart.setBackgroundColor(Color.WHITE);
        mpLineChart.setNoDataText("No Data");
        mpLineChart.setNoDataTextColor(Color.BLUE);

        mpLineChart.setDrawGridBackground(true);  //draw grid

        mpLineChart.setDrawBorders(true);  //borders
        mpLineChart.setBorderWidth(2);
        mpLineChart.animateX(10000);
        mpLineChart.invalidate();
    }

    public void readCSV(String path){

        try {
            String csvfileString = "C:\\Users\\Stephany\\Desktop\\Potentiostat\\app\\src\\main\\assets\\dpv.csv";
            File csvfile = new File(csvfileString);
            CSVReader reader = new CSVReader(new FileReader(csvfile.getAbsolutePath()));
            Log.d("Button", csvfile.getAbsolutePath());
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
//                System.out.println(nextLine[0] + nextLine[1] + "etc...");
                Log.d("CSVValue",nextLine[0] + nextLine[1]);
            }
        } catch (Exception e) {
            Log.d("Button", e.getMessage());
        }

    }
    private ArrayList<Entry> dataValues1()
    {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(-0.0999451f,0.0000970627f));
        dataVals.add(new Entry(-0.0949097f,0.000134745f));
        dataVals.add(new Entry(-0.0898743f,0.000134595f));
        return dataVals;
    }
}