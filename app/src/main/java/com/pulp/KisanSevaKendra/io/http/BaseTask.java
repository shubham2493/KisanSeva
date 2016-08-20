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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;

import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import static com.pulp.KisanSevaKendra.utils.Constants.RequestParam;


public abstract class BaseTask<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    protected static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    protected MultipartEntity entity;

    protected Map<String, String> mBundle;

    public Map<String, String> getmBundle() {
        return mBundle;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return super.getParams();
    }

    public void setmBundle(Map<String, String> mBundle) {
        this.mBundle = mBundle;
    }


    String tag;
    String subCategory;

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    JSONObject jsonResponse;
    JSONArray jsonArrayResponse;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public JSONObject getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(JSONObject jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public void setJsonArrayResponse(JSONArray jsonResponse) {
        this.jsonArrayResponse = jsonResponse;
    }

    public JSONArray getJsonArrayResponse() {
        return jsonArrayResponse;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    String response;

    static final int MY_SOCKET_TIMEOUT_MS = 10000;
    static final int DEFAULT_MAX_RETRIES = 2;
    static final int DEFAULT_BACKOFF_MULT = 2;

    public BaseTask(RequestParam requestParam, ErrorListener listener) {
        super(requestParam.getMethod(), requestParam.getComleteUrl(), listener);
        setShouldCache(false);
        this.tag = requestParam.getRequestTag();
        this.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DEFAULT_MAX_RETRIES,
                DEFAULT_BACKOFF_MULT));
    }

}
