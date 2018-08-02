package autoride.me.testservice01;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    boolean creating;
    Intent intent1;
    PendingIntent pintent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopService();
    }

    public void stopService(View view) {
    /*    Intent intent2 = new Intent(MainActivity.this, autoride.me.testservice01.MyService.class);
        PendingIntent pintent2 = PendingIntent.getService(MainActivity.this, 0, intent2, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, 50 * 1000, pintent2);
        if (intent2.filterEquals(intent1)) {
            alarm.cancel(pintent1);
            stopService(new Intent(this,MyService.class));
        }*/

    if(isMyServiceRunning(MyService.class.getName())){
        stopService();
    }

    }

    public void startService(View view) {

        intent1 = new Intent(MainActivity.this, autoride.me.testservice01.MyService.class);
        pintent1 = PendingIntent.getService(MainActivity.this, 0, intent1, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, 50 * 1000, pintent1);
    }



  void startService(){
      intent1 = new Intent(MainActivity.this, autoride.me.testservice01.MyService.class);
      pintent1 = PendingIntent.getService(MainActivity.this, 0, intent1, 0);
      AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
      alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, 5 * 1000, pintent1);
  }

  void stopService(){
      Intent intent2 = new Intent(MainActivity.this, autoride.me.testservice01.MyService.class);
      PendingIntent pintent2 = PendingIntent.getService(MainActivity.this, 0, intent2, 0);
      AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
      alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, 50* 1000, pintent2);
      if (intent2.filterEquals(intent1)) {
          stopService(intent2);
          alarm.cancel(pintent1);
      }
  }


    private boolean isMyServiceRunning(String serviceClassname) {

        //getting all the services
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {

            Log.i("debug", service.service.getClassName());

            if (serviceClassname.equals(service.service.getClassName())) {

                return true;
            }
        }
        return false;
    }
}
