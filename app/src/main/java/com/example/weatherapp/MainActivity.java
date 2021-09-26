 package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.app.VoiceInteractor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arthurivanets.bottomsheets.BottomSheet;
import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView cloud1, cloud2;
    TextView textView;
    ImageView img;
    public EditText edit;
    ObjectAnimator objectAnimator, obj;
    private LineChart chart;
    ListView myListView;
    private String[] textString = {"Tommorow", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday"};
    private int[] drawableIds = {R.drawable.ic_sun, R.drawable.ic_sun, R.drawable.ic_cloudy, R.drawable.ic_cloudy, R.drawable.ic_cloudy, R.drawable.ic_cloudy};
    private String[] weather = {"24","25","26","27","22","23"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get();
        textView = (TextView) findViewById(R.id.textDegree);
        chart = (LineChart) findViewById(R.id.chart);
        WeatherView weatherView = findViewById(R.id.weather_view);
        myListView = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this,  textString, drawableIds, weather);
        myListView.setAdapter(adapter);
        cloud1 = (ImageView) findViewById(R.id.cloudBold);
        cloud2 = (ImageView) findViewById(R.id.cloud);
        weatherView.setWeatherData(PrecipType.RAIN);
        objectAnimator = ObjectAnimator.ofFloat(cloud1, "x", 180);
        obj = ObjectAnimator.ofFloat(cloud2, "x", 300);
        objectAnimator.setDuration(4000);
        objectAnimator.start();
        obj.start();
        obj.setDuration(3000);
        LineDataSet lineDataSet = new LineDataSet(dataVals1(), "Data Set");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate();
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setDrawAxisLine(false);

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawAxisLine(false);

        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.setDrawBorders(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setClickable(false);
        chart.setDrawGridBackground(false);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        chart.animateX(2000);
        chart.animate().setDuration(2000);
        chart.animateY(2000);

    }
    private ArrayList<Entry> dataVals1()
    {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,2));
        dataVals.add(new Entry(2,122));
        dataVals.add(new Entry(3,10));
        dataVals.add(new Entry(4,50));
        return dataVals;
    }
    private void get()
    {
        String apiKey = "407dddc2c9f928b5f10972b5e362f704";
        String city = "Delhi";
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=407dddc2c9f928b5f10972b5e362f704";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    String temp = object.getString("temp");
                    System.out.println(temp);
                    Double d = Double.parseDouble(temp)-273.15;
                    textView.setText((d.toString().substring(0,4) + (char) 0x00B0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }


}