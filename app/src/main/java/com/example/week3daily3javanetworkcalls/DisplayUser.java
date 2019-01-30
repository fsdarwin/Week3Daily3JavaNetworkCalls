package com.example.week3daily3javanetworkcalls;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3daily3javanetworkcalls.model.User.Result;

import java.util.ArrayList;

import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayUser extends Fragment {
    //DECLARE variables for fields
    ImageView imgBig;
    TextView tvName;
    TextView tvDob;
    TextView tvAge;
    TextView tvLat;
    TextView tvLong;
    TextView tvStreet;
    TextView tvCity;
    TextView tvState;
    TextView tvPostCode;
    TextView tvTimeZone;

    public DisplayUser() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_user, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //INSTANTIATE variables with fields
        imgBig = view.findViewById(R.id.imgBig);
        tvName = view.findViewById(R.id.tvName);
        tvDob = view.findViewById(R.id.tvDob);
        tvAge = view.findViewById(R.id.tvAge);
        tvLat = view.findViewById(R.id.tvLat);
        tvLong = view.findViewById(R.id.tvLong);
        tvStreet = view.findViewById(R.id.tvStreet);
        tvCity = view.findViewById(R.id.tvCity);
        tvState = view.findViewById(R.id.tvState);
        tvPostCode = view.findViewById(R.id.tvPostCode);

        //GET data from setArguments bundle send from activity
        Bundle bundle = this.getArguments();
        Result passedUser = bundle.getParcelable("user");
        //INSTANTIATE variables with data
        String imgBig = passedUser.getPicture().getLarge();
        String name = passedUser.getName().getLast()
                + ", " + passedUser.getName().getFirst()
                + ", " + passedUser.getName().getTitle();
        String dob = passedUser.getDob().getDate();
        String age = passedUser.getDob().getAge().toString();
        String lat = passedUser.getLocation().getCoordinates().getLatitude();
        String longit = passedUser.getLocation().getCoordinates().getLongitude();
        String street = passedUser.getLocation().getStreet();
        String city = passedUser.getLocation().getCity();
        String state = passedUser.getLocation().getState();
        String postCode = passedUser.getLocation().getPostcode();
        //SEND data to be set in the TextView fields
        setUserDisplay(imgBig, name, dob, age, lat, longit, street, city, state, postCode);
    }
    //SET the data to the TextView Fields
    public void setUserDisplay(String bigImage,
                               String name,
                               String dob,
                               String age,
                               String lat,
                               String log,
                               String street,
                               String city,
                               String state,
                               String postCode) {
        Glide.with(getContext())
                .load(bigImage)
                .into(imgBig);
        tvName.setText(name);
        tvDob.setText(dob);
        tvAge.setText(age);
        tvLat.setText(lat);
        tvLong.setText(log);
        tvStreet.setText(street);
        tvCity.setText(city);
        tvState.setText(state);
        tvPostCode.setText(postCode);


    }
}
