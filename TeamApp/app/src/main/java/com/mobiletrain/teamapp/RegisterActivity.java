package com.mobiletrain.teamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tvDelete)
    TextView tvDelete;
    @BindView(R.id.btRegister)
    Button btRegister;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_secret)
    EditText etSecret;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btModify)
    Button btModify;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.weibo)
    ImageView weibo;
    @BindView(R.id.txweibo)
    ImageView txweibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                loginByEscrow(qq);

            }
        });

        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                loginByEscrow(sina);
            }
        });

        txweibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                loginByEscrow(wechat);
            }
        });

    }

    private void loginByEscrow(Platform platform) {
        if (platform.isAuthValid()) {
            String userId = platform.getDb().getUserId();
            Log.e("sharedemo", "platformLogin:UserName= "+platform.getDb().getUserName());

            if (userId != null) {
                Toast.makeText(RegisterActivity.this, "使用第三方平台登录成功", Toast.LENGTH_SHORT).show();
                //      login(userId,"platform");//使用第三方平台账号登陆自己的服务器
            }
        }

        else{
            Toast.makeText(RegisterActivity.this, "未获得第三方平台登录授权", Toast.LENGTH_SHORT).show();
            platform.authorize();//引导用户进行授权
        }

        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });

        platform.showUser(null);
    }


}
