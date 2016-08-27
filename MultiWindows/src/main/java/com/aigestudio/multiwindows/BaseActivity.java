package com.aigestudio.multiwindows;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
    protected TextView mTVContent;
    protected Button mBtnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base);

        Log.i("AigeStudio", getClass().getSimpleName() + ":onCreate");

        LinearLayout llRoot = (LinearLayout) findViewById(R.id.base_ll);
        llRoot.setBackgroundColor(initColor());
        llRoot.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
                    ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                    mTVContent.setText(item.getText());
                }
                return true;
            }
        });
        mTVContent = (TextView) findViewById(R.id.base_tv);

        mBtnClick = (Button) findViewById(R.id.base_btn);
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeBasic();
                options.setLaunchBounds(new Rect());

                Intent intent = new Intent(BaseActivity.this, SplitActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);

                ActivityCompat.startActivity(BaseActivity.this, intent, null);
            }
        });
        mBtnClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData data = ClipData.newPlainText(view.getClass().getName(), "AigeStudio");
                View.DragShadowBuilder builder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(data, builder, view, View.DRAG_FLAG_GLOBAL);
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("AigeStudio", getClass().getSimpleName() + ":onPostCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onRestart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onPostResume");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("AigeStudio", getClass().getSimpleName() + ":onDestroy");
    }

    protected abstract int initColor();

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
//        Log.i("AigeStudio", getClass().getSimpleName() + ":" + isInMultiWindowMode);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
//        Log.i("AigeStudio", getClass().getSimpleName() + ":" + isInPictureInPictureMode);
    }
}