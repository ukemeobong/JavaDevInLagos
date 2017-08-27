package com.uetechnologies.ukemeobong.javadevinlagos;

/**
 * Created by Ukemeobong on 8/20/2017.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.widget.Toast;

//This method checkes for the internet connection availability in the phone
public class ConnectionTest {

    static public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    //This method display the Toast
    static public void showToastDuration(Context context,String message, int toastTimeInMS, int gravity) {
        final Toast mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastTimeInMS, 1000 ) {
            public void onTick(long millisUntilFinished) {
                mToast.show();
            }
            public void onFinish() {
                mToast.cancel();
            }
        };
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
        toastCountDown.start();

    }
}
