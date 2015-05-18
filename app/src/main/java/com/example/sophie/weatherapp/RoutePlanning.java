package com.example.sophie.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class RoutePlanning extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_planning);
        Spinner();
        RangeBar();


    }

    public void Spinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner_dif = (Spinner) findViewById(R.id.spinner_dif);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.location_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<CharSequence> adapter_dif = ArrayAdapter.createFromResource(
                this, R.array.difficulty, android.R.layout.simple_spinner_item);
        adapter_dif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_dif.setAdapter(adapter_dif);
        spinner_dif.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Button myButton =(Button)findViewById(R.id.button1);
        myButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Spinner sp = (Spinner) findViewById(R.id.spinner);
                String spinnerString = null;
                spinnerString = sp.getSelectedItem().toString();
                int nPos = sp.getSelectedItemPosition();


                Toast.makeText(getApplicationContext(), "getSelectedItem=" + spinnerString,
                        Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "getSelectedItemPosition=" + nPos,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void RangeBar(){
        // create RangeSeekBar as Integer range between 0 and 50
        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(0, 50, this.getApplicationContext());
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            int originalMin = -1;
            int originalMax = -1;
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i("RoutePlanning", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);



                if((originalMin != minValue) && (minValue != 0)) {
                    originalMin = minValue;
                    Toast.makeText(getApplicationContext(), "Not less than " + minValue + "km",
                            Toast.LENGTH_LONG).show();


                }

                if((originalMax != maxValue) && (maxValue != 50)) {
                    originalMax = maxValue;
                    Toast.makeText(getApplicationContext(), "Not more than " + maxValue + "km",
                            Toast.LENGTH_LONG).show();


                }

            }
        });

        // add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) findViewById(R.id.rangeBarLayout);
        layout.addView(seekBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_route_planning, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

