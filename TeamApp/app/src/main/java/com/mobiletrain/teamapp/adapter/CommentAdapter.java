package com.mobiletrain.teamapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobiletrain.teamapp.R;
import com.mobiletrain.teamapp.javabean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016/11/4.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {


    private static final String TAG = "test";
    private Context context;
    private View view;
    private final LayoutInflater inflater;
    private List<CommentBean.NormalBean.ListBean> list = new ArrayList<>();

    public CommentAdapter(Context context, List<CommentBean.NormalBean.ListBean> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
        Log.e(TAG, "CommentAdapter:list: " + list );

    }

    public void setList(List<CommentBean.NormalBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_comment, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (list.size() > 0) {
            String content = list.get(position).getContent();
            String imageUrl = list.get(position).getUser().getProfile_image();
            String sex = list.get(position).getUser().getSex();
            String username = list.get(position).getUser().getUsername();

            holder.ivUser.setImageURI(Uri.parse(imageUrl));
            holder.tvInfo.setText(content);
            holder.tvUsername.setText(username);

            if (sex.equals("m")) {
                holder.ivSex.setImageResource(R.mipmap.men);
            } else {
                holder.ivSex.setImageResource(R.mipmap.women);
            }
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
            return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user)
        SimpleDraweeView ivUser;
        @BindView(R.id.iv_sex)
        ImageView ivSex;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.btn)
        Button btn;
        @BindView(R.id.tv_info)
        TextView tvInfo;

        Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
