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
package com.pulp.KisanSevaKendra.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.pulp.KisanSevaKendra.R;
import com.pulp.KisanSevaKendra.adapters.ChatArrayAdapter;
import com.pulp.KisanSevaKendra.adapters.OptionAdapter;
import com.pulp.KisanSevaKendra.beans.Answer;
import com.pulp.KisanSevaKendra.beans.ChatMessage;
import com.pulp.KisanSevaKendra.beans.Option;
import com.pulp.KisanSevaKendra.beans.ResponseBean;
import com.pulp.KisanSevaKendra.io.http.ApiRequests;
import com.pulp.KisanSevaKendra.io.http.BaseTask;
import com.pulp.KisanSevaKendra.listeners.AppRequest;
import com.pulp.KisanSevaKendra.utils.Constants;
import com.pulp.KisanSevaKendra.utils.Utility;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AppRequest {
    private ListView listView;
    private EditText chatText;

    private Button buttonSend;
    private ChatArrayAdapter chatArrayAdapter;
    private boolean side = false;
    private ListView optionslist;
    private OptionAdapter optionAdapter;
    private static int current_id = 1;
    private static int previous_id = 0;
    private LinearLayout entertextlayout;
    ResponseBean data;

    private static double longitude;
    private static double latitude;
    private static String authtoken = "";


    private static int language_id = 0;
    String userinput = "";
     ProgressDialog progressDialog;

    public static boolean RESULT_AWAITED = Boolean.FALSE;

    private ArrayList<Answer> answers = new ArrayList<Answer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        buttonSend = (Button) findViewById(R.id.send);

        listView = (ListView) findViewById(R.id.msgview);
        entertextlayout = (LinearLayout) findViewById(R.id.textinputlayout);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.rightmessage);
        listView.setAdapter(chatArrayAdapter);
        chatText = (EditText) findViewById(R.id.msg);
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            if (location == null) {
                Toast.makeText(this, "Unable To find Location", Toast.LENGTH_SHORT);
            } else {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    entertextlayout.setVisibility(View.GONE);
                    current_id = Integer.valueOf(data.getData()[0].getOption().getAnswer()[0].getId());
                    previous_id = Integer.valueOf(data.getData()[0].getOption().getAnswer()[0].getParent_id());
                    userinput = chatText.getText().toString();
                    ApiRequests.getInstance().ContentData(MainActivity.this, MainActivity.this, Constants.RequestParam.CONTENTDATA, current_id, previous_id, language_id, userinput, "0", "0", "0", String.valueOf(longitude), String.valueOf(latitude), authtoken);
                    RESULT_AWAITED = Boolean.TRUE;
                    chatArrayAdapter.notifyDataSetChanged();
                    return sendChatMessage();
                }
                return false;
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
                entertextlayout.setVisibility(View.GONE);
                current_id = Integer.valueOf(data.getData()[0].getOption().getAnswer()[0].getId());
                previous_id = Integer.valueOf(data.getData()[0].getOption().getAnswer()[0].getParent_id());
                userinput = chatText.getText().toString();
                ApiRequests.getInstance().ContentData(MainActivity.this, MainActivity.this, Constants.RequestParam.CONTENTDATA, current_id, previous_id, language_id, userinput, "0", "0", "0", String.valueOf(longitude), String.valueOf(latitude), authtoken);
                RESULT_AWAITED = Boolean.TRUE;
                chatArrayAdapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        // sendChatMessagehint("Namaskar");


        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });


        optionslist = (ListView) findViewById(R.id.hintview);


        ApiRequests.getInstance().ContentData(MainActivity.this, MainActivity.this, Constants.RequestParam.CONTENTDATA, current_id, previous_id, language_id, userinput, "0", "0", "0", String.valueOf(longitude), String.valueOf(latitude), authtoken);


    }


    private boolean sendChatMessage() {
        if (!(chatText.getText().toString()).trim().isEmpty()) {
            chatArrayAdapter.add(new ChatMessage(true, chatText.getText().toString()));
        }
        chatText.setText("");
        side = !side;
        return true;
    }

    private boolean sendChatMessagehint(String msg) {
        chatArrayAdapter.add(new ChatMessage(false, msg));
        return true;
    }


    @Override
    public <T> void onRequestStarted(BaseTask<T> listener, Constants.RequestParam requestParam) {

    }

    @Override
    public <T> void onRequestCompleted(BaseTask<T> listener, Constants.RequestParam requestParam) {

        try {


            answers.clear();
            Gson gson = new Gson();
            data = gson.fromJson(String.valueOf(listener.getJsonResponse()), ResponseBean.class);
            if (data != null) {
                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                RESULT_AWAITED = Boolean.FALSE;
                chatArrayAdapter.notifyDataSetChanged();
                authtoken = data.getData()[0].getOption().getToken();
                sendChatMessagehint(data.getData()[0].getOption().getQuestion());
                int count = Integer.valueOf(data.getData()[0].getOption().getCount());

                if (count != 0) {
                    String is_userinput = data.getData()[0].getOption().getAnswer()[0].getIs_userinput();
                    if (is_userinput.equals("1")) {
                        entertextlayout.setVisibility(View.VISIBLE);
                    }
                    optionslist.setVisibility(View.VISIBLE);
                    for (int i = 0; i < count; i++) {
                        String id = data.getData()[0].getOption().getAnswer()[i].getId();
                        if (id == null) {
                            id = "0";
                        }
                        String is_child = data.getData()[0].getOption().getAnswer()[i].getIs_child();
                        String question = data.getData()[0].getOption().getAnswer()[i].getQuestion();
                        String parent_id = data.getData()[0].getOption().getAnswer()[i].getParent_id();
                        is_userinput = data.getData()[0].getOption().getAnswer()[i].getIs_userinput();
                        String state_id = data.getData()[0].getOption().getAnswer()[i].getState_id();
                        String district_id = data.getData()[0].getOption().getAnswer()[i].getDistrict_id();
                        String bypass = data.getData()[0].getOption().getAnswer()[i].getBypass();
                        Answer ans = new Answer(id, question, is_child, parent_id, is_userinput, state_id, district_id, bypass);
                        answers.add(ans);
                    }
                    optionAdapter = new OptionAdapter(this, data.getData()[0].getOption(), chatArrayAdapter, optionslist, this, String.valueOf(latitude), String.valueOf(longitude));
                    optionslist.setAdapter(optionAdapter);
                    optionslist.setSelection(0);
                }
                if (count == 0) {
                    Answer ans = new Answer("", "धन्यवाद", "", "", "0", "", "", "");
                    answers.add(ans);
                    RESULT_AWAITED = Boolean.FALSE;
                    chatArrayAdapter.notifyDataSetChanged();
                    sendChatMessagehint("धन्यवाद");
                    sendChatMessagehint("पुनः आरंभ करें");
                    answers.clear();
                    Option option = new Option();

                    option.setToken(data.getData()[0].getOption().getToken());
                    Answer[] answers = new Answer[2];
                    answers[0] = new Answer();
                    answers[1] = new Answer();
                    answers[0].setQuestion("हाँ");
                    answers[0].setId("4");
                    answers[0].setParent_id("3");
                    answers[0].setIs_child("1");
                    answers[0].setState_id("0");
                    answers[0].setDistrict_id("0");
                    answers[0].setBypass("0");
                    answers[1].setQuestion("नहीं");
                    answers[1].setIs_child("1");
                    answers[1].setAppExit(Boolean.TRUE);
                    option.setAnswer(answers);
                    optionAdapter = new OptionAdapter(this, option, chatArrayAdapter, optionslist, this, String.valueOf(latitude), String.valueOf(longitude));
                    optionslist.setAdapter(optionAdapter);
                    optionslist.setSelection(0);
                    optionslist.setVisibility(View.VISIBLE);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public <T> void onRequestFailed(BaseTask<T> listener, Constants.RequestParam requestParam) {


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
