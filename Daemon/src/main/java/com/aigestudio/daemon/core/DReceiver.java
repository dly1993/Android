package com.aigestudio.daemon.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.stkj.newslocker.cores.SPManager;
import com.stkj.newslocker.services.LockService;

public class DReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (SPManager.getInstance(context).isLockEnable())
            context.startService(new Intent(context, LockService.class));
    }
}