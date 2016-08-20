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


package com.pulp.KisanSevaKendra.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Base64;
import com.google.android.gms.maps.model.LatLng;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utility {
    public final static Uri BASE_APP_URI = Uri.parse("android-app://com.pulp.trailsofindia/http/trailsofindia.pulp.com/");
    private static final String API_VERSION_BIKE = "1.1";
    private static final String API_VERSION_DUAL = "1.1";//Byclye + Bike
    public static final String PLATFORM = "0";
    public final static String APP_PATH_SD_CARD = "/PhilipsSpeedStyle/";
    public final static String APP_THUMBNAIL_PATH_SD_CARD = "thumbnails";
    private static final String PREFERENCES_FILE = "MichilenPrefrence";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static String APP_VERSION = "1.1";
    public static String savedImageUrl;
    private static Bitmap FINAL_JOINED_IMAGE = null;
    private static LinkedHashMap<String, Bitmap> imageCache;
    private String iv = "fedcba9876543210";

    private String SecretKey = "0123456789abcdef";

    public static void setBitmap(Bitmap finalbitmap) {
        FINAL_JOINED_IMAGE = finalbitmap;
    }

    public static Uri saveImageToExternalStorage(Bitmap image, Context cntxt) {
        Uri absolutePath = null;
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD + APP_THUMBNAIL_PATH_SD_CARD;
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            OutputStream fOut;
            File file = new File(fullPath, String.valueOf(System.currentTimeMillis()).concat(".png"));
            file.createNewFile();
            fOut = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            absolutePath = Uri.fromFile(file);
            Images.Media.insertImage(cntxt.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            savedImageUrl = fullPath;
        } catch (Exception e) {
            savedImageUrl = null;
        }
        return absolutePath;
    }

    /**
     * Return the string from shared preference for the app.
     *
     * @param context
     * @return {@link SharedPreferences}
     */
    public static SharedPreferences getAppPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

//    public static SharedPreferences getProfilePrefrences(Context context) {
//        return context.getSharedPreferences(PROFILE_PREFERENCES_FILE, Context.MODE_PRIVATE);
//    }

    public static String getDeviceID(Context context) {
        TelephonyManager mngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mngr.getDeviceId();
    }

    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();

        }
    }

    public static String getBase64FromBitmap(final Bitmap bitmap) {
        String encodedFormImage = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] uploadBitmap = byteArrayOutputStream.toByteArray();
            encodedFormImage = Base64.encodeToString(uploadBitmap, Base64.DEFAULT);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedFormImage;
    }
