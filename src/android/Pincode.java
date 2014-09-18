package com.brentsys.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;

import android.app.Activity;
import android.content.Intent;

import com.brentsys.pincode.InputActivity;

public class Pincode extends CordovaPlugin {
	public static final String ACTION_ENTER_PIN = "enterPin";
	public static final String ACTION_CLOSE = "close";
	public static final String PIN_TITLE="title";
	public static final String ACTION_SET_TEXT = "setText";
	private static final int PIN_CODE_REQUEST=200;
	private static final String PINCODE="pinCode";
	private static final String CANCELLED="cancelled";

    private static final String LOG_TAG = "Pincode";

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	this.callbackContext = callbackContext;
    	if (ACTION_ENTER_PIN.equals(action)) {
            this.enterPin(args, callbackContext);
        } else {
        	callbackContext.error("Invalid action");
            return false;
        }
        return true;
    }

    private void enterPin(JSONArray args, CallbackContext callbackContext) throws JSONException {
    	try {
    		JSONObject arg_object = args.getJSONObject(0);
    		Intent pincodeIntent = new Intent(this.cordova.getActivity(), InputActivity.class)
    			.putExtra("title", arg_object.getString("title"));
    		this.cordova.startActivityForResult((CordovaPlugin) this, pincodeIntent, PIN_CODE_REQUEST);
//    		InputActivity act = (InputActivity) this.cordova.getActivity();
//    		act.setPinCodeBridge(this);
    	}  catch (Exception e) {
            callbackContext.error(e.getMessage());
      
        }
    }

    public void setValue(String pincode){
    	JSONObject obj = new JSONObject();
        try {
            obj.put(PINCODE, pincode);
            obj.put(CANCELLED, false);
        } catch (JSONException e) {
            Log.d(LOG_TAG, "This should never happen");
        }
//        this.callbackContext.success(obj);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PIN_CODE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put(PINCODE, intent.getStringExtra(PINCODE));
                    obj.put(CANCELLED, false);
                } catch (JSONException e) {
                    Log.d(LOG_TAG, "This should never happen");
                }
                //this.success(new PluginResult(PluginResult.Status.OK, obj), this.callback);
                this.callbackContext.success(obj);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put(PINCODE, "");
                    obj.put(CANCELLED, true);
                } catch (JSONException e) {
                    Log.d(LOG_TAG, "This should never happen");
                }
                //this.success(new PluginResult(PluginResult.Status.OK, obj), this.callback);
                this.callbackContext.success(obj);
            } else {
                //this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
                this.callbackContext.error("Unexpected error");
            }
        }
    }

}