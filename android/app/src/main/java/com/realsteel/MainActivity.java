package com.realsteel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        try {
            System.loadLibrary("realsteel"); // expect librealsteel.so in jniLibs/<abi>/
        } catch (UnsatisfiedLinkError e) {
            // Native library not found — app will still start; log via Logcat if needed
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("RealSteel Recomp (Android) - placeholder launcher");
        setContentView(tv);

        // Later: call native init entrypoints via JNI as needed
    }
}
