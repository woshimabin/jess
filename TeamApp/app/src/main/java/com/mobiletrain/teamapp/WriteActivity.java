package com.mobiletrain.teamapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btn_write_i)
    Button btnWriteI;
    @BindView(R.id.btn_write_ii)
    Button btnWriteIi;
    @BindView(R.id.btn_write_iii)
    Button btnWriteIii;
    @BindView(R.id.btn_write_iv)
    Button btnWriteIv;
    @BindView(R.id.btn_write_v)
    Button btnWriteV;
    @BindView(R.id.btn_write_vi)
    Button btnWriteVi;

    private Handler handler = new Handler(){};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);

        TranslateAnimation animationi = createTransYAnim(-1000,0,false,0,new OvershootInterpolator());
        TranslateAnimation animationii = createTransYAnim(-1000,0,false,100,new OvershootInterpolator());
        TranslateAnimation animationiii = createTransYAnim(-1000,0,false,50,new OvershootInterpolator());
        TranslateAnimation animationiv = createTransYAnim(-1000,0,false,0,new OvershootInterpolator());
        TranslateAnimation animationv = createTransYAnim(-1000,0,false,100,new OvershootInterpolator());
        TranslateAnimation animationvi = createTransYAnim(-1000,0,false,50,new OvershootInterpolator());
        btnWriteI.startAnimation(animationi);
        btnWriteIi.startAnimation(animationii);
        btnWriteIii.startAnimation(animationiii);
        btnWriteIv.startAnimation(animationiv);
        btnWriteV.startAnimation(animationv);
        btnWriteVi.startAnimation(animationvi);

        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "fonts/mengziti.ttf");
        tvTitle.setTypeface(fromAsset);


    }

    public void cancelAll(View view) {
        view.setVisibility(View.GONE);
        TranslateAnimation animationi = createTransYAnim(0,1000,true,0,new AnticipateInterpolator());
        TranslateAnimation animationii = createTransYAnim(0,1000,true,100,new AnticipateInterpolator());
        TranslateAnimation animationiii = createTransYAnim(0,1000,true,50,new AnticipateInterpolator());
        TranslateAnimation animationiv = createTransYAnim(0,1000,true,0,new AnticipateInterpolator());
        TranslateAnimation animationv = createTransYAnim(0,1000,true,100,new AnticipateInterpolator());
        TranslateAnimation animationvi =createTransYAnim(0,1000,true,50,new AnticipateInterpolator());
        btnWriteI.startAnimation(animationi);
        btnWriteIi.startAnimation(animationii);
        btnWriteIii.startAnimation(animationiii);
        btnWriteIv.startAnimation(animationiv);
        btnWriteV.startAnimation(animationv);
        btnWriteVi.startAnimation(animationvi);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                WriteActivity.this.finish();
            }
        },2000);

    }

    private TranslateAnimation createTransYAnim(int yStart,int yEnd,boolean fill, long offset, Interpolator interpolator){
        TranslateAnimation animation = new TranslateAnimation(0, 0, yStart, yEnd);
        animation.setDuration(2000);
        animation.setFillAfter(fill);
        animation.setStartOffset(offset);
        animation.setInterpolator(interpolator);
        return animation;
    }
}
