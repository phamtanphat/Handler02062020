package com.example.handler02062020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Thread mThreadA, mThreadB, mThreadC;
    Myhandler mMyHanlder;
    int a, b, c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = b = c = 0;
        mMyHanlder = new Myhandler();
        mThreadA = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                Message message = mMyHanlder.obtainMessage();
                message.arg1 = a;
                message.what = 0;
                mMyHanlder.sendMessage(message);
            }
        });
        mThreadA.start();
        mThreadB = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                Message message = mMyHanlder.obtainMessage();
                message.arg1 = b;
                message.what = 1;
                mMyHanlder.sendMessage(message);
            }
        });
        mThreadB.start();
        mThreadC = new Thread(new Runnable() {
            @Override
            public void run() {
                c = a + b;
                Message message = mMyHanlder.obtainMessage();
                message.arg1 = c;
                message.what = 2;
                mMyHanlder.sendMessage(message);
            }
        });
        mThreadC.start();
    }

    public class Myhandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(MainActivity.this, msg.arg1 + "", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(MainActivity.this, msg.arg1 + "", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(MainActivity.this, msg.arg1 + "", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}