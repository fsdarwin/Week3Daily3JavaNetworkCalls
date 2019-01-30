package com.example.week3daily3javanetworkcalls;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.week3daily3javanetworkcalls.model.User.Result;

public class FragmentHolder extends AppCompatActivity {

    //DECLARE variables
    Intent receivedIntent;
    FragmentManager fm;
    DisplayUser showUserFrag;
    Result user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

        //PULL intent sent from MainActivity
        receivedIntent = getIntent();
        user = receivedIntent.getParcelableExtra("user");

        //BUNDLE the data to send to Fragment
        Bundle data = new Bundle();//create bundle instance
        data.putParcelable("user", user);
        showUserFrag = new DisplayUser();
        showUserFrag.setArguments(data);

        //SET up Fragment manager and commit replace
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragHolder, showUserFrag).commit();

    }
    //SEND back to MainActivity
    public void onClick(View view) {
        Intent goBack = new Intent(this, MainActivity.class);
        startActivity(goBack);
    }
}