//
//    public static String getDirectionsUrlWithWaypoints(LatLng origin, LatLng dest, ArrayList<LocationPoint> checkins, int start, int end) {
//        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
//        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
//        String sensor = "sensor=false";
//        String waypoints = "";
//        String parameters = "";
//        if (checkins != null && checkins.size() > 0) {
//            waypoints = "waypoints=";
//            for (int i = start; i <= end; i++) {
//                LatLng point = new LatLng(Double.valueOf(checkins.get(i).getLatitude()), Double.valueOf(checkins.get(i).getLongitude()));
//                waypoints += point.latitude + "," + point.longitude + "|";
//            }
//            parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + waypoints + "&alternatives=true";
//            ;
//        } else {
//            parameters = str_origin + "&" + str_dest + "&" + sensor + "&alternatives=true";
//            ;
//        }
//        String output = "json";
//        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
//        return url;
//    }
//
    public static String getDirectionsUrlWithoutWayPoints(LatLng origin, LatLng dest) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&alternatives=true";
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }
//
//    public static String autoCompleteTextViewUrl(Activity context, String text) {
//        String key = "AIzaSyCI0049MaICQ7gtKLvNHM3otNY-PBlI5Tw";
//        String input = "";
//        try {
//            input = URLEncoder.encode(text, "utf-8");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
//        googlePlacesUrl.append("input=" + input);
//        googlePlacesUrl.append("&sensor=false");
//        googlePlacesUrl.append("&components=country:in");
//        googlePlacesUrl.append("&key=" + context.getResources().getString(R.string.google_maps_key_server));
//        return googlePlacesUrl.toString();
//    }
//
//    public static String placeDetailsFromPlaceidUrl(Activity context, String placeid) {
//        String key = "AIzaSyCI0049MaICQ7gtKLvNHM3otNY-PBlI5Tw";
//        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?");
//        googlePlacesUrl.append("placeid=" + placeid);
//        googlePlacesUrl.append("&key=" + context.getResources().getString(R.string.google_maps_key_server));
//
//        return googlePlacesUrl.toString();
//    }
//
//    public static String nearbyPlacesUrl(Activity context, LatLng checkinLocation) {
//        StringBuilder googlePlacesUrl = null;
//        if (checkinLocation != null) {
//            googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//            googlePlacesUrl.append("location=" + checkinLocation.latitude + "," + checkinLocation.longitude);
//            googlePlacesUrl.append("&radius=" + 1000);
//            googlePlacesUrl.append("&sensor=false");
//            googlePlacesUrl.append("&key=" + context.getResources().getString(R.string.google_maps_key_server));
//        }
//        return googlePlacesUrl.toString();
//    }
//
//    public static Marker drawMarker(LatLng point, GoogleMap map, LocationPoint.LocationPointType type) {
//        if (point != null) {
//            MarkerOptions markerOptions = new MarkerOptions();
//            BitmapDescriptor hotspotIcon = BitmapDescriptorFactory.fromResource(R.drawable.hotspot);
//            if (type == LocationPoint.LocationPointType.START_POINT)
//                markerOptions.position(point).title("Start Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//            else if (type == LocationPoint.LocationPointType.END_POINT)
//                markerOptions.position(point).title("End Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//            else if (type == LocationPoint.LocationPointType.CHECK_POINT)
//                markerOptions.position(point).title("Checkin").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//            else
//                markerOptions.position(point).title("Hotspot").icon(hotspotIcon);
//            if (point.latitude != 0 && point.longitude != 0)
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(point.latitude, point.longitude), 12.0f));
//            Marker marker = map.addMarker(markerOptions);
//            return marker;
//        } else
//            return null;
//    }
//
//    public static JSONObject saveLocation(LatLng location) {
//        try {
//            JSONObject locationJson = new JSONObject();
//
//            locationJson.put("LATITUDE", location.latitude);
//            locationJson.put("LONGITUDE", location.longitude);
//            return locationJson;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    public static Date modifyToday() {
        Date current = new Date();
        long prevDay = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
        Date prev = new Date(prevDay);
        return prev;
    }

    public static boolean isNetworkConnectionAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public static Uri Savefile(Context mContext) {
        Uri mImageCaptureUri = null;
        File imageFolder;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            imageFolder = new File(Environment.getExternalStorageDirectory(), "ProfilePics/");
        else imageFolder = mContext.getCacheDir();
        if (!imageFolder.exists())
            imageFolder.mkdirs();
        String filename = imageFolder.getName();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String imageFileName = filename + timeStamp + ".jpg";

        File photo = new File(Environment.getExternalStorageDirectory(), "ProfilePics/" + imageFileName);
        mImageCaptureUri = Uri.fromFile(photo);

        return mImageCaptureUri;
    }

    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 200;
        int targetHeight = 200;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth) / 2,
                ((float) targetHeight) / 2,
                (Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
                Path.Direction.CW);
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawOval(new RectF(0, 0, targetWidth, targetHeight), paint);
        paint.setColor(Color.WHITE);
        canvas.clipPath(path);
        canvas.drawBitmap(scaleBitmapImage, new Rect(0, 0, scaleBitmapImage.getWidth(),
                scaleBitmapImage.getHeight()), new RectF(0, 0, targetWidth,
                targetHeight), paint);
        return scaleBitmapImage;
    }

    public static String getYoutubeVideoId(String viewUrl) {
        String videoId = null;
        try {
            Pattern pattern = Pattern.compile(".*(?:youtu.be\\/|v\\/|u\\/\\w\\/|embed\\/|watch\\?v=|watch\\?)([^#\\&\\?]*).*");
            Matcher matcher = pattern.matcher(viewUrl);
            if (matcher.matches()) {
                videoId = matcher.group(1);
            } else if (!viewUrl.contains("//v")) {
                videoId = viewUrl.substring(viewUrl.lastIndexOf("//"), viewUrl.length());
            } else {
                videoId = viewUrl.substring(viewUrl.lastIndexOf("//"), viewUrl.length());
            }
        } catch (Exception e) {
            videoId = null;
        }
        return videoId;
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If it
     * doesn't, display a dialog that allows users to download the APK from the
     * Google Play Store or enable it in the device's system settings.
     */
//    public static boolean checkPlayServices(Activity mActivity) {
//        int resultCode = GooglePlayServicesUtil
//                .isGooglePlayServicesAvailable(mActivity);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                GooglePlayServicesUtil.getErrorDialog(resultCode, mActivity,
//                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
//            } else {
//                // finish();
//            }
//            return false;
//        }
//        return true;
//    }

    public static Bitmap getResizedBitmap(String imageDecodeString) {
        Bitmap bitmap = null;
        try {
            if (imageDecodeString == null) {
                throw new NullPointerException();
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inPurgeable = Boolean.TRUE;
                bitmap = BitmapFactory.decodeFile(imageDecodeString, options);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }






    public static int BooleanToInt(boolean value) {

        if (value)
            return 1;
        else
            return 0;
    }

    public static String formatDate(String dateString) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" /* 10-Sep-2013 09:53:37*/);
            Date d = sd.parse(dateString);
            sd = new SimpleDateFormat("dd-MM-yyyy");
            return sd.format(d);
        } catch (ParseException e) {
        }
        return "";
    }

    public static boolean isImageHeavy(long bytes) {
        boolean imageHeavy = Boolean.FALSE;
        long sizeInMb = bytes / (1024 * 1024);
        if (sizeInMb > 3.5) {
            imageHeavy = Boolean.TRUE;
        } else {
            imageHeavy = Boolean.FALSE;
        }
        return imageHeavy;
    }



    public static void onRegistration(Context context, String event) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.PARAM_1, event);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.COMPLETE_REGISTRATION, eventValue);
    }

    public static void analyticsUiPopulated(Context context, String viewName, String contentId) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, viewName);
