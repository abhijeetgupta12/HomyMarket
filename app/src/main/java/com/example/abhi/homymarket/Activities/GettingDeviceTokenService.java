package com.example.abhi.homymarket.Activities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

public class GettingDeviceTokenService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        Log.d("DEVICE_TOKEN:",s);

    }
}
