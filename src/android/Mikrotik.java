package com.javray.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

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
        else if (action.equals("disconnect")) {
          if (this.aConn.isConnected()) {
            try {
              this.aConn.disconnect();
            }
            catch (IOException ex) {
              callbackContext.error("Can't Disconnect: " + ex.toString());
              return false;
            }
            callbackContext.success("Done");
          }
          else {
            callbackContext.error("Not Connected");
          }
        }
        else if (action.equals("login")) {
          if (this.aConn.isConnected()) {
            String name = args.getString(0);
            char[] passwod = args.getString(1).toCharArray();
            String res = this.aConn.login(name, passwod);
            callbackContext.success(res);
          }
          else {
            callbackContext.error("Not Connected");
          }
        }
        else if (action.equals("command")) {

          if (this.aConn.isConnected()) {

            String command = args.getString(0);
            String res = this.aConn.sendCommand(command);
            String result = "";

            if (res.equals("Sent successfully")) {
              res = "";
              try {
                while (!(result = this.aConn.getData()).equals("")) {
                  res += result;
                }
              }
              catch (InterruptedException e) {
                callbackContext.error("No Data: " + e.toString());
                return false;
              }
              callbackContext.success(res);
            }
            else {
              callbackContext.error(res);
            }
          }
          else {
            callbackContext.error("Not Connected");
          }
        }

        return false;
    }
}