//        if (contentId != null && contentId.length() > 0) {
//            eventValue.put(AFInAppEventParameterName.PARAM_1, contentId);
//        }
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.CONTENT_VIEW, eventValue);
    }

    public static void analyticsButtonClick(Context context, String buttonName) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, "Button Clicked");
//        eventValue.put(AFInAppEventParameterName.PARAM_1, buttonName);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.UPDATE, eventValue);
    }

    public static void analyticsTutorialComplete(Context context, boolean tutorialComplete, String tutorialType) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.SUCCESS, tutorialComplete);
//        eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, tutorialType);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.TUTORIAL_COMPLETION, eventValue);
    }

    public static void analyticsListContent(Context context, String viewName, String listType) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, viewName);
//        eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, listType);
//        AppsFlyerLib.trackEvent(context, AFInAppEventParameterName.CONTENT_LIST, eventValue);
    }

    public static void analyticsShare(Context context, String viewName, String contentType) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, viewName);
//        eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, contentType);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.SHARE, eventValue);
    }

    public static void analyticsInvite(Context context, String viewName, String inviteIds) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, viewName);
//        eventValue.put(AFInAppEventParameterName.PARAM_1, inviteIds);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.INVITE, eventValue);
    }

    public static void analyticsLogin(Context context, String viewName, String loginType) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, viewName);
