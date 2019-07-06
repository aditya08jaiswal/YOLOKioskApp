package com.yolohealth.yolokioskapp.httpclient;

import org.json.JSONObject;

/**
 * Created by yolo on 4/10/15.
 */
public abstract class HTTPListener {

    public abstract void onSuccess(JSONObject response);
    public abstract void onError(String error);

}
