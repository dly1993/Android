//package com.aigestudio.daemon.core;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.os.SystemClock;
//
//import com.stkj.newslocker.R;
//import com.stkj.newslocker.utils.SysUtil;
//
//public class DService extends Service {
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (SysUtil.isAfter18()) {
//            Notification.Builder builder = new Notification.Builder(this);
//            builder.setSmallIcon(R.mipmap.ic_launcher);
//            startForeground(168000, builder.build());
//        } else
//            startForeground(168000, new Notification());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(1000);
//                stopForeground(true);
//                NotificationManager manager =
//                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                manager.cancel(16800);
//                stopSelf();
//            }
//        }).start();
//        return super.onStartCommand(intent, flags, startId);
//    }
//}