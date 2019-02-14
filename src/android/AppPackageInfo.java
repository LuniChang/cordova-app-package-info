package com.lunichang.app.packageinfo;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class AppPackageInfo extends CordovaPlugin {

    private Context context;

    public final static String TAG="AppPackageInfo";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        context = this.cordova.getActivity().getApplicationContext();

        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
  
        if (action.equals("getAllAppInfo")) {

            getAllAppInfo(callbackContext);
            return true;
        }

        if (action.equals("getAppInfo")) {
            String packageName = args.getString(0);
            getAppInfo(packageName,callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }


    private void getAllAppInfo(CallbackContext callbackContext) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);


        JSONArray jsonInfos = new JSONArray();

        try {
            for (int i = 0; i < packages.size(); ++i) {
                try {
                    PackageInfo pagItem = packages.get(i);
                    JSONObject jsonPagItem= new JSONObject();

                    jsonPagItem.put("name",pagItem.applicationInfo.loadLabel(packageManager).toString());
                    jsonPagItem.put("versionName",pagItem.versionName);
                    jsonPagItem.put("versionCode",pagItem.versionCode);
                    jsonPagItem.put("packageName",pagItem.packageName);

                    jsonInfos.put(jsonPagItem);
                } catch (Exception e) {
                    Log.e(TAG,e.getMessage());
                }
            }



        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        PluginResult r = new PluginResult(PluginResult.Status.OK, jsonInfos);

        r.setKeepCallback(true);

        callbackContext.sendPluginResult(r);
    }


    private void getAppInfo(String packageName,CallbackContext callbackContext) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();



        try {

                    PackageInfo pagItem = packageManager.getPackageInfo(packageName,0);
                    JSONObject jsonPagItem= new JSONObject();

                    jsonPagItem.put("name",pagItem.applicationInfo.loadLabel(packageManager).toString());
                    jsonPagItem.put("versionName",pagItem.versionName);
                    jsonPagItem.put("versionCode",pagItem.versionCode);
                    jsonPagItem.put("packageName",pagItem.packageName);



            PluginResult r = new PluginResult(PluginResult.Status.OK, jsonPagItem);

            r.setKeepCallback(true);

            callbackContext.sendPluginResult(r);



        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }


    }
}
