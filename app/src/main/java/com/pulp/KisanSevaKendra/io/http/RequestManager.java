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
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Request Manager Class for Managing the Network Requests
 * 
 */
public class RequestManager {

	Bitmap bitmap = null;

	private static RequestManager mRequestManager;

	/**
	 * Queue which Manages the Network Requests :-)
	 */
	private static RequestQueue mRequestQueue;

	// ImageLoader Instance

	private RequestManager() {

	}

	public static RequestManager get(Context context) {

		if (mRequestManager == null)
			mRequestManager = new RequestManager();

		return mRequestManager;
	}

	/**
	 * @param context
	 *            application context
	 * 
	 */
	public static RequestQueue getnstance(Context context) {

		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
//			OkHttpClient okHttpClient = new OkHttpClient();
//			okHttpClient.networkInterceptors().add(new StethoInterceptor());
//			mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(okHttpClient));
		}

		return mRequestQueue;

	}


}
