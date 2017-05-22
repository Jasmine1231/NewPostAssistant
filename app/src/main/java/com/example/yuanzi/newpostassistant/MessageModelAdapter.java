package com.example.yuanzi.newpostassistant;

/**
 * Created by yuanzi on 2017/5/22.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.example.yuanzi.newpostassistant.R.id.isget;


public class MessageModelAdapter extends RecyclerView.Adapter<MessageModelAdapter.ViewHolder> {
    private List<MessageModel> mMessageList;
    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView message_company;
        Button delete;
        //TextView message_huojia;


        public ViewHolder(View view){
            super(view);
            message_company = (TextView) view.findViewById(R.id.message_company);
            delete = (Button)view.findViewById(R.id.delete);
            //message_huojia = (TextView) view.findViewById(R.id.message_huojia);

        }
    }
    public MessageModelAdapter(List<MessageModel> messageList){
        mMessageList = messageList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_model,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        MessageModel messageModel = mMessageList.get(position);
        holder.message_company.setText(messageModel.getMessage_company());
        // holder.message_huojia.setText(messageModel.getMessage_huojia());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessageList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0,mMessageList.size());
            }
        });

    }
    @Override
    public int getItemCount(){
        return  mMessageList.size();
    }

}

