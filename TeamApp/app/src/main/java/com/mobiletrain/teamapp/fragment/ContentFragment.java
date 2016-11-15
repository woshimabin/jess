package com.mobiletrain.teamapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mobiletrain.teamapp.R;
import com.mobiletrain.teamapp.adapter.ContentFragmentLVAdapter;
import com.mobiletrain.teamapp.javabean.AllContentBean;
import com.mobiletrain.teamapp.javabean.GifsBean;
import com.mobiletrain.teamapp.javabean.ImagesBean;
import com.mobiletrain.teamapp.model.Content;
import com.mobiletrain.teamapp.util.HttpUtil;
import com.mobiletrain.teamapp.util.JsonUtil;
import com.mobiletrain.teamapp.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qcf on 2016/10/31.
 */

@SuppressLint("ValidFragment")
public class ContentFragment extends Fragment {

    private static final String TAG = "test ContentFragment";
    private static final int MSG_ALL_CONTNET_JSON_GET = 486;
    List<Content> contents = new ArrayList<>();

    private String url;
    private AllContentBean allContentBean;
    private ImagesBean imagesBean;
    private GifsBean gifsBean;
    private List<AllContentBean.ListBean> list;
    private int dataInitDelayMillis;


    @SuppressLint("ValidFragment")
    public ContentFragment(String url) {
        this.url = url;
    }

    private static final int MSG_BEANS_PARSE_OK = 199;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ALL_CONTNET_JSON_GET:
                    final String json = (String) msg.obj;
                    ThreadUtil.execute(new Runnable() {
                        @Override
                        public void run() {
                            allContentBean = JsonUtil.parseToAllContentBean(json);
                            imagesBean = JsonUtil.parseToImagesBean(json);
                            gifsBean = JsonUtil.parseToGifsBean(json);

                            handler.sendEmptyMessage(MSG_BEANS_PARSE_OK);
                        }
                    });
                    break;

                case MSG_BEANS_PARSE_OK:
                    Log.d(TAG, "handleMessage:MSG_BEANS_PARSE_OK");
                    if (allContentBean != null) {
                        list = allContentBean.getList();
                        for (int i = 0; i < list.size(); i++) {
                            String uCover = list.get(i).getU().getHeader().get(0);//头像
                            String name = list.get(i).getU().getName();//用户名
                            boolean is_v = list.get(i).getU().isIs_v();//是否v
                            String passtime = list.get(i).getPasstime();//日期
                            String text = list.get(i).getText();//内容详情
                            String share_url = list.get(i).getShare_url();//分享详情
                            String up = list.get(i).getUp();//点赞次数
                            int down = list.get(i).getDown();//踩次数
                            int forward = list.get(i).getForward();//分享次数
                            String comment = list.get(i).getComment();//评论次数
                            String loadUrl = "";
                            int duration = 0;
                            int playcount = 0;
                            int width = 0;
                            int height = 0;

                            String vedioCover = "";
                            if (list.get(i).getVideo() != null) {

                                String playUrl = list.get(i).getVideo().getDownload().get(0);//播放地址
                                loadUrl = playUrl;
                                duration = list.get(i).getVideo().getDuration();//播放时长、要转格式
                                playcount = list.get(i).getVideo().getPlaycount();//播放次数
                                width = list.get(i).getVideo().getWidth();
                                height = list.get(i).getVideo().getHeight();
                                vedioCover = list.get(i).getVideo().getThumbnail().get(0);
                            }
                            if (imagesBean.getList().get(i).getImage() != null) {
                                String imageUrl = imagesBean.getList().get(i).getImage().getBig().get(0);
                                loadUrl = imageUrl;
//                                Log.e(TAG, "handleMessage: " + loadUrl);
                                width = imagesBean.getList().get(i).getImage().getWidth();
                                height = imagesBean.getList().get(i).getImage().getHeight();
                            }
                            if (gifsBean.getList().get(i).getGif() != null) {
                                String gifUrl = gifsBean.getList().get(i).getGif().getImages().get(0);
                                loadUrl = gifUrl;
                                width = gifsBean.getList().get(i).getGif().getWidth();
                                height = gifsBean.getList().get(i).getGif().getHeight();
                            }

                            String type = list.get(i).getType();//类型
                            String id = list.get(i).getId();//条目ID

                            Content content = new Content(uCover, name, is_v, passtime, text, share_url, up, down, forward, comment, loadUrl, duration, playcount, type, id, width, height, vedioCover);
                            contents.add(content);
                        }
                        contentFragmentLVAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
    @BindView(R.id.ptrlv)
    PullToRefreshListView ptrlv;
    private View view;
    private ContentFragmentLVAdapter contentFragmentLVAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_content, container, false);
            ButterKnife.bind(this, view);

            ptrlv.setMode(PullToRefreshBase.Mode.BOTH);
            ILoadingLayout loadingLayoutProxy = ptrlv.getLoadingLayoutProxy();
            loadingLayoutProxy.setPullLabel("下拉刷新...");
            loadingLayoutProxy.setReleaseLabel("放开你的手,让我来...");
            loadingLayoutProxy.setRefreshingLabel("正在刷新,请稍等...");
            loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.reflash));

            ptrlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           ThreadUtil.execute(new Runnable() {
                               @Override
                               public void run() {
                                       contents.clear();
                                   HttpUtil.okHttpAsyncGet(getActivity(), url, handler,MSG_ALL_CONTNET_JSON_GET);
                               }
                           });
                           ptrlv.onRefreshComplete();
                       }
                   },3000);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ThreadUtil.execute(new Runnable() {
                                @Override
                                public void run() {
                                    HttpUtil.okHttpAsyncGet(getActivity(), url, handler,MSG_ALL_CONTNET_JSON_GET  );
                                }
                            });
                            ptrlv.onRefreshComplete();
                        }
                    }, 3000);
                }
            });
        }
        contentFragmentLVAdapter = new ContentFragmentLVAdapter(getActivity(), contents);
        ptrlv.setAdapter(contentFragmentLVAdapter);
        return view;
    }

    /**
     * （延时）初始化数据
     */
    private void initData() {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(dataInitDelayMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                HttpUtil.okHttpAsyncGet(getActivity(), url, handler,MSG_ALL_CONTNET_JSON_GET);
            }
        });
    }

    public void setDataInitDelayMillis(int dataInitDelayMillis) {
        this.dataInitDelayMillis = dataInitDelayMillis;
    }
}
