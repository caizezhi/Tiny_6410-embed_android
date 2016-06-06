package com.friendlyarm.Graph;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import com.friendlyarm.AndroidSDK.HardwareControler;
import com.friendlyarm.LEDDemo.LEDTestingActivity;
import com.friendlyarm.MainActivity.MainActivity;
import com.friendlyarm.MainActivity.R;
/**
 * Created by CAI on 6/6/2016.
 */

public class Graph extends Activity {
    private SurfaceHolder holder;
    private SurfaceView surface;
    private Paint paint;
    private final int HEIGHT = 320;
    // 要绘制的曲线的水平宽度
    private final int WIDTH = 500;
    // 离屏幕左边界的起始距离
    private final int X_OFFSET = 5;
    // 初始化X坐标
    private int cx = X_OFFSET;
    // 实际的Y轴的位置
    private int centerY = HEIGHT / 2;
    private Timer timer = new Timer();
    private TimerTask task = null;
    public static final int Update_UI = 0;
    private TextView mTextView_ADC = null;
    private int cy = 0;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_wave);
        // 获得SurfaceView对象
        surface = (SurfaceView) findViewById(R.id.activity_show_wave_sv);
        // 初始化SurfaceHolder对象
        holder = surface.getHolder();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);

    }
    public void click(final View v) {
        drawBackGround(holder);
        cx = X_OFFSET;
        if (task != null) {
            task.cancel();
        }
        task = new TimerTask() {

            @Override
            public void run() {
                cy = HardwareControler.readADC()/5;
                Canvas canvas = holder.lockCanvas(new Rect(cx, cy - 2, cx + 2,
                        cy + 2)
                );
                // 根据Ｘ，Ｙ坐标画点
                canvas.drawPoint(cx, cy, paint);
                cx++;
                // 超过指定宽度，线程取消，停止画曲线
                if (cx > WIDTH) {
                    task.cancel();
                    task = null;
                }
                // 提交修改
                holder.unlockCanvasAndPost(canvas);
            }
        };
        timer.schedule(task, 0, 30);
        holder.addCallback(new Callback() {

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                drawBackGround(holder);
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                timer.cancel();
            }
        });
    }

    private void drawBackGround(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        // 绘制白色背景
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(2);
        // 绘制坐标轴
        canvas.drawLine(X_OFFSET, centerY, WIDTH, centerY, p);
        canvas.drawLine(X_OFFSET, 40, X_OFFSET, HEIGHT, p);
        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0, 0, 0, 0));
        holder.unlockCanvasAndPost(canvas);
    }

    public boolean onKeyDown(int keyCode,KeyEvent
            event){
        if (keyCode==KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0)
        {
            Intent myIntent;
            myIntent = new Intent(Graph.this, com.friendlyarm.MainActivity.MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return false;


    }
}