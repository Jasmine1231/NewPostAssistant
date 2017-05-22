package com.example.yuanzi.newpostassistant;

/**
 * Created by yuanzi on 2017/5/21.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by yz on 2017/3/11.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> mMessageList;
    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView message_time;
        TextView d_company;
        TextView d_phone_number;
        TextView d_isget;

        public ViewHolder(View view){
            super(view);
            message_time = (TextView) view.findViewById(R.id.message_time);
            d_company = (TextView) view.findViewById(R.id.company);
            d_phone_number = (TextView) view.findViewById(R.id.phone_number);
            d_isget = (TextView) view.findViewById(R.id.isget);
        }
    }
    public MessageAdapter(List<Message> messageList){
        mMessageList = messageList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){

        Message message = mMessageList.get(position);
        holder.message_time.setText(message.getMessage_time());
        holder.d_company.setText(message.getD_company());
        holder.d_phone_number.setText(message.getD_phone_number());
        holder.d_isget.setText(message.getD_isget());
        holder.d_isget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.d_isget.getText().equals("未签收")) {
                    holder.d_isget.setText("已签收");
                    holder.d_isget.setTextColor(Color.rgb(0, 255, 0));
                }
                else {
                    holder.d_isget.setText("未签收");
                    holder.d_isget.setTextColor(Color.rgb(143,137,143));
                }

            }
        });
    }
    @Override
    public int getItemCount(){
        return  mMessageList.size();
    }

}

