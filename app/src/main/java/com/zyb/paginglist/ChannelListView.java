package com.zyb.paginglist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zyb.paginglist.ChannelList.IChannelListView;

/**
 * Created by Sky000 on 2018/11/23.
 */

public class ChannelListView extends RecyclerView implements IChannelListView{

    public ChannelListView(Context context) {
        super(context);
    }


    @Override
    public void showChannelList() {

    }

    @Override
    public void showLoading() {

    }
}
