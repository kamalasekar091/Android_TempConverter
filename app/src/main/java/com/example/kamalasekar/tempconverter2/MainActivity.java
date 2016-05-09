package com.example.kamalasekar.tempconverter2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;  //declare seekbar object
    TextView textView;
    TextView textView2;
    //declare member variables for SeekBar
    int discrete = 0;
    int start = 50;
    int start_position = 50; //progress tracker
    int temp = 0;
    //declare objects for ViewStub
    ViewStub stub;
    CheckBox checkBox;
    //declare Listview object
    ListView lv;

    /**
     * The oncreate material to load the page on login
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare viewstub object
        stub = (ViewStub) findViewById(R.id.viewStub1);
        @SuppressWarnings("unused")
        View inflated = stub.inflate();
        stub.setVisibility(View.INVISIBLE);

        //viewstub logic

        checkBox = (CheckBox) findViewById(R.id.checkBox1);
        //handle checkbox click event
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    //remove objects from parent view to allow for child view
                    checkBox.setVisibility(View.GONE);
                    seekBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);

                    stub.setVisibility(View.VISIBLE);

                }
            }
        });

        //seekbar logic

        textView = (TextView) findViewById(R.id.textview);
        textView.setText("     Celsius at 0 degrees");  //set for default view
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setProgress(start_position);

        textView2 = (TextView) findViewById(R.id.textView2);
        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                if (temp == 0) {//for initial view result

                    //Text view to display the Fahrenheit result in case of celsius of zero degree celsius
                    textView2.setText("     Fahrenheit result 32 degrees");
//                    Toast.makeText(getBaseContext(), "Fahrenheit result 32 degrees",
//                            Toast.LENGTH_SHORT).show();
                }
                else {

                    //Text view to display the Fahrenheit result in case of celsius of zero degree celsius
                    textView2.setText("     Fahrenheit result " + discrete + " degrees");
//                    Toast.makeText(getBaseContext(), "Fahrenheit result "
//                            + String.valueOf(discrete) + " degrees", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            // Method to calculate celsius to farhenite
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // TODO Auto-generated method stub
                // To convert progress passed as discrete (Fahrenheit) value
                temp = progress - start;
                discrete = (int) Math.round((((temp * 9.0) / 5.0) + 32));  //convert C to F temp
                textView.setText("     Celsius at " + temp + " degrees");
                Log.d("Prgress",""+temp);
            }
        });

        //The array is used to display in the front end in list view
        //Listview logic
        String[] wkTemps = new String[] {"Monday        : -1", "Tuesday       : -6", "Wednesday  : -8", "Thursday      : -5", "Friday           : -3"};

        lv = (ListView) findViewById(R.id.listView);
        @SuppressWarnings({"unchecked", "rawtypes"})
		       /*
		       * To use a basic ArrayAdapter, you just need to initialize the adapter and
		       * attach the adapter to the ListView. First, initialize the adapter...:
		       *
		       */
                ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, wkTemps);

        // Assign adapter to ListView
        lv.setAdapter(adapter);

    }//end onCreate method
}