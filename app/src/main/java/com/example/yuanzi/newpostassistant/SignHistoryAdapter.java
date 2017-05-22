package com.example.yuanzi.newpostassistant;

/**
 * Created by yuanzi on 2017/5/22.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.yuanzi.newpostassistant.R.id.isget;


public class SignHistoryAdapter extends RecyclerView.Adapter<SignHistoryAdapter.ViewHolder>{
    private List<SignHistory> mMessageList;
    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView text;

        public ViewHolder(View view){
            super(view);
            text = (TextView) view.findViewById(R.id.sign_history);

        }
    }
    public SignHistoryAdapter(List<SignHistory> messageList){
        mMessageList = messageList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sign_history,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        SignHistory signHistory = mMessageList.get(position);
        holder.text.setText(signHistory.getText());
    }
    @Override
    public int getItemCount(){
        return  mMessageList.size();
    }
}
