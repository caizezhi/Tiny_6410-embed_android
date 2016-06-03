package com.friendlyarm.LEDDemo;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.friendlyarm.MainActivity.MainActivity;
import com.friendlyarm.MainActivity.R;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;


import com.friendlyarm.AndroidSDK.HardwareControler;

public class LEDTestingActivity extends Activity implements OnClickListener {
	
	private Button btnLED1On; 
	private Button btnLED1Off; 
	private Button btnLED2On; 
	private Button btnLED2Off; 
	private Button btnLED3On; 
	private Button btnLED3Off; 
	private Button btnLED4On; 
	private Button btnLED4Off; 

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leddemo);
        
    	btnLED1On = (Button)findViewById(R.id.btnLED1On);
    	btnLED1Off = (Button)findViewById(R.id.btnLED1Off);
    	btnLED2On = (Button)findViewById(R.id.btnLED2On);
    	btnLED2Off = (Button)findViewById(R.id.btnLED2Off);
    	btnLED3On = (Button)findViewById(R.id.btnLED3On);
    	btnLED3Off = (Button)findViewById(R.id.btnLED3Off);
    	btnLED4On = (Button)findViewById(R.id.btnLED4On);
    	btnLED4Off = (Button)findViewById(R.id.btnLED4Off);
    	btnLED1On.setOnClickListener(this);
    	btnLED1Off.setOnClickListener(this);
    	btnLED2On.setOnClickListener(this);
    	btnLED2Off.setOnClickListener(this);
    	btnLED3On.setOnClickListener(this);
    	btnLED3Off.setOnClickListener(this);
    	btnLED4On.setOnClickListener(this);
    	btnLED4Off.setOnClickListener(this);
    }
    
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLED1On:
			HardwareControler.setLedState(0,1);
			break;
		case R.id.btnLED1Off:
			HardwareControler.setLedState(0,0);
			break;
		case R.id.btnLED2On:
			HardwareControler.setLedState(1,1);
			break;
		case R.id.btnLED2Off:
			HardwareControler.setLedState(1,0);
			break;
		case R.id.btnLED3On:
			HardwareControler.setLedState(2,1);
			break;
		case R.id.btnLED3Off:
			HardwareControler.setLedState(2,0);
			break;
		case R.id.btnLED4On:
			HardwareControler.setLedState(3,1);
			break;
		case R.id.btnLED4Off:
			HardwareControler.setLedState(3,0);
			break;
		default:
			break;
		}
	}
    public boolean onKeyDown(int keyCode,KeyEvent
            event){
        if (keyCode==KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0)
        {
            Intent myIntent;
            myIntent = new Intent(LEDTestingActivity.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return false;


    }
}