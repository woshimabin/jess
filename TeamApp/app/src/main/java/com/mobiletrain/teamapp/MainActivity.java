package com.mobiletrain.teamapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobiletrain.teamapp.fragment.AllContentsFragment;
import com.mobiletrain.teamapp.javabean.TabBean;
import com.mobiletrain.teamapp.util.HttpUtil;
import com.mobiletrain.teamapp.util.JsonUtil;
import com.mobiletrain.teamapp.util.ThreadUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test MainActivity";

    private static final int MSG_CONTENT_JSON_GOT = 100;
    @BindView(R.id.rg_main_rb1)
    RadioButton rgMainRb1;
    @BindView(R.id.rg_main_rb2)
    RadioButton rgMainRb2;
    @BindView(R.id.rg_main_rb3)
    Button rgMainRb3;
    @BindView(R.id.rg_main_rb4)
    Button rgMainRb4;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    @BindView(R.id.main_tv)
    TextView mainTv;
    @BindView(R.id.rg_main_btnmid)
    ImageView rgMainBtnmid;
    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.pbMask)
    ProgressBar pbMask;

//    private List<Fragment> fragments = new ArrayList<>();

    private static final int MSG_TAB_BEAN_PARSE_OK = 99;
    private Fragment currentFragment = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CONTENT_JSON_GOT:
                    final String json = (String) msg.obj;
                    ThreadUtil.execute(new Runnable() {
                        @Override
                        public void run() {
                            TabBean tabBean = JsonUtil.parseTabBean(json);
                            if (tabBean != null) {
                                List<TabBean.MenusBean> menus = tabBean.getMenus();
                                menuEssence = menus.get(0);//精华
                                menusNewsPost = menus.get(1);//新帖
                                Log.e(TAG, "handleMessage: " + menuEssence.getSubmenus().size());
                                Log.e(TAG, "handleMessage: " + menusNewsPost.getSubmenus().size());
                            }

                            handler.sendEmptyMessageDelayed(MSG_TAB_BEAN_PARSE_OK,1000);
                        }
                    });
                    break;

                case MSG_TAB_BEAN_PARSE_OK:
                    Log.d(TAG, "handleMessage:MSG_TAB_BEAN_PARSE_OK");
                    showEssence();
                    pbMask.setVisibility(View.GONE);
                    break;
            }
        }
    };

    private FragmentManager fm;
    public String url = "http://s.budejie.com/public/list-appbar/budejie-android-6.5.8/?market=tencentyingyongbao&ver=6.5.8&visiting=&os=5.1&appname=baisibudejie&client=android&udid=862644034204067&mac=a4%3A44%3Ad1%3A81%3A7b%3A5f";
    private AllContentsFragment allContentsFragmentI;
    private AllContentsFragment allContentsFragmentII;
    private TabBean.MenusBean menuEssence;
    private TabBean.MenusBean menusNewsPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mlife", "onCreate: ");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/mengziti.ttf");
        mainTv.setTypeface(typeface);

        fm = getSupportFragmentManager();
        initRadioGroup();

        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                HttpUtil.okHttpAsyncGet(MainActivity.this, url, handler,MSG_CONTENT_JSON_GOT);
            }
        });
    }

    private void initRadioGroup() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_main_rb1:
                        //fm.beginTransaction().replace(R.id.flContent, allContentsFragmentI).commit();
//                        fm.beginTransaction().show(allContentsFragmentI).hide(allContentsFragmentII).commit();
                        showEssence();
                        break;

                    case R.id.rg_main_rb2:
//                      fm.beginTransaction().show(allContentsFragmentII).hide(allContentsFragmentI).commit();
                        //fm.beginTransaction().replace(R.id.flContent, allContentsFragmentII).commit();
                        showPost();
                        break;
                }
            }
        });
    }

    /**
     * 显示最新
     */
    private void showPost() {
        if (allContentsFragmentII != null) {
            showFragment(allContentsFragmentII);
            return;
        }

        if (menusNewsPost != null) {
            allContentsFragmentII = new AllContentsFragment(menusNewsPost);
            showFragment(allContentsFragmentII);
        } else {
            Log.e(TAG, "menusNewsPost尚未初始化");
        }
    }

    /**
     * 显示精华
     */
    private void showEssence() {
        if (allContentsFragmentI != null) {
            showFragment(allContentsFragmentI);
            return;
        }

        if (menuEssence != null) {
            allContentsFragmentI = new AllContentsFragment(menuEssence);
            showFragment(allContentsFragmentI);
        } else {
            Log.e(TAG, "menuEssence尚未初始化");
        }
    }

    public void switchToWrite(View view) {
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);

    }

    public void switchMine(View view) {
        startActivity(new Intent(MainActivity.this, MineActivity.class));
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("mlife", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("mlife", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mlife", "onDestroy: ");
    }


    public void switchAttention(View view) {
        startActivity(new Intent(MainActivity.this, guanzhu_Activity.class));
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(R.id.flContent, fragment);
        }
        if (currentFragment != null && currentFragment != fragment) {
            ft.hide(currentFragment);
        }
        ft.show(fragment);
        ft.commit();

        currentFragment = fragment;
    }

}
