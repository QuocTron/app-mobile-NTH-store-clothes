package com.group7.appsellclothes.response;

import com.google.gson.annotations.SerializedName;
import com.group7.appsellclothes.model.User;

import java.util.List;

public class ListUser {
    public ListUser() {
    }

    public ListUser(List<com.group7.appsellclothes.model.User> users) {
        this.users = users;
    }

    public List<com.group7.appsellclothes.model.User> getUsers() {
        return users;
    }

    public void setUsers(List<com.group7.appsellclothes.model.User> users) {
        this.users = users;
    }

    @SerializedName("users")
    private List<User> users;

    @Override
    public String toString() {
        return "ListUser{" +
                "users=" + users +
                '}';
    }
}
