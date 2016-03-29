package studio.mitra.lightsoff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
    }
    //--------------------------------------------------------------------------------

    /**
     * Global Variables
     */

    //to count the hours
    public static int hours=1;

    //to count minutes
    public static int mins=0;

    //to keep track of am and pm
    public static int period=1;

    //-----------------------------------------------------------------------------


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

    //decrement hours count
    public  void hourDec(View view)
    {
        if(hours==1)
        {
            hours=12;
        }
        else
        {
            --hours;
        }
        displayHours(hours);
    }

    //increment hours count
    public void hourInc(View view)
    {
        if(hours==12)
        {
            hours=1;
        }
        else
        {
            ++hours;
        }
        displayHours(hours);
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

    //decrement mins count
    public  void minDec(View view)
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

    //increment mins count
    public void minInc(View view)
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

    //----------------------------------------------------

    /**
     * Methods for the AM/PM
     */
    public void amPmButton(View view)
    {
        period*=-1;
        Button button=(Button) findViewById(R.id.amPmButton);
        if(period==1)
            button.setText("AM");
        else
            button.setText("PM");
    }

}
