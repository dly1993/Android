//package com.aigestudio.daemon.utils;
//
//import android.app.Activity;
//import android.app.ActivityManager;
//import android.app.PendingIntent;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Point;
//import android.net.ConnectivityManager;
//import android.os.Build;
//import android.os.PowerManager;
//import android.renderscript.Allocation;
//import android.renderscript.Element;
//import android.renderscript.RenderScript;
//import android.renderscript.ScriptIntrinsicBlur;
//import android.util.Log;
//import android.view.Display;
//import android.view.WindowManager;
//
//import com.stkj.newslocker.R;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//
//import static android.graphics.PixelFormat.TRANSLUCENT;
//import static android.view.Gravity.TOP;
//import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
//import static android.view.WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
//import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//import static android.view.WindowManager.LayoutParams.TYPE_PHONE;
//import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//import static android.view.WindowManager.LayoutParams.TYPE_TOAST;
//
//public final class SysUtil {
//    public static final SimpleDateFormat SDF_HM = new SimpleDateFormat("HH:mm", Locale.getDefault());
//    private static int[] screen;
//
//    public static boolean isAfter19() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//    }
//
//    public static boolean isAfter18() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
//    }
//
//    public static boolean isAfter17() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
//    }
//
//    public static boolean isAfter16() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
//    }
//
//    public static boolean isAfter14() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
//    }
//
//    public static boolean isAfter13() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2;
//    }
//
//    public static boolean isAfter9() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
//    }
//
//    public static boolean isAfter8() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
//    }
//
//    public static boolean isAfter5() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
//    }
//
//    public static String getPkgCodePath(Context context) {
//        String path = "";
//        if (isAfter8()) {
//            path = context.getPackageCodePath();
//        } else {
//            try {
//                ApplicationInfo ai =
//                        context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
//                path = ai.sourceDir;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return path;
//    }
//
//    public static boolean isServiceAlive(Context context, Class clz) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo info : am.getRunningServices(Integer.MAX_VALUE))
//            if (info.service.getClassName().equals(clz.getName()))
//                return true;
//        return false;
//    }
//
//    public static WindowManager.LayoutParams buildWindowParams() {
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//        params.width = MATCH_PARENT;
//        params.height = MATCH_PARENT;
//        params.gravity = TOP;
//        params.flags = FLAG_NOT_FOCUSABLE | FLAG_NOT_TOUCH_MODAL | FLAG_LAYOUT_IN_SCREEN |
//                FLAG_DISMISS_KEYGUARD;
//        //
//        params.format = TRANSLUCENT;
//
//        Log.e("buildWindowParams", Build.MODEL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            params.type = TYPE_TOAST;
//        } else {
//            params.type = TYPE_SYSTEM_ALERT;
//        }
//
//        if (equals("CM810"))
//            params.type = TYPE_TOAST;
//        if (equalsForManufacturer("Meizu")) {
//            params.type = TYPE_TOAST;
//        }
//        if (equalsForManufacturer("ONEPLUS")) {
//            params.type = TYPE_PHONE;
//        }
//        return params;
//    }
//
//    private static boolean equals(String model) {
//
//        return Build.MODEL.trim().equalsIgnoreCase(model);
//    }
//
//    private static boolean equalsForManufacturer(String model) {
//
//        return Build.MANUFACTURER.trim().equalsIgnoreCase(model);
//    }
//
//    public static Bitmap blurBitmap(Context context, Bitmap bitmap, float radius) {
//        if (isAfter17()) {
//            Bitmap result = Bitmap.createBitmap(
//                    bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//            RenderScript rs = RenderScript.create(context);
//            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//            Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
//            Allocation allOut = Allocation.createFromBitmap(rs, result);
//            blurScript.setRadius(radius);
//            blurScript.setInput(allIn);
//            blurScript.forEach(allOut);
//            allOut.copyTo(result);
//            rs.destroy();
//            return result;
//        }
//        return bitmap;
//    }
//
//    public static Bitmap getBitmapFromRes(Context context, int resID) {
//        Bitmap bitmap;
//        try {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeResource(context.getResources(), resID, options);
//            options.inSampleSize = 1;
//            int[] sizes = getScreenSize(context);
//            int width = options.outWidth;
//            int height = options.outHeight;
//            if (width > sizes[0] || height > sizes[1]) {
//                int ratioWidth = Math.round((float) width / (float) sizes[0]);
//                int ratioHeight = Math.round((float) height / (float) sizes[1]);
//                options.inSampleSize = ratioWidth < ratioHeight ? ratioHeight : ratioWidth;
//            }
//            options.inJustDecodeBounds = false;
//            bitmap = BitmapFactory.decodeResource(context.getResources(), resID, options);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
//            baos.close();
//        } catch (IOException e) {
//            bitmap = BitmapFactory.decodeResource(context.getResources(), resID);
//        }
//        return bitmap;
//    }
//
//    public static int[] getScreenSize(Context context) {
//        if (null != screen)
//            return screen;
//        screen = new int[2];
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        if (isAfter13()) {
//            Point size = new Point();
//            display.getSize(size);
//            screen[0] = size.x;
//            screen[1] = size.y;
//        } else {
//            screen[0] = display.getWidth();
//            screen[1] = display.getHeight();
//        }
//        return screen;
//    }
//
//    public static boolean isNetworkValid(Context context) {
//        ConnectivityManager conn =
//                (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
//        boolean wifi = conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
//        boolean internet =
//                conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
//        return wifi | internet;
//
//    }
//
//    public static int obtainWeekResID(int week) {
//        switch (week) {
//            case 1:
//                return R.string.Sunday;
//            case 2:
//                return R.string.Monday;
//            case 3:
//                return R.string.Tuesday;
//            case 4:
//                return R.string.Wednesday;
//            case 5:
//                return R.string.Thursday;
//            case 6:
//                return R.string.Friday;
//            case 7:
//                return R.string.Saturday;
//        }
//        return week;
//    }
//
//    public static void lightScreen(Context context) {
//        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        if (null == pm) return;
//        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
//                PowerManager.SCREEN_DIM_WAKE_LOCK, "lightenScreen");
//        if (null == wl || wl.isHeld()) return;
//        wl.acquire();
//    }
//
//    public static void startPendingIntent(Context context, String packageName, PendingIntent contentIntent) {
//        PackageInfo pi;
//        try {
//            pi = context.getPackageManager().getPackageInfo(packageName, 0);
//            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
//            resolveIntent.setPackage(pi.packageName);
//            PackageManager pManager = context.getPackageManager();
//            List apps = pManager.queryIntentActivities(resolveIntent, 0);
//            Iterator iterator = apps.iterator();
//            if (iterator.hasNext()) {
//                ResolveInfo ri = (ResolveInfo) iterator.next();
//                if (ri != null) {
//                    packageName = ri.activityInfo.packageName;
//                    String className = ri.activityInfo.name;
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ComponentName cn = new ComponentName(packageName, className);
//                    intent.setComponent(cn);
//                    try {
//                        if (contentIntent != null)
//                            contentIntent.send(context, 1, intent);
//                    } catch (PendingIntent.CanceledException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}