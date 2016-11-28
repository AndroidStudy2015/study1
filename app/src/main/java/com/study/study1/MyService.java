package com.study.study1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    String data = "1";

    private class MyBinder extends Binder implements IService {


        @Override
        public String getData() {
            return getDataService();
        }

        @Override
        public void setData(String str) {

        }

        public void say() {
            sayService();
        }
    }

    public void sayService() {

    }

    private String getDataService() {
        return data;
    }


    public MyService() {
    }

    public void setDataService(String str) {
        data = str;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("qqq", "onBind。。。。。。。。" + data);

        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("qqq", "onCreate。。。。。。。。" + data);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("qqq", "onStartCommand。。。。。。。。" + data);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("qqq", "onDestroy。。。。。。。。" + data);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("qqq", "onUnbind。。。。。。。。" + data);
        return super.onUnbind(intent);
    }
}
