package com.study.study1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Intent intent;
    private Button tongbu;
    private TextView tv;
    private ServiceConnection conn;
    private IService mIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        tongbu = (Button) findViewById(R.id.tongbu);

        intent = new Intent(MainActivity.this, MyService.class);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        tongbu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:

                conn = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.e("qqq", "onServiceConnected。。。。。。。。");

                        mIService = (IService) service;
                        Log.e("qqq", "onServiceConnected。。。。。。。。"+ mIService.getData());
                        mIService.setData("shezhi");
                        Log.e("qqq", "onServiceConnected。。。。。。。。"+ mIService.getData());

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(conn);

                break;

            case R.id.tongbu:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(conn);

    }
}
