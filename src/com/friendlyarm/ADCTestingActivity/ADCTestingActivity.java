package com.friendlyarm.ADCTestingActivity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.friendlyarm.AndroidSDK.HardwareControler;
import com.friendlyarm.ADCTestingActivity.R;

/**
 * Created by CAI on 16/6/3.
 */
public class ADCTestingActivity extends Activity{
    private static final String TAG = "ADCTestingActivity";

    public static final int Update_UI=0;

    private Timer mTimer = null;
    private TimerTask mTimerTask = null;

    private TextView mTextView_ADC=null;
    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adclayout);
        initUI();
        initData();
    }

    private void initUI(){
        mTextView_ADC = (TextView)findViewById(R.id.TextView_adcresult);
    }

    private void initData(){
        mTimer = new Timer();
        mTimerTask = new TimerTask(){
            @Override
            public void run(){
                result = HardwareControler.readADC();
                if(-1==result){
                    Log.e(TAG,"Read ADC ERROR!");
                }else{
                    Log.i(TAG,"readADC result:"+result);
                    mHandler.sendMessage(mHandler.obtainMessage(Update_UI));
                }
            }
        };
        mTimer.schedule(mTimerTask,0,500);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            int type = msg.what;
            switch(type){
                case Update_UI:
                    mTextView_ADC.setText(String.valueOf(result));
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onDestroy(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
        super.onDestroy();
    }
}
