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
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pulp.KisanSevaKendra.listeners.AppRequest;
import com.pulp.KisanSevaKendra.utils.Constants;
import com.pulp.KisanSevaKendra.utils.Utility;

/**
 * Created by shubham on 02/08/16.
 */
public class ApiRequests {

    public static final String PREFERENCES_FILE = "MichlinPreference";
    private static final String TAG = "ApiRequests";
    private static ApiRequests apiRequests = null;
    private RequestQueue mRequestQueue;
    private Constants.RequestParam requestParam;

    public static final int DEFAULT_TIMEOUT_MS = 0;
    public static final float DEFAULT_BACKOFF_MULT = 0.7f;

    public static ApiRequests getInstance() {
        if (apiRequests == null) {
            apiRequests = new ApiRequests();
            return apiRequests;
        }
        return apiRequests;
    }




    public void ContentData(Context context, AppRequest appRequest, Constants.RequestParam requestParam, Integer current_id,Integer previous_id,Integer language_id,String userinput,String state_id,String district_id ,String bypass,String longitude,String latitude,String authtoken) {
        if (context != null) {
            this.requestParam = requestParam;
            mRequestQueue = RequestManager.getnstance(context);

            VolleyErrorListener error = new VolleyErrorListener();

            ContentData  contentData = new ContentData(context, appRequest, requestParam, error,current_id,previous_id,language_id,userinput,state_id,district_id,bypass,longitude,latitude,authtoken);

            error.setRequestLister(appRequest, contentData);
            contentData.setRetryPolicy(Utility.getRetryPolicy());
            mRequestQueue.add(contentData);

            appRequest.onRequestStarted(contentData, requestParam);
        }

    }

    /**
     * Will be reponsible for listening errors
     * <p/>
     * *
     */
    class VolleyErrorListener implements Response.ErrorListener {
        private AppRequest listener;
        private BaseTask<?> task;

        void setRequestLister(AppRequest listener, BaseTask<?> task) {
            this.listener = listener;
            this.task = task;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            listener.onRequestFailed(task, requestParam);
        }
    }
}
