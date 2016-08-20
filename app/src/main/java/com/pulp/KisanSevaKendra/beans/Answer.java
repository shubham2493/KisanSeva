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

/**
 * Created by shubham on 02/08/16.
 */
public class Answer {
    private String id;

    private String is_child;

    private String is_userinput;

    private String question;

    private String parent_id;

    private String state_id;
    private String district_id;
    private String address;

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getBypass() {
        return bypass;
    }

    public void setBypass(String bypass) {
        this.bypass = bypass;
    }

    private String bypass;



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIs_child ()
    {
        return is_child;
    }

    public void setIs_child (String is_child)
    {
        this.is_child = is_child;
    }

    public String getIs_userinput ()
    {
        return is_userinput;
    }

    public void setIs_userinput (String is_userinput)
    {
        this.is_userinput = is_userinput;
    }

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getParent_id ()
    {
        return parent_id;
    }

    public void setParent_id (String parent_id)
    {
        this.parent_id = parent_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", is_child = "+is_child+", is_userinput = "+is_userinput+", question = "+question+", parent_id = "+parent_id+"]";
    }

    public Answer() {

    }

    public Answer(String id,String question,String is_child,String parent_id,String is_userinput,String state_id,String district_id,String bypass)
    {
        this.id=id;
        this.question=question;
        this.is_child=is_child;
        this.parent_id=parent_id;
        this.is_userinput=is_userinput;

        this.state_id=state_id;
        this.district_id=district_id;
        this.bypass=bypass;


    }

    private boolean appExit = Boolean.FALSE;

    public boolean isAppExit() {
        return appExit;
    }

    public void setAppExit(boolean appExit) {
        this.appExit = appExit;
    }
}

