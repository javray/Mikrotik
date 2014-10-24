package com.javray.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import libAPI.*;

public class Mikrotik extends CordovaPlugin {

    private ApiConn aConn = null;

    public Mikrotik() {
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("connect")) {
            String ipAddress = args.getString(0);
            int ipPort = args.getInt(1);

            this.aConn = new ApiConn(ipAddress, ipPort);
            this.aConn.run();

            if (this.aConn.isConnected()) {
              callbackContext.success("Connected");
            }
            else {
              callbackContext.error("Not Connected");
            }
        }
        else if (action.equals("login")) {
            String name = args.getString(0);
            char[] passwod = args.getString(1).toCharArray();
            String res = this.aConn.login(name, passwod);
            callbackContext.success(res);
        }
        return false;
    }
}

