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
package com.pulp.KisanSevaKendra.adapters;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.pulp.KisanSevaKendra.R;
import com.pulp.KisanSevaKendra.beans.ChatMessage;
import com.pulp.KisanSevaKendra.beans.Option;
import com.pulp.KisanSevaKendra.io.http.ApiRequests;
import com.pulp.KisanSevaKendra.ui.MainActivity;
import com.pulp.KisanSevaKendra.utils.Constants;

import java.util.ArrayList;

/**
 * Created by shubham on 03/08/16.
 */
public class OptionAdapter extends BaseAdapter {

    private Option optionslist = null;
    Activity mActivity;
    private ChatArrayAdapter chatArrayAdapter;
    private boolean side = false;
    private LayoutInflater inflater = null;
    private Context context;
    private TextView optionstextview;
    private LinearLayout optionstextviewlayout;
    private ListView optionlistview;
    private MainActivity mainActivity;
    private String longitude;
    private String latitude;
    private String authtoken="";

    public OptionAdapter(Activity mActivity, Option options, ChatArrayAdapter chatArrayAdapter, ListView optionlist, MainActivity mainActivity, String latitude, String longitude) {
        this.mainActivity = mainActivity;
        this.chatArrayAdapter = chatArrayAdapter;
        this.optionslist = options;
        this.inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mActivity = mActivity;
        this.context = mActivity.getApplicationContext();
        this.optionlistview = optionlist;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public int getCount() {
        return optionslist.getAnswer().length;
    }

    @Override
    public Object getItem(int position) {
        return optionslist.getAnswer()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        vi = inflater.inflate(R.layout.optionlayout, null);

        optionstextview = (TextView) vi.findViewById(R.id.optionstxtview);
        optionstextview.setText(optionslist.getAnswer()[position].getQuestion());
        optionstextviewlayout = (LinearLayout) vi.findViewById(R.id.optionslayout);
//        int  is_child = Integer.valueOf(optionslist.get(position).getIs_child());
//        if(is_child==0)
//        {
//            sendChatMessagehint("Thank you");
//        }


        optionstextviewlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int current_id = Integer.valueOf(optionslist.getAnswer()[position].getId());
                int previous_id = Integer.valueOf(optionslist.getAnswer()[position].getParent_id());
                int language_id = 0;
                String userinput = "";
                String state_id = optionslist.getAnswer()[position].getState_id();
                String district_id = optionslist.getAnswer()[position].getDistrict_id();
                String bypass = optionslist.getAnswer()[position].getBypass();

//                int address = Integer.valueOf(optionslist.getAddress());
//                if(address==1)
//                {
//                    String addsearch = optionslist.getQuestion();
//                    getLocationFromAddress(mActivity, addsearch);
//                }


                int is_child = Integer.valueOf(optionslist.getAnswer()[position].getIs_child());


                String ans = optionslist.getAnswer()[position].getQuestion();
                String add = optionslist.getAnswer()[position].getQuestion();
                sendChatMessagehint(ans);

                if (is_child != 0) {
                    if (optionslist.getAnswer()[position].isAppExit()) {
                        System.exit(0);
                    } else {
                        authtoken = optionslist.getToken();
                        ApiRequests.getInstance().ContentData(mainActivity, mainActivity, Constants.RequestParam.CONTENTDATA, current_id, previous_id, language_id, userinput, state_id, district_id, bypass, longitude, latitude, optionslist.getToken());
                        optionlistview.setVisibility(View.GONE);
                    }
                } else {
                    optionlistview.setVisibility(View.GONE);
                    sendChatMessagehint("धन्यवाद");
                    MainActivity.RESULT_AWAITED = Boolean.FALSE;
                    chatArrayAdapter.notifyDataSetChanged();
                }
            }

        });


        return vi;
    }

    private boolean sendChatMessagehint(String msg) {

        chatArrayAdapter.add(new ChatMessage(!side, msg));
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        int isChild = Integer.valueOf(optionslist.getAnswer()[position].getIs_child());
        if (isChild > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
