package com.zyb.paginglist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.zyb.paginglist.Loading.LoadingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sky000 on 2018/11/23.
 * 1.RecyclerView标准实现步骤：
 *   创建Adapter
 *
 */

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChannelListView mChannelListView;
    ChannelListAdapter mChannelListAdapter;
    List<String> mChannelNumList;

    LoadingView mLoadingView;
    private boolean mIsLoading = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreat");
        setContentView(R.layout.activity_main);
        //mChannelListView = findViewById(R.id.channel_list_view);
        //mChannelNumList = new ArrayList<>();
        //mChannelListAdapter = new ChannelListAdapter(mChannelNumList);
        //mChannelListView.setAdapter(mChannelListAdapter);

        mLoadingView = findViewById(R.id.loading_view);

    }

    private void showLoadingView(){
        if (mLoadingView != null && !mIsLoading) {
            Log.d(TAG,"showLoadingView");
            mIsLoading = true;
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    private void hideLoadingView(){
        if(mLoadingView != null && mIsLoading){
            Log.d(TAG,"hideLoadingView");
            mIsLoading = false;
            mLoadingView.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG,"keyCode : "+keyCode);
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            {
                //mChannelListView.show();
                return true;
            }
            case KeyEvent.KEYCODE_DPAD_UP:
            {
                showLoadingView();
                return true;
            }
            case KeyEvent.KEYCODE_DPAD_DOWN:
            {
                hideLoadingView();
                return true;
            }
        }
        return true;
    }
}
