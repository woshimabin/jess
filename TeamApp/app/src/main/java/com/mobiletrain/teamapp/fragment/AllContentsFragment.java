package com.mobiletrain.teamapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiletrain.teamapp.R;
import com.mobiletrain.teamapp.adapter.VpFragmentAdapter;
import com.mobiletrain.teamapp.javabean.TabBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by qcf on 2016/11/7.
 */

@SuppressLint("ValidFragment")
public class AllContentsFragment extends Fragment {


    private static final String TAG = "test";
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    private TabBean.MenusBean menuEssence;
    private VpFragmentAdapter vpFragmentAdapter;
    private View view;


    @SuppressLint("ValidFragment")
    public AllContentsFragment(TabBean.MenusBean menuEssence) {
        this.menuEssence = menuEssence;
        List<TabBean.MenusBean.SubmenusBean> submenus = menuEssence.getSubmenus();
        for (int i = 0; i < submenus.size(); i++) {
            String name = submenus.get(i).getName();
            String url = submenus.get(i).getUrl() + "budejie-android-6.5.8/0-20.json?market=tencentyingyongbao&ver=6.5.8&visiting=&os=5.1&appname=baisibudejie&client=android&udid=862644034204067&mac=a4%3A44%3Ad1%3A81%3A7b%3A5f";
            titles.add(name);
            ContentFragment contentFragment = new ContentFragment(url);
            fragments.add(contentFragment);

            /**
             * 对第二个Fragment的数据初始化工作进行延时，避免刚进入程序时界面卡死
             */
            if (i == 1) {
                contentFragment.setDataInitDelayMillis(1000);
            } else {
                contentFragment.setDataInitDelayMillis(0);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        long start = System.currentTimeMillis();

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_all_contents, container, false);
            ViewPager vpContent = (ViewPager) view.findViewById(R.id.vpContent);
            TabLayout tablayout = (TabLayout) view.findViewById(R.id.tablayout);

            FragmentManager support = getChildFragmentManager();
            Log.e(TAG, "onCreateView: vpFragment ===" + vpContent + "\n" + support + "\n" + fragments + "\n" + titles);
            vpFragmentAdapter = new VpFragmentAdapter(support, fragments, titles);

            vpContent.setAdapter(vpFragmentAdapter);
        vpContent.setOffscreenPageLimit(fragments.size());
            tablayout.setupWithViewPager(vpContent);
        }
//        if (view!=null){
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent!=null){
//                parent.removeView(view);
//            }
//        }

        ButterKnife.bind(this, view);
        long end = System.currentTimeMillis();
        Log.e(TAG, "执行时长：" + (end - start) + "毫秒");
        return view;
    }
}
