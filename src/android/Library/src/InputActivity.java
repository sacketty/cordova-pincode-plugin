package com.brentsys.pincode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.brentsys.FakeR;
import com.brentsys.Utils;
import com.brentsys.cordova.Pincode;

import java.util.HashMap;
import java.util.Map;


public class InputActivity extends Activity implements View.OnClickListener {


    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static char mask = '*';

    private String pinCode;
    private TextView pinCodeView;
    private TextView infoView;

    private FakeR fakeR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initR();
        setContentView(fakeR.getId("layout","activity_input"));
        for(int id:map.keySet()){
            this.findViewById(id).setOnClickListener(this);
        }
        pinCodeView = (TextView) this.findViewById(fakeR.getId("id","pinView"));
        infoView = (TextView) this.findViewById(fakeR.getId("id","tvInfo"));
        String title = getIntent().getStringExtra(Pincode.PIN_TITLE);
        if(title != null) infoView.setText(title);
    }

    private void initR() {
        fakeR = new FakeR(this);
        int[] ids = {fakeR.getId("id","btn0"), fakeR.getId("id","btn1"), fakeR.getId("id","btn2"),
                fakeR.getId("id","btn3"), fakeR.getId("id","btn4"), fakeR.getId("id","btn5"),
                fakeR.getId("id","btn6"), fakeR.getId("id","btn7"), fakeR.getId("id","btn8"),
                fakeR.getId("id","btn9"), fakeR.getId("id","btnDelete"),
                fakeR.getId("id","btnCancel"), fakeR.getId("id","btnOk")
        };
        int idx = 0;
        map.clear();
        for (int id : ids) {
            map.put(id, idx);
            idx++;
        }
    }


    private void updatePinCodeView(){
        if(pinCode != null)
            pinCodeView.setText(Utils.repeat(mask, pinCode.length()));
    }

    @Override
    public void onClick(View view) {
        int val = map.get(view.getId());
        Intent resultData = new Intent();
        if(pinCode==null) pinCode ="";
        switch(val){
            case 10:
                //delete last entry
                if(pinCode.length()>0){
                    pinCode=pinCode.substring(0,pinCode.length()-1);
                }
                break;
            case 11:
                //cancel
                setResult(Activity.RESULT_CANCELED, resultData);
                finish();
                break;
            case 12:
                //submit
                resultData.putExtra("pinCode", pinCode);
                setResult(Activity.RESULT_OK, resultData);
                finish();
                break;
            default:
                pinCode = pinCode + String.valueOf(val);
        }
        updatePinCodeView();
    }
}
