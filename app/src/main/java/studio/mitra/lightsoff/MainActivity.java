package studio.mitra.lightsoff;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * Global Variables
     */

    //to count the hours
    public int hours = 0;

    //to count minutes
    public  int mins=0;

    //to keep track of am and pm
    public  int period=1;

    //-----------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        //-------------------------------------------------------------------------
        /**
         *
         *Creating reference for Buttons
         */

        //Hour Button Decrement
        Button hourDec=(Button)findViewById(R.id.hourButtonDec);

        //Hour Button Increment

        Button hourInc=(Button)findViewById(R.id.hourButtonInc);

        //Minutes Button Increment

        Button minDec=(Button)findViewById(R.id.minButtonDec);

        //Minute Button Increment

        Button minInc = (Button)findViewById(R.id.minButtonInc);

        //AM|PM button
        final Button amPm=(Button) findViewById(R.id.amPmButton);

        //------------------------------------------------------
        /**
         * Hours Button method
         */

        hourDec.setOnClickListener(
                new Button.OnClickListener(){
                    //decrement hours count
                    public  void onClick(View view)
                    {
                        if (hours == 1 || hours == 0)
                        {
                            hours=12;
                        }
                        else
                        {
                            --hours;
                        }
                        displayHours(hours);
                    }

                }
        );

        hourInc.setOnClickListener(
                new Button.OnClickListener(){
                    //increment hours count
                    public void onClick(View view)
                    {
                        if (hours == 12 || hours == 0)
                        {
                            hours=1;
                        }
                        else
                        {
                            ++hours;
                        }
                        displayHours(hours);
                    }
                }
        );

        /*
        Minutes Button methods
         */
        minDec.setOnClickListener(
                new Button.OnClickListener(){
                    //decrement mins count
                    public  void onClick(View view)
                    {
                        if(mins==0)
                        {
                            mins=55;
                        }
                        else
                        {
                            mins-=5;
                        }
                        displayMins(mins);
                    }
                }
        );


        minInc.setOnClickListener(
                new Button.OnClickListener(){
                    //increment mins count
                    public void onClick(View view)
                    {
                        if(mins==55)
                        {
                            mins=0;
                        }
                        else
                        {
                            mins+=5;
                        }
                        displayMins(mins);
                    }
                }
        );
        /**
         * Methods for the AM/PM
         */
        amPm.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View view)
                    {
                        period*=-1;
                        Button button=(Button) findViewById(R.id.amPmButton);
                        if(period==1)
                            button.setText("AM");
                        else
                            button.setText("PM");
                    }


                }
        );

        /**
         * Calculate Button Method
         */
        Button calc=(Button)findViewById(R.id.calculateButton);

        calc.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);

                        /**
                         * Sending the values of hour, minutes and am/pm to main2activity
                         */
                        //hour value
                        i.putExtra("hours",hours);

                        //minutes value
                        i.putExtra("minutes",mins);
                        //am/pm value
                        i.putExtra("amPm",period);
                        startActivity(i);
                        finish(); //so that current activity is not stacked behind the new one.
                    }
                }
        );

        /**
         * Sleep now Button
         */
        Button sleep = (Button) findViewById(R.id.calculateButton2);
        assert sleep != null;
        sleep.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        Intent in = new Intent(MainActivity.this, Main3Activity.class);
                        startActivity(in);
                        finish();

                    }
                }
        );


    }
    //--------------------------------------------------------------------------------




    /**
     * functions of the hour clock
     */

    //display hour in the hourTextView

    private  void displayHours(int x)
    {

        TextView hourText=(TextView) findViewById(R.id.hourTextView);
        if(x>9)
            hourText.setText(""+x);
        else
            hourText.setText("0"+x);
    }






    //-------------------------------------------------------------------

    /**
     * Methods for minutes clock
     */

    //display minutes in minTextView
    private  void displayMins(int x)
    {

        TextView minText=(TextView) findViewById(R.id.minTextView);
        if(x>9)
            minText.setText(""+x);
        else
            minText.setText("0"+x);
    }





    //----------------------------------------------------

    /**
     * Overiding the back button of the phone to exit the app and popping an alert
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                }).setNegativeButton("No", null).show();

    }

}



