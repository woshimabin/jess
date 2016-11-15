package com.mobiletrain.teamapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiletrain.teamapp.adapter.CommentAdapter;
import com.mobiletrain.teamapp.adapter.ContentFragmentLVAdapter;
import com.mobiletrain.teamapp.javabean.CommentBean;
import com.mobiletrain.teamapp.model.Content;
import com.mobiletrain.teamapp.util.HttpUtil;
import com.mobiletrain.teamapp.util.JsonUtil;
import com.mobiletrain.teamapp.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends AppCompatActivity {

    private static final String TAG = "test";
    private static final int MSG_COMMENT_JSON_GOT = 200;
    @BindView(R.id.comment_back_ll)
    LinearLayout commentBackLl;
    @BindView(R.id.comment_tv_more)
    TextView commentTvMore;
    @BindView(R.id.lvContent)
    ListView lvContent;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    private List<Content> contents = new ArrayList<>();
    private String ID_COMMENT;
    private List<CommentBean.NormalBean.ListBean> list = new ArrayList<>();
    private final int GOT_URL = 10;
    public Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case MSG_COMMENT_JSON_GOT:
                    if (msg.obj != null) {
                        String json = (String) msg.obj;
                        Log.e(TAG, "handleMessage: Comment:json" + json);
                        CommentBean commentBean = JsonUtil.parseToCommentBean(json);
                        Log.e(TAG, "handleMessage:commentBean " + commentBean);
                        if(commentBean!=null){
                            list = commentBean.getNormal().getList();
                            Log.e(TAG, "handleMessage: list" + list);
                        }
                        if (list!=null){
                            adapter1.setList(list);
                            adapter1.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(CommentActivity.this, "数据异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private CommentAdapter adapter1;
    private LinearLayoutManager manager;
    private String url;

    //"http://c.api.budejie.com/topic/comment_list/21679509/0/budejie-android-6.5.8/0-20.json"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Content content = (Content) bundle.getSerializable("content");
        contents.clear();
        contents.add(content);
        String id = content.getId();
        ID_COMMENT = id;
        getdata();

        ContentFragmentLVAdapter adapter = new ContentFragmentLVAdapter(this, contents);
        lvContent.setAdapter(adapter);

        adapter1 = new CommentAdapter(this, list);
        manager = new LinearLayoutManager(this);
        rvComment.setLayoutManager(manager);
        rvComment.setAdapter(adapter1);
    }

    private void getdata() {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                url = ("http://c.api.budejie.com/topic/comment_list/" + ID_COMMENT + "/0/budejie-android-6.5.8/0-20.json");
                HttpUtil.okHttpAsyncGet(CommentActivity.this, url, handler,MSG_COMMENT_JSON_GOT);

            }
        });
    }
}
