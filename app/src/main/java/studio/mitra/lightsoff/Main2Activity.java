package studio.mitra.lightsoff;

import android.content.DialogInterface;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /**
         * Receiving values from main activity
         */
        Intent in = getIntent();

        //fetching hours
        int h = in.getIntExtra("hours", 0);

        /**
         * by default h is equal to 0 thats why changing it to 1 to get proper result
         */
        if (h == 0)
            h = 1;
        //fetching mins
        int m = in.getIntExtra("minutes", 0);
        //fetching am or pm
        int p = in.getIntExtra("amPm", 1);


        /**
         * decreasing 15 minutes- average time taken for normal person to sleep
         */
        String aP;
        int i;
        for (i = 1; i <= 15; i += 5) {
            if (m == 0) {
                m = 55;
                if (h == 12) {
                    p *= -1;
                    h -= 1;

                }
                if (h == 1)
                    h = 12;
                else
                    h -= 1;
            } else
                m -= 5;
        }


        calculateSleep(h, m, p);


        /**
         * back button
         */
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {

                        //new intent to go back to main menu
                        Intent backI = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(backI);
                        finish(); // so that current activity is not stacked behind the new one.
                    }
                }
        );
        //onBackPressed();

        /**
         * Creating Toast to make user aware of the minimum time required for heathy sleep
         */

        Toast.makeText(Main2Activity.this, "Six hours of minimum sleep is advisable", Toast.LENGTH_SHORT).show();
    }

    /**
     * method to calculate the time
     *
     * @Shubhodeep hrs
     * @Shubhodeep min
     * @Shubhodeep p
     */
    private void calculateSleep(int hrs, int min, int p) {
        /**
         * initialization of variables
         */
        int i, j, sum;
        sum = 0;
        String output, aP;
        output = " ";

        //Output field where the calculated value will be displayed
        TextView t = (TextView) findViewById(R.id.sleepText);


        for (i = 1; i <= 108; i++) {
            sum += 5;

            if (min == 0) {     //if minutes is 0 then decreased minute will be 55 and hour-1
                min = 55;
                if (hrs == 12) {  //checking the conversion of am | pm
                    p *= -1;
                    hrs -= 1;

                }
                if (hrs == 1)
                    hrs = 12;
                else
                    hrs -= 1;
            } else
                min -= 5;

            //storing the output into the string output
            if (sum % 90 == 0 && sum >= 180) {

                if (p == 1)
                    aP = "AM";
                else
                    aP = "PM";
                if (sum == 180) {
                    if (min == 0)
                        output = (hrs + ":00 " + aP) + output;

                    else
                        output = (hrs + ":" + min + " " + aP) + output;
                } else {
                    if (min == 0)
                        output = (hrs + ":00 " + aP + " or ") + output;
                    else
                        output = (hrs + ":" + min + " " + aP + " or ") + output;
                }
            }
        }

        /**
         * Displaying the output in TextView with ID sleepText
         */
        t.setText(output);


    }

    /**
     * SET_ALARM method (Calling default intent for alarm)
     */
    public void setAlarm(View view) {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        /*i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
        i.putExtra(AlarmClock.EXTRA_HOUR, 11);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 20);*/

        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }
    //----------------------------------------------------

    /**
     * Overriding the back button of the phone to exit the app and popping an alert
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
