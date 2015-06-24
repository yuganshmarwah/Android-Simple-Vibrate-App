package wh.vibrate;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    final int[] i = {0};
    Thread vibratethread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vibratethread = new Vibratethread();
        final Button toggle = (Button) findViewById(R.id.Toggle);
        final TextView display = (TextView) findViewById(R.id.display);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i[0] == 0) {
                    i[0] = 1 ;
                    if (vibratethread.isAlive()){
                        vibratethread.interrupt();
                        vibratethread = new Vibratethread();
                    }
                    else{
                        vibratethread.start();
                    }
                    display.setText("ON");
                    display.setTextColor(Color.GREEN);
                    toggle.setText("STOP");
                } else {
                    vibratethread.interrupt();
                    vibratethread = new Vibratethread();
                    i[0] = 0;
                    display.setTextColor(Color.RED);
                    display.setText("OFF");
                    toggle.setText("START");
                }
            }

        });






    }



    class Vibratethread extends Thread{
        final Vibrator vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        public Vibratethread(){
            super();
        }
        public void run(){
            while (i[0] == 1)
            {
                vibrate.vibrate(300);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
}
}