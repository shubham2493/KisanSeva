
package com.pulp.KisanSevaKendra.utils;

import android.widget.Toast;

import com.android.volley.Request.Method;



public class Constants {


   private  static final  String BASE_URL ="http://52.39.54.43/hackathon/";



    public static final int INTERNET_AVAILABLE = 101;
    public static final int NO_INTERNET_AVAILABLE = 102;

    public static final String YOUTUBE_FRAME_BASE_URL = "http://img.youtube.com/vi/";
    public static final String YOUTUBE_FRAME_POSTFIX_URL = "/1.jpg";
    public static final String GCM_REGITRATION_ID = "649855110665";
    public static final String GCM_PUSH_TOKEN = "gcm_push_token";
    public static final String PUSH_TOKEN_SENT = "pushtokensent";
    public static final String SELECTEDPOSITION = "SELECTEDPOSITION";
    public static final int CODELOGIN = 200;
    public static final String PROFILE_FETCHED = "profileFetched";
    public static final String PREFERENCES_NOTIFICATION_DISABLED = "isNotificationDisabled";
    public static final String PREFERENCES_SOUND_DISABLED = "isSoundDisabled";
    public static final String PREFERENCES_APP_STATE = "isAppStateBike";
    public static final String PROFILE_PREFERENCES_FILE = "MichilenProfilePrefrence";
    public static final String ISPROFILECOMPLETED = "isprofilecompleted";
    public static final String FIRSTGOOGLEREQUEST = "firstfacebookrequest";
    public static final String QUIT = "quit";
    public static final String ISLOGGEDIN = "isloggedIn";
    public static final String FIRSTNAME = "first_name";
    public static final String LASTNAME = "last_name";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String LOGIN_TYPE = "type";
    public static final String PROFILE_SERVER_TIME = "profile_server_type";
    public static final String AUTH_TOKEN = "Auth-Token";
    public static final String FIRST_LAUNCH = "firstLaunch";
    public static final String PIC_URL = "picurl";
    public static final String COVER_PIC_URL = "coverPicurl";
    public static final String ISFROMLOGIN = "isFromLogin";
    public static final String IS_APP_STATE_SET = "isAppStateSet";
    public static final String GENDER = "gender";
    public static final String COMMENTS_LIST = "comments_list";
    public static final String LOCATION_LIST = "hotspot_list";
    public static final String CHECKIN_LIST = "checkin_list";
    public static final String IS_FROM_NOTIFICATIONS = "is_from_notifications";
    public static final String NOTIFIACTION_TYPE_ID = "notification_type_id";
    public static final String NOTIFIACTION_URL = "notification_url";
    public static final String NOTIFIACTION_TRAILID = "notification_trailId";
    public static final String NOTIFIACTION_ID = "notification_id";
    public static final String READ_NOTIFIACTION_LIST = "read_notification_list";
    public static final String UNREAD_NOTIFIACTION_LIST = "unread_notification_list";
    public static final String NOTIFICATION_LIST = "notification_list";
    public static final String ORIGIN_LATLNG = "origin";
    public static final String DEST_LATLNG = "dest";
    public static final String USERID = "user_id";
    public static final String INTENT_EVENT_IS_ERROR = "isEventError";
    public static final String INTENT_TRAIL_DB_ID = "trailDbId";
    public static final String INTENT_TRAIL_IS_OWNER = "trailOwner";
    public static final String INTENT_TRAIL_NAME = "trailDbName";
    public static final String INTENT_TRAIL_START_LOCATION = "startLocation";
    public static final String INTENT_TRAIL_END_LOCATION = "endLocation";
    public static final String INTENT_TRAIL_DESCRIPTION = "INTENT_TRAIL_DESCRIPTION";
    public static final String INTENT_TRAIL_DISTANCE = "INTENT_TRAIL_DISTANCE";
    public static final String INTENT_TRAIL_COVER_PHOTO = "trailCoverPhoto";
    public static final String INTENT_TRAIL_TYPE = "trailType";
    public static final String INTENT_IS_HOTSPOT = "intent_is_hotspot";
    public static final String INTENT_BRAND_NAME = "brandName";
    public static final String INTENT_MODEL_NAME = "modelName";
    public static final String INTENT_YEAR_NAME = "yearName";
    public static final String INTENT_BIKE_IMAGE = "imageUrl";
    public static final String INTENT_BIKE_DESC = "description";
    public static final String INTENT_BIKE_ID = "bikeid";
    public static final String INTENT_BRAND_ID = "brandid";
    public static final String INTENT_MODEL_ID = "modelid";
    public static final String INTENT_BIKE_NAME = "bikeName";
    public static final String INTENT_IMAGE_INDEX = "startIndex";
    public static final String INTENT_IMAGES = "imagesArray";
    public static final String INTENT_LATTITUDE = "latitude";
    public static final String INTENT_LONGITUDE = "longitude";
    public static final String INTENT_LOCATION = "INTENT_LOCATION";
    public static final String INTENT_LOCATION_ID = "INTENT_LOCATION_ID";
    public static final String INTENT_LOCATION_TITLE = "INTENT_LOCATION_TITLE";
    public static final String INTENT_LOCATION_IMAGE = "INTENT_LOCATION_IMAGE";
    public static final String INTENT_LOCATION_DESCRIPTION = "INTENT_LOCATION_DESCRIPTION";
    public static final String INTENT_LOCATION_EDIT = "INTENT_LOCATION_EDIT";
    public static final String INTENT_OFFLINE = "INTENT_OFFLINE";
    public static final String INTENT_CHECKIN = "INTENT_CHECKIN";
    public static final String COMMENTS_SIZE = "COMMENTS_SIZE";
    public static final String BIKER_PROFILE_URL = "BIKER_PROFILE_URL";
    public static final String BIKER_URL = "BIKER_URL";
    public static final String INTERNET_AVAILABLE_FIRST = "INTERNET_AVAILABLE_FIRST";
    public static final int TYPE_TAG_KEY = 10;
    public static final int TRAIL_PICK_FROM_GALLERY = 4;
    public static final String RandonHashKey = "hash";
    /**
     * Trail API *
     */
    public static final String INTENT_TRAIL_FILTER = "trailFilter";
    public static final String INTENT_IS_FRESH_CALL = "isFreshCall";
    public static final String PREFERENCES_MY_TRAIL_SERVERTIME = "myTrailServertime";
    public static final String PREFERENCES_PUBLIC_SERVERTIME = "publicTrailServertime";
    public static final String PREFERENCES_FEATURED_SERVERTIME = "featuredTrailServertime";
    public static final String PREFERENCES_GROUP_SERVERTIME = "groupServertime";
    public static final String PREFERENCES_EVENT_SERVERTIME = "eventServertime";
    public static final String PREFERENCES_FRIENDS_SERVERTIME = "friendsServertime";
    public static final String PREFERENCES_GARAGE_BIKE_SERVERTIME = "myGarageBikeServertime";
    public static final String PREFERENCES_DEFAULT_GARAGE_BIKE_SERVERTIME = "defaultGarageServertime";
    public static final String PREFERENCES_ADD_GROUP = "addGroupLink";
    public static final String PREFERENCES_ADD_EVENT = "addEventLink";
    public static final String PREFERENCES_ADD_FRIENDS = "addFriendLink";
    public static final int GARAGE_PICK_FROM_GALLERY = 5;

    public static int FEATURED_LIST_SIZE = 10;
    ///
    public static int SPLASH_SCREEN_DISPLAY_DURATION = 3000;
    public static int PICK_FROM_CAMERA = 1;
    public static int CROP_FROM_CAMERA = 2;
    public static int PICK_FROM_FILE = 3;

//    public enum PulpError {
//        NONE(0, 0, Toast.LENGTH_SHORT),
//        NO_CONNECTION(1, R.string.hello_world, Toast.LENGTH_SHORT),
//        TIME_OUT_ERROR(2, R.string.app_name, Toast.LENGTH_SHORT),
//        MEMORY_FULL(3, R.string.app_name, Toast.LENGTH_SHORT),
//        IO_ERROR(4, R.string.app_name, Toast.LENGTH_SHORT);
//
//        private int id;
//        private int erroMsgResourceId;
//        private int messageDuration;
//
//        PulpError(int id, int erroMsgResourceId, int messageDuration) {
//            this.id = id;
//            this.erroMsgResourceId = erroMsgResourceId;
//            this.messageDuration = messageDuration;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public int getErroMsgResourceId() {
//            return erroMsgResourceId;
//        }
//
//        public int getMessageDuration() {
//            return messageDuration;
//        }
//    }

    public enum TrailType {
        DRAFT(0, "DRAFT"),
        IN_REVIEW(1, "IN_REVIEW"),
        PUBLISHED(2, "PUBLISHED"),
        FEATURED(3, "FEATURED"),
        REJECTED(4, "REJECTED"),
        SHARED(5, "SHARED");

        private int id;
        private String name;

        TrailType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static TrailType getTrailTypeById(int id) {
            switch (id) {
                case 0:
                    return DRAFT;
                case 1:
                    return IN_REVIEW;
                case 2:
                    return PUBLISHED;
                case 3:
                    return FEATURED;
                case 4:
                    return REJECTED;
                case 5:
                    return SHARED;
                default:
                    return null;
            }
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public enum TrailFilter {
        PUBLIC_TRAILS(0),
        MY_TRAILS(1),
        FEATURED_TRAILS(2);

        private int id;

        TrailFilter(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }


    public enum RequestParam {


        CONTENTDATA(1,Method.POST,"index.php/index","index");


        private int id;
        private int method;
        private String postFix;
        private String requestTag;

        RequestParam(int id, int method, String postFix, String requestTag) {
            this.id = id;
            this.method = method;
            this.postFix = postFix;
        }

        RequestParam(int id, int method, String postFix, String newPostFix, String requestTag) {
            this.id = id;
            this.method = method;
//            if (Constants.onlyBikeModeApp) {
//                this.postFix = postFix;
//            } else {
                this.postFix = newPostFix;
//            }
        }

        public int getId() {
            return id;
        }
        public static String getBaseURL() {return BASE_URL;}

        public String getComleteUrl() {
            return getBaseURL().concat(postFix);
        }

        public String getRequestTag() {
            return requestTag;
        }

        public int getMethod() {
            return method;
        }
    }

    public enum MultimediaType {
        COVER_PHOTO(1),
        IMAGE(2),
        VIDEO(3);

        private int id;

        MultimediaType(int id) {

        }

        public int getId() {
            return id;
        }
    }


    public enum NotificationType {
        HOME(0),
        EVENTS(1),
        GROUPS(2),
        TRAILS(3),
        FRIENDS(4),
        SOCAIL_ARTICLE(5),
        GARAGE(6),
        BADGE(7),
        SINGLE_TRAILS(8);


        private int id;

        NotificationType(int id) {
            this.id = id;
        }

        public static NotificationType getNotificationTypeById(int id) {
            switch (id) {
                case 1:
                    return EVENTS;
                case 2:
                    return GROUPS;
                case 3:
                    return TRAILS;
                case 4:
                    return FRIENDS;
                case 5:
                    return SOCAIL_ARTICLE;
                case 6:
                    return GARAGE;
                case 7:
                    return BADGE;
                case 8:
                    return SINGLE_TRAILS;
                case 0:
                default:
                    return HOME;
            }

        }

        public int getId() {
            return id;
        }
    }


    public enum GenderType {
        Male(0, "Male"),
        Female(1, "Female");


        private int id;
        private String value;

        GenderType(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public static GenderType getGenderTypeById(int id) {
            if (id == 1) {
                return Female;
            } else {
                return Male;
            }
        }

        public String getValue() {
            return value;
        }

        public int getId() {
            return id;
        }
    }


    public static final String CURRENT_IN_APP_TUTORIAL = "currentInAppTutorial";

    public static final boolean onlyBikeModeApp = Boolean.TRUE;


}
