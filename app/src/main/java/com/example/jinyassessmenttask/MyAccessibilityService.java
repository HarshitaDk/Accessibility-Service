package com.example.jinyassessmenttask;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.LogRecord;

/**
 * Created by Driverskart on 28-02-2017.
 */
public class MyAccessibilityService extends AccessibilityService {



    public static final String TAG = "volumeMaster";
    ArrayList<AccessibilityNodeInfo> textViewNodes;
    AccessibilityNodeInfo rootNode;
    String tv1Text;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event)
    {

        Log.v(TAG, "***** onAccessibilityEvent");
        Toast.makeText(getApplicationContext(), "Got event from: " + getPackageName(), Toast.LENGTH_LONG).show();

    }

    private void findChildViews(AccessibilityNodeInfo parentView) {
        int childCount = parentView.getChildCount();
        if (parentView == null || parentView.getClassName() == null ) {
            Log.v(TAG, "Null parent view");
            return;
        }


        if (childCount == 0 && (parentView.getClassName().toString().contentEquals("android.widget.TextView"))) {
            textViewNodes.add(parentView);
        } else {
            for (int i = 0; i < childCount; i++) {
                findChildViews(parentView.getChild(i));
            }
        }
    }


    @Override
    public void onInterrupt()
    {

        Log.v(TAG, "***** onInterrupt");
    }

    @Override
    public void onServiceConnected()
    {

        Log.v(TAG, "***** onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityEvent.TYPES_ALL_MASK;
        setServiceInfo(info);


    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}