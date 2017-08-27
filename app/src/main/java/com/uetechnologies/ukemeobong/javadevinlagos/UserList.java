package com.uetechnologies.ukemeobong.javadevinlagos;

/**
 * Created by Ukemeobong on 8/13/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class UserList {

    @SerializedName("items")
    @Expose
    private List<UserInformation> items = null;

    //Getters and Setters for the above list<UserInfromation> of items
    public List<UserInformation> getItems() {
        return items;
    }

    public void setItems(List<UserInformation> items) {
        this.items = items;
    }



}
