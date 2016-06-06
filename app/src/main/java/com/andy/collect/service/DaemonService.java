package com.andy.collect.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import com.andy.collect.Properties;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * desc: 后台服务Service
 * @author andy he
 * @time 2016/6/2 14:31
 */
public class DaemonService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Timer().schedule(new WmTask(), 0L, Properties.cyclesTime);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY; //START_STICKY service被kill掉后自动重写创建
        //return super.onStartCommand(intent, START_STICKY, startId);
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public DaemonService() {
        super();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    /**
     * desc: 此执行定时任务队列
     * @author andy he
     * @time 2016/3/21 14:04
     */
    class WmTask extends TimerTask{
        @Override
        public void run() {
            //fixme 在此执行定时任务队列;后期根据实际业务在此加入业务逻辑
        }

        @Override
        public long scheduledExecutionTime() {
            return super.scheduledExecutionTime();
        }

        @Override
        public boolean cancel() {
            return super.cancel();
        }

        protected WmTask() {
            super();
        }
    }
}
