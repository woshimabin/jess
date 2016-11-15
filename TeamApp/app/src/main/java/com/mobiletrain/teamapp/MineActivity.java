package com.mobiletrain.teamapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobiletrain.teamapp.adapter.MyListAdapter;
import com.mobiletrain.teamapp.adapter.RegisterLvAdapter;
import com.mobiletrain.teamapp.customview.ExListView;
import com.mobiletrain.teamapp.fragment.MyListFragmentI;
import com.mobiletrain.teamapp.fragment.MyListFragmentII;
import com.mobiletrain.teamapp.javabean.RegisterBean;
import com.mobiletrain.teamapp.model.RegisterLv;
import com.mobiletrain.teamapp.util.HttpUtil;
import com.mobiletrain.teamapp.util.JsonUtil;
import com.mobiletrain.teamapp.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MineActivity extends AppCompatActivity  {

    private static final String TAG = "test";
    private static final int MSG_CONTNET_MINE_JSON_GET = 300;
    @BindView(R.id.ll_my_touxiang_iv)
    ImageView llMyTouxiangIv;
    @BindView(R.id.ll_my_touxiang_tv)
    TextView llMyTouxiangTv;
    @BindView(R.id.my_touxiang_switch)
    TextView myTouxiangSwitch;
    @BindView(R.id.ll_my_touxiang)
    RelativeLayout llMyTouxiang;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.lv)
    ExListView lv;
    @BindView(R.id.pbload)
    ProgressBar pbload;

    List<Fragment> fragmens = new ArrayList<>();
    List<RegisterLv> data = new ArrayList<>();


    private MyListAdapter myListAdapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CONTNET_MINE_JSON_GET:
                    String json = (String) msg.obj;
                    RegisterBean registerBean = JsonUtil.parseToRegisterBean(json);
                    if (registerBean != null) {
                        List<RegisterBean.RecTagsBean> list = registerBean.getRec_tags();
                        for (int i = 0; i < list.size(); i++) {
                            String image = list.get(i).getImage_list();
                            Log.d(TAG, "handleMessage: " + image);
                            String name = list.get(i).getTheme_name();
                            Log.d(TAG, "handleMessage: " + name);
                            String sub_number = "" + list.get(i).getSub_number();
                            Log.d(TAG, "handleMessage: " + sub_number);
                            String post_num = "" + list.get(i).getPost_num();
                            Log.d(TAG, "handleMessage: " + post_num);
                            RegisterLv registerLv = new RegisterLv(image, name, sub_number, post_num);
                            data.add(registerLv);
                        }
                        registerLvAdapter.notifyDataSetChanged();
                        pbload.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };
    private String json;
    private RegisterLvAdapter registerLvAdapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);

        fragmens.add(new MyListFragmentI());
        fragmens.add(new MyListFragmentII());

        myListAdapter = new MyListAdapter(getSupportFragmentManager(), fragmens);
        vp.setAdapter(myListAdapter);

        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                url = "http://d.api.budejie.com/tag/subscribe/budejie-android-6.5.8.json";
                HttpUtil.okHttpAsyncGet(MineActivity.this, url, handler,MSG_CONTNET_MINE_JSON_GET);
            }
        });
        registerLvAdapter = new RegisterLvAdapter(MineActivity.this, data);
        lv.setAdapter(registerLvAdapter);

    }



    public void loginByWeChat(View view) {
        startActivity(new Intent(this,RegisterActivity.class));

    }

    @OnClick(R.id.ll_my_touxiang_iv)
    public void onClick(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

}
