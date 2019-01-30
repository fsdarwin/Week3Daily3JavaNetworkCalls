package com.example.week3daily3javanetworkcalls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.week3daily3javanetworkcalls.model.User.Result;
import com.example.week3daily3javanetworkcalls.model.datasource.httpUrlConnection.HttpUrlConnTask;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //DECLARE class variables
    public static final String TAG = "FRANK: ";
    ArrayList<Result> resultArrayList;
    RvAdapter rvAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CALL API connection task
        HttpUrlConnTask httpUrlConnTask = new HttpUrlConnTask();
        httpUrlConnTask.execute();

        //BEGIN set up of RecyclerView
        recyclerView = findViewById(R.id.rvMainRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    //RECEIVE arrayList from eventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<Result> event) {
        if (event != null) {
            resultArrayList = event;
            //FINISH setting up RV Adapter
            rvAdapter = new RvAdapter(resultArrayList);
            recyclerView.setAdapter(rvAdapter);
        }
    }
}
