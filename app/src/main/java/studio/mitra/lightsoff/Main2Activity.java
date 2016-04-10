package studio.mitra.lightsoff;

import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /**
         * Receiving values from main activity
         */
        Intent in=getIntent();

        //fetching hours
        int h=in.getIntExtra("hours",0);
        //fetching mins
        int m=in.getIntExtra("minutes",0);
        //fetching am or pm
        int p=in.getIntExtra("amPm",1);


        /**
         * decreasing 15 minutes- average time taken for normal person to sleep
         */
        String aP;
        int i;
        for(i=1;i<=15;i+=5)
        {
            if(m==0) {
                m = 55;
                if (h == 12) {
                    p *= -1;

                }
                if(h==1)
                    h=12;
                else
                    h -= 1;
            }
            else
                m-=5;
        }


        calculateSleep(h, m, p);


        /**
         * back button
         */
        Button back= (Button)findViewById(R.id.backButton);
        back.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View view){

                        //new intent to go back to main menu
                        Intent backI = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(backI);
                        finish(); // so that current activity is not stacked behind the new one.
                    }
                }
        );



    }

    /**
     * method to calculate the time
     * @param hrs
     * @param min
     * @param p
     */
    private void calculateSleep(int hrs,int min,int p)
    {
        /**
         * initialization of variables
         */
            int i,j,sum;
            sum=0;
            String output,aP;
            output=" ";
        TextView t=(TextView)findViewById(R.id.sleepText);



            for(i=1;i<=108;i++) {
                sum += 5;

                if (min == 0) {     //if minutes is 0 then decreased minute will be 55 and hour-1
                    min = 55;
                    if (hrs == 12) {  //checking the coversion of am | pm
                        p *= -1;

                    }
                    if(hrs==1)
                        hrs=12;
                    else
                        hrs -= 1;
                }
                else
                    min -= 5;

                //storing the output into the string output
                if (sum % 90 == 0 && sum >= 270) {

                    if (p == 1)
                        aP = "AM";
                    else
                        aP = "PM";
                    if (sum == 540) {
                        if(min==0)
                            output = output + (hrs + ":00 " + aP);

                        else
                            output = output + (hrs + ":" + min + " " + aP);
                    } else {
                        if(min==0)
                            output = output + (hrs + ":00 " + aP+ " or ");
                        else
                            output = output + (hrs + ":" + min + " " + aP + " or ");
                    }
                }
            }

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

}
