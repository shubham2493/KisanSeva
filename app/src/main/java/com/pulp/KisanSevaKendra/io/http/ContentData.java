/*
 * Copyright (c) 2016 Pulp Strategy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pulp.KisanSevaKendra.io.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.pulp.KisanSevaKendra.listeners.AppRequest;
import com.pulp.KisanSevaKendra.utils.Constants;
import com.pulp.KisanSevaKendra.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 02/08/16.
 */
public class ContentData extends BaseTask<JSONObject> {
    private static final String TAG = "UserLogin";
    private Map<String, String> headers = new HashMap<String, String>();
    private Context mContext;
    private AppRequest appRequest;
    private Map<String, String> mParams;
    private Constants.RequestParam requestParam;
    @Override
    public Map<String, String> getHeaders()  throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
       // headers.put(Constants.AUTH_TOKEN, Utility.getAppPreferences(mContext).getString(AUTH_TOKEN, ""));
        return headers;
    }
    public void setHeaders(String title, String content) {
        headers.put(title, content);
    }


    public ContentData(Context context, AppRequest appRequest, Constants.RequestParam requestParam, Response.ErrorListener listener,Integer current_id,Integer previous_id,Integer language_id,String userinput,String state_id,String district_id ,String bypass,String longitude,String latitude,String authtoken) {
        super(requestParam, listener);
        this.requestParam = requestParam;
        this.mContext = context;
        this.appRequest = appRequest;
//        if(!Utility.getAppPreferences(context).getString(Constants.GCM_PUSH_TOKEN,"").equals("")){
//            Utility.getAppPreferences(context).edit().putBoolean(Constants.PUSH_TOKEN_SENT,Boolean.TRUE).apply();
//        }
        mParams = new HashMap<String, String>();
        try {



            mParams.put("current_id",String.valueOf(current_id));
            mParams.put("previous_id",String.valueOf(previous_id));
            mParams.put("language_id",String.valueOf(language_id));
            mParams.put("userinput",userinput);
            mParams.put("state_id",state_id);
            mParams.put("district_id",district_id);
            mParams.put("bypass",bypass);
            mParams.put("lat",latitude);
            mParams.put("long",longitude);
            mParams.put("token",authtoken);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setRetryPolicy(Utility.getRetryPolicy());
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }
        return volleyError;
    }


    @Override
    protected void deliverResponse(JSONObject response) {
        setJsonResponse(response);
        appRequest.onRequestCompleted(this, requestParam);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        JSONObject json = null;
        try {

            json = new JSONObject(new String(response.data));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Response.success(json, HttpHeaderParser.parseCacheHeaders(response));

    }
}
