package com.friendlyarm.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import com.friendlyarm.MainActivity.R;

/**
 * Created by CAI on 6/3/2016.
 */
public class MainActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  /* 设置显示main.xml布局 */


        Button button1 = (Button)this.findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //新建一个Intent
                Intent intent = new Intent();
                //制定intent要启动的类
                intent.setClass(com.friendlyarm.MainActivity.MainActivity.this, com.friendlyarm.LEDDemo.LEDTestingActivity.class);
                //启动一个新的Activity
                startActivity(intent);
                //关闭当前的
                com.friendlyarm.MainActivity.MainActivity.this.finish();

            }
        });
        Button button2 = (Button)this.findViewById(R.id.button2);
        button2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //新建一个Intent
                Intent intent = new Intent();
                //制定intent要启动的类
                intent.setClass(com.friendlyarm.MainActivity.MainActivity.this, com.friendlyarm.ADCTestingActivity.ADCTestingActivity.class);
                //启动一个新的Activity
                startActivity(intent);
                //关闭当前的
                com.friendlyarm.MainActivity.MainActivity.this.finish();

            }
        });
    }
}