//        eventValue.put(AFInAppEventParameterName.PARAM_1, loginType);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.LOGIN, eventValue);
    }

    public static void analyticsPushNotification(Context context, String viewName, String notificationTypeId) {
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, viewName);
//        eventValue.put(AFInAppEventParameterName.PARAM_1, notificationTypeId);
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.OPENED_FROM_PUSH_NOTIFICATION, eventValue);
    }


    public static void onEventAnalyticsAppFlyer(Context context, String name, String event) {
        // Get tracker.
//        new TrackingAysnc(context, name, event).execute();
//        Map<String, Object> eventValue = new HashMap<String, Object>();
//        eventValue.put(AFInAppEventParameterName.PARAM_1, name);
//        eventValue.put(AFInAppEventParameterName.PARAM_1, event);
//        eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, name);
//        eventValue.put(AFInAppEventParameterName.CONTENT_ID, event);
//
//        AppsFlyerLib.trackEvent(context, AFInAppEventType.LOGIN, eventValue);
//        Tracker t = ((TrailsOfIndia) mActivity.getApplication()).getTracker();
//// Build and send an Event.
//        t.send(new HitBuilders.EventBuilder()
//                .setCategory("ANDROID")
//                .setAction(label)
//                .setLabel(label)
//                .build());
    }

    public static String getAppVersion(Context context) {

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            APP_VERSION = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return APP_VERSION;
    }

    public static int[] getOfflineLocationParameters(Context mContext) {
        int cid = 0, lac = 0;
        int[] locArray;

        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();


        telephonyManager.getNetworkType();

        if (cellLocation != null) {
            cid = cellLocation.getCid();
            lac = cellLocation.getLac();
        }
        locArray = new int[]{cid, lac};

        return locArray;

    }





    public class TestThread implements Runnable {

        @Override
        public void run() {

        }
    }


//    public static void addTagToGoogleIndex(String title, String trailDbId, String type) {
//        try {
//            StringBuffer sbf = new StringBuffer();
//            final String TITLE = title;
//            sbf.append(trailDbId).append("|").append(type);
//            String output = sbf.toString();
//            final Uri APP_URI = BASE_APP_URI.buildUpon().appendPath(output).build();
//            Action viewAction = Action.newAction(Action.TYPE_VIEW, TITLE, APP_URI);
//            PendingResult<Status> result = AppIndex.AppIndexApi.start(TrailsOfIndia.getInstance().mClient, viewAction);
//            result.setResultCallback(new ResultCallback<Status>() {
//                @Override
//                public void onResult(Status status) {
//                    if (status.isSuccess()) {
//                    } else {
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static RetryPolicy getRetryPolicy() {
        int DEFAULT_TIMEOUT_MS = 0;
        float DEFAULT_BACKOFF_MULT = 0.7f;
        return new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static String getDecodedString(String str) {
        try {
            str = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    public static String getEncodedString(String str) {

        String string = str;

        try {
            string = URLEncoder.encode(string, "UTF8");
        } catch (UnsupportedEncodingException e2) {
//            Log.e("Utility", "exception".concat(e2.getMessage()));
            e2.printStackTrace();
        } catch (IllegalArgumentException e1) {
//            Log.e("Utility", "exception".concat(e1.getMessage()));
            e1.printStackTrace();
        } catch (NullPointerException e) {
//            Log.e("Utility", "exception".concat(e.getMessage()));
            e.printStackTrace();
        }
        return string;
    }


    private static String[] requestPermissionArray = new String[]{

            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
    };
    private static final int PERMISION_REQUEST_CODE = 101;

    public static boolean permisionsRequired(Activity activity) {

        boolean isPermissionAvialable;
        if (!isMarshmallowOrAbove()) {
            isPermissionAvialable = Boolean.FALSE;
        } else if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isPermissionAvialable = Boolean.TRUE;
        } else if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isPermissionAvialable = Boolean.TRUE;
        } else {
            isPermissionAvialable = Boolean.FALSE;
        }
        return isPermissionAvialable;
    }

    public static boolean isMarshmallowOrAbove() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= 23) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestPermission(Activity activity) {
        ArrayList<String> permissionArrayList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionArrayList.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionArrayList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        String[] arr = permissionArrayList.toArray(new String[permissionArrayList.size()]);
        ActivityCompat.requestPermissions(activity, arr, PERMISION_REQUEST_CODE);
    }

    public static String getApiVersion() {
        if (Constants.onlyBikeModeApp) {
            return API_VERSION_BIKE;
        } else {
            return API_VERSION_DUAL;
        }
    }
    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(Activity activity) {
        if(activity!=null) {
            if (activity.getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}

