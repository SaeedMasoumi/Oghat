package io.saeid.oghattest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.joda.time.DateTime;

import io.saeid.oghat.PrayerTime;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateTime dt = new DateTime();
        PrayerTime pt = PrayerTime.getInstance().setDate(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth()).setLatLong(35.696111d, 51.423056d).setTimeZone(4.5f).calculate();
        TextView textView = (TextView) findViewById(R.id.text);
        TextView textView2 = (TextView) findViewById(R.id.text2);
        textView.setText("تهران : " + dt.getYear() + "/" + dt.getMonthOfYear() + "/" + dt.getDayOfMonth() + "\n"
                + "اذان صبح: " + pt.getSobhAzan() + "\n" +
                "طلوع افتاب: " + pt.getToloo() + "\n" +
                "اذان ظهر: " + pt.getZohrAzan() + "\n" +
                "اذان عصر: " + pt.getAsrAzan() + "\n" +
                "غروب: " + pt.getGhoroob() + "\n" +
                "اذان مغرب: " + pt.getMaghrebAzan() + "\n" +
                "اذان عشا: " + pt.getEshaAzan() + "\n" +
                "نیمه شب شرعی: " + pt.getMidnight());
        pt.setLatLong(38.066667d, 46.300000d).calculate();
        textView2.setText("تبریز : " + dt.getYear() + "/" + dt.getMonthOfYear() + "/" + dt.getDayOfMonth() + "\n"
                + "اذان صبح: " + pt.getSobhAzan() + "\n" +
                "طلوع افتاب: " + pt.getToloo() + "\n" +
                "اذان ظهر: " + pt.getZohrAzan() + "\n" +
                "اذان عصر: " + pt.getAsrAzan() + "\n" +
                "غروب: " + pt.getGhoroob() + "\n" +
                "اذان مغرب: " + pt.getMaghrebAzan() + "\n" +
                "اذان عشا: " + pt.getEshaAzan() + "\n" +
                "نیمه شب شرعی: " + pt.getMidnight());
    }


}
