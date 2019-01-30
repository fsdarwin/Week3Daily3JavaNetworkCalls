package com.example.week3daily3javanetworkcalls.events;

import com.example.week3daily3javanetworkcalls.model.User.UserResponse;

import java.util.ArrayList;

public class UserResponseEvent {
    private ArrayList<UserResponse> userResponse;

    public ArrayList<UserResponse> getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(ArrayList<UserResponse> userResponse) {
        this.userResponse = userResponse;
    }

    public UserResponseEvent(ArrayList<UserResponse> userResponse) {
        this.userResponse = userResponse;
    }
}

