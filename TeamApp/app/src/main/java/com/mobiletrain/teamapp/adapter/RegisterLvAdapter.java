package com.mobiletrain.teamapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiletrain.teamapp.R;
import com.mobiletrain.teamapp.model.RegisterLv;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jay on 2016/11/5.
 */
public class RegisterLvAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    List<RegisterLv> list;
    Context context;

    public RegisterLvAdapter(Context context, List<RegisterLv> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Holder holder ;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, viewGroup, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        RegisterLv registerLv = list.get(position);
        String image = registerLv.getImage();
        //holder.ivRegister.setImageURI(Uri.parse(image));
        Picasso.with(context)
                .load(image)
                .error(R.mipmap.icon_new)
                .placeholder(R.mipmap.download)
                .into(holder.ivRegister);

        holder.tvTitle.setText(registerLv.getName());
        holder.tvInfo.setText(registerLv.getSub_number()+"万订阅 | "+"总贴数"+registerLv.getPost_num());

        return convertView;
    }

    static class Holder {
        @BindView(R.id.iv_register)
        ImageView ivRegister;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_attention)
        TextView tvAttention;

        Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
