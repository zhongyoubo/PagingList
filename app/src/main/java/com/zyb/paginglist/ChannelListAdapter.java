package com.zyb.paginglist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Sky000 on 2018/11/23.
 * 创建自定义的RecyclerView Adapter的标准步骤：
 * 1.创建自定义的Adapter：ChannelListAdapter
 * 2.创建自定义的ViewHolder：在ChannelListAdapter中创建一个静态内部类，继承RecyclerView.ViewHolder
 * 3.实现RecyclerView Adapter的三个方法，
 *      onCreateViewHolder：映射Item Layout Id，创建ViewHolder并返回（此ViewHodler为自定义的ChannelListAdapter.ViewHolder类型）
 *      onBindViewHolder：为ViewHolder holder指定数据
 *      getItemCount：返回Item的个数
 *
 *
 */

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ViewHolder> {
    List<String> mListData;

    public ChannelListAdapter(List<String> data){
        mListData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(mListData.get(position));
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage = null;
        TextView mText = null;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.channel_num);
        }
    }
}
