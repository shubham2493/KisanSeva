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
package com.pulp.KisanSevaKendra.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class is created for Location properties
 * Created by nikhil on 24/04/15.
 */
public class LocationPoint implements  Serializable {

    @Expose
    @SerializedName("id")
    private long locationDbId;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String cid;
    @Expose
    private String lac;
    @Expose
    private String mcc;
    @Expose
    private String mnc;
    @Expose
    private List<String> images = new ArrayList<>();
    @Expose
    private int type;

    @Expose
    private String updateTime;
    private long rowId;
    private long trailDBId;
    private String imageUri;
    private boolean isOffline;

    public LocationPoint() {

    }

    //Start and End Locatoin Point
    public LocationPoint(int type, long trailDBId, String latitude, String longitude, String title, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.title = title;
        this.description = description;
        this.trailDBId = trailDBId;
    }

    //Infor Marker
    public LocationPoint(long locationDbId, int type, long trailDBId, String latitude, String longitude, String title, String description, String imageUri, String updateTime) {
        this.locationDbId = locationDbId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.title = title;
        this.description = description;
        this.trailDBId = trailDBId;
        this.imageUri = imageUri;
        this.updateTime = updateTime;
    }

    //Create Trail when no trail ID is there Start and End Locatoin Point
    public LocationPoint(int type, String latitude, String longitude, String title, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.title = title;
        this.description = description;
    }

    //Offline Location Point
    public LocationPoint(long rowId, int type, long trailDBId, String latitude, String longitude, String title, String description, String cid, String lac, boolean isOffline) {
        this.rowId = rowId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trailDBId = trailDBId;
        this.type = type;
        this.title = title;
        this.description = description;
        this.cid = cid;
        this.lac = lac;
        this.isOffline = isOffline;
    }

    //Offline Location Point before insert i.e. no column Id exits
    public LocationPoint(int type, long trailDBId, String latitude, String longitude, String title, String description, String cid, String lac) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.trailDBId = trailDBId;
        this.type = type;
        this.title = title;
        this.description = description;
        this.cid = cid;
        this.lac = lac;
    }

    //Location Point before sync starts i.e no locationDBID present neither updateTime
    public LocationPoint(int type, long trailDBId, String latitude, String longitude, String title, String description, String imageUri) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.title = title;
        this.description = description;
        this.trailDBId = trailDBId;
        this.imageUri = imageUri;
    }

    public String getCid() {
        return cid;
    }

    public String getLac() {
        return lac;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    /**
     * @return The latitude
     */
    public String getLatitude() {
        return latitude;
    }


/*

Location Point for offline checkins

 */

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The images
     */
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public long getLocationDbId() {
        return locationDbId;
    }

    public void setLocationDbId(long locationDbId) {
        this.locationDbId = locationDbId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTrailDBId() {
        return trailDBId;
    }

    public void setTrailDBId(long trailDBId) {
        this.trailDBId = trailDBId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public long getRowId() {
        return rowId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setIsOffline(boolean isOffline) {
        this.isOffline = isOffline;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public enum LocationPointType {
        START_POINT(3),
        END_POINT(4),
        CHECK_POINT(1),
        HOTSPOT_POINT(2);

        private int id;

        LocationPointType(int id) {
            this.id = id;
        }

        public static LocationPointType getLocationPointById(int id) {
            switch (id) {
                case 1:
                    return CHECK_POINT;
                case 2:
                    return HOTSPOT_POINT;
                case 3:
                    return START_POINT;
                case 4:
                    return END_POINT;
                default:
                    return null;
            }
        }

        public int getId() {
            return id;
        }
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }
}
