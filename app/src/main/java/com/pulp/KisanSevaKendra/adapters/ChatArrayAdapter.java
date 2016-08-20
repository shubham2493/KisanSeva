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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pulp.KisanSevaKendra.R;
import com.pulp.KisanSevaKendra.beans.ChatMessage;
import com.pulp.KisanSevaKendra.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham on 01/08/16.
 */
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {
    private TextView chatText;
    private ProgressBar progressBar;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private Context context;


    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (chatMessageObj.left) {
            row = inflater.inflate(R.layout.rightmessage, parent, false);
        } else {
            row = inflater.inflate(R.layout.leftmessage, parent, false);
        }
        chatText = (TextView) row.findViewById(R.id.msgr);
        chatText.setText(chatMessageObj.message);
        if ((chatMessageObj.left)) {
            progressBar = (ProgressBar) row.findViewById(R.id.progressBar);
            if (!MainActivity.RESULT_AWAITED) {
                if (chatMessageList.size() - 1 == position) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
        return row;
    }


}


