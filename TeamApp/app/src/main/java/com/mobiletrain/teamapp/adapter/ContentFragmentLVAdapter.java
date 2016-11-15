package com.mobiletrain.teamapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imageutils.BitmapUtil;
import com.mobiletrain.teamapp.CommentActivity;
import com.mobiletrain.teamapp.R;
import com.mobiletrain.teamapp.WebviewActivity;
import com.mobiletrain.teamapp.model.Content;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by qcf on 2016/10/31.
 */

public class ContentFragmentLVAdapter extends BaseAdapter {

    private static final String TAG = "test";
    private final LayoutInflater inflater;
    private final MediaPlayer mediaPlayer;
    private Context context;
    private List<Content> contents = new ArrayList<>();
    private int currentPlayingPosition = -1;
    private int widthPixels;
    private int heightPixels;
    private List<Map<String, Boolean>> flags = new ArrayList<>();

    private int currentPosition = -1;


    public ContentFragmentLVAdapter(Context context, List<Content> contents) {
        this.context = context;
        this.contents = contents;
        inflater = LayoutInflater.from(context);
        mediaPlayer = new MediaPlayer();
        ShareSDK.initSDK(context);
        Fresco.initialize(context);
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    boolean videoUpOrDown = false;
    boolean textUpOrDown = false;
    boolean imageUpOrDown = false;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_zuhe, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Content content = contents.get(position);
        final String type = content.getType();

        widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        heightPixels = context.getResources().getDisplayMetrics().heightPixels;

        if (type.equals("video")) {
            holder.cvRootVideo.setVisibility(View.VISIBLE);
            holder.cvRootImage.setVisibility(View.GONE);
            holder.cvRootText.setVisibility(View.GONE);
        } else if (type.equals("text")) {
            holder.cvRootText.setVisibility(View.VISIBLE);
            holder.cvRootVideo.setVisibility(View.GONE);
            holder.cvRootImage.setVisibility(View.GONE);
        } else {
            holder.cvRootImage.setVisibility(View.VISIBLE);
            holder.cvRootText.setVisibility(View.GONE);
            holder.cvRootVideo.setVisibility(View.GONE);
        }


        Map<String, Boolean> map = new HashMap<>();
        map.put("isUp", false);
        map.put("isDown", false);
        flags.add(map);

        if (type.equals("video")) {

            videoItemShow(position, holder, content);

        } else if (type.equals("text")) {

            textItemShow(position, holder, content);

        } else {//图片
            imageItemShow(position, holder, content);
        }


        return convertView;
    }

    private void imageItemShow(final int position, final ViewHolder holder, final Content content) {

        final String loadUrl = content.getLoadUrl();
        Log.e(TAG, "getView: " + loadUrl);
        String url = content.getuCover();
        final int width = content.getWidth();
        final int height = content.getHeight();
        /**
         * 设置展开全图
         */
        holder.llFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebviewActivity.class);
                intent.putExtra("loadUrl", loadUrl);
                context.startActivity(intent);
            }
        });


        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.sdvCoverImg.getLayoutParams();

        Log.e(TAG, "imageItemShow: imgsize:" + width + "/" + height);


        if (width < widthPixels) {
            layoutParams.width = widthPixels;
            layoutParams.height = (int) (height * ((float)widthPixels/width));
        } else {
            layoutParams.width = width;
            layoutParams.height = height;
        }

        Log.e(TAG, "imageItemShow: layoutParams" + layoutParams.width+"/"+layoutParams.height );

        /**
         * 图画根部局宽高
         */
        LinearLayout.LayoutParams layoutParamsllImg = (LinearLayout.LayoutParams) holder.llImg.getLayoutParams();
        layoutParamsllImg.height = layoutParams.height > 1980 ? 1980 : layoutParams.height;
        if (layoutParams.height > 1980) {
            holder.llFullScreen.setVisibility(View.VISIBLE);

        } else {
            holder.llFullScreen.setVisibility(View.GONE);
        }
        holder.llImg.setLayoutParams(layoutParamsllImg);


        holder.sdvCoverImg.setLayoutParams(layoutParams);

        holder.sdvCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageRequest request;
        if (height > 8000) {
            request = ImageRequestBuilder
                    .newBuilderWithSource(Uri.parse(loadUrl))
                    .setResizeOptions(new ResizeOptions(width, height, BitmapUtil.MAX_BITMAP_SIZE, 3f))
                    .build();
        } else {
            request = ImageRequestBuilder
                    .newBuilderWithSource(Uri.parse(loadUrl))
                    .build();
        }
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setImageRequest(request)
                .build();
        holder.sdvCoverImg.setController(controller);
        holder.sdvCoverImg.getHierarchy().setActualImageFocusPoint(new PointF(0.5f, 0f));

        Picasso.with(context).load(url)
                .fit()
                .centerInside()
                .noFade()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgUCover);

        holder.imgUName.setText(content.getName());
        holder.imgUPasstime.setText(content.getPasstime());
        holder.tvImage.setText(content.getText());
        final int up = Integer.parseInt(content.getUp());
        holder.rbtnImage1.setText(up + "");
        final int down = content.getDown();
        holder.rbtnImage2.setText(down + "");
        final int forward = content.getForward();
        holder.rbtnImage3.setText(forward + "");
        String comment = content.getComment();
        holder.rbtnImage4.setText(comment);

        Log.e(TAG, "imageItemShow: " + flags );
        final Boolean isUp = flags.get(position).get("isUp");
        Boolean isDown = flags.get(position).get("isDown");
        upOrDown(holder.rbtnImage1, holder.rbtnImage2, isUp, isDown);

        holder.rbtnImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                Map<String, Boolean> map = flags.get(currentPosition);
                Boolean isUp = map.get("isUp");
                Boolean isDown = map.get("isDown");
                toUp(holder.rbtnImage1, up, isUp, isDown);
                map.clear();
                map.put("isUp", true);
                map.put("isDown", false);
            }
        });
        holder.rbtnImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                Map<String, Boolean> map = flags.get(currentPosition);
                Boolean isUp = map.get("isUp");
                Boolean isDown = map.get("isDown");
                toDown(holder.rbtnImage2, down,isUp,isDown);
                map.clear();
                map.put("isUp", false);
                map.put("isDown", true);
            }
        });


        holder.rbtnImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

        //设置评论监听
        holder.rbtnImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CommentActivity.class);
                Content content1 = contents.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("content",content1);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }


    private void textItemShow(final int position, final ViewHolder holder, final Content content) {
        String url = content.getuCover();


        Picasso.with(context).load(url)
                .fit()
                .centerInside()
                .noFade()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivTextCover);
        holder.tvTextName.setText(content.getName());
        holder.tvTextPasstime.setText(content.getPasstime());
        holder.tvText.setText(content.getText());
        final int up = Integer.parseInt(content.getUp());
        holder.rbtnText1.setText(up + "");
        final int down = content.getDown();
        holder.rbtnImage2.setText(down + "");
        int forward = content.getForward();
        holder.rbtnImage3.setText(forward + "");
        String comment = content.getComment();
        holder.rbtnImage4.setText(comment);

        Boolean isUp = flags.get(position).get("isUp");
        Boolean isDown = flags.get(position).get("isDown");
        upOrDown(holder.rbtnText1, holder.rbtnText2, isUp, isDown);

        holder.rbtnText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                Map<String, Boolean> map = flags.get(currentPosition);
                Boolean isUp = map.get("isUp");
                Boolean isDown = map.get("isDown");
                toUp(holder.rbtnText1, up, isUp, isDown);
                map.clear();
                map.put("isUp", true);
                map.put("isDown", false);

            }
        });
        holder.rbtnText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                Map<String, Boolean> map = flags.get(currentPosition);
                Boolean isUp = map.get("isUp");
                Boolean isDown = map.get("isDown");
                toDown(holder.rbtnText2, down, isUp, isDown);
                map.clear();
                map.put("isUp", false);
                map.put("isDown", true);
            }
        });

        holder.rbtnText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

        //设置评论监听
        holder.rbtnText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CommentActivity.class);
                Content content1 = contents.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("content",content1);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private void videoItemShow(final int position, final ViewHolder holder, Content content) {
        String url = content.getuCover();
        int width = content.getWidth();
        int height = content.getHeight();
        String vedioCoverUrl = content.getVedioCoverUrl();


        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.sfvCoverVideo.getLayoutParams();
        lp.width = width;
        lp.height = height;
        Log.e(TAG, "videoItemShow: pixels" + width + "/" + height);

        if (width < widthPixels) {

            lp.width = widthPixels;
            lp.height = (int) ((float) height * ((float) widthPixels / (float) width));
            if (lp.height > 1000) {
                int thisH = lp.height;
                lp.height = 1000;
                lp.width = (int) (lp.width * 1000f / thisH);
            }
        }

        Log.e(TAG, "videoItemShow: lp.pixels" + lp.width + "/" + lp.height);
        holder.sfvCoverVideo.setLayoutParams(lp);
        holder.ivVideoCover.setLayoutParams(lp);
        Picasso.with(context).load(url)
                .fit()
                .centerInside()
                .noFade()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.videoUCover);
        holder.videoUName.setText(content.getName());
        holder.videoTvPasstime.setText(content.getPasstime());
        holder.tvVideo.setText(content.getText());
        final int up = Integer.parseInt(content.getUp());
        holder.rbtnVideo1.setText(up + "");
        final int down = content.getDown();
        holder.rbtnVideo2.setText(down + "");
        int forward = content.getForward();
        holder.rbtnVideo3.setText(forward + "");
        String comment = content.getComment();
        holder.rbtnVideo4.setText(comment);


        holder.rbtnVideo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

        //设置评论监听
        holder.rbtnVideo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CommentActivity.class);
                Content content1 = contents.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("content",content1);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        String videoUrl = content.getLoadUrl();

        /**
         * 设置封面
         */
        Glide.with(context)
                .load(Uri.parse(vedioCoverUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivVideoCover);

        /**
         * 点击封面，讲position赋值给currentposition，播放
         */
        holder.ivVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPlayingPosition = position;
                notifyDataSetChanged();
            }
        });

        if (position == currentPlayingPosition) {

            holder.ivVideoPlay.setVisibility(View.INVISIBLE);
            holder.sfvCoverVideo.setVisibility(View.VISIBLE);
            holder.ivVideoCover.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.VISIBLE);
            playVideo(videoUrl, holder);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    holder.progressBar.setVisibility(View.GONE);
                    holder.ivVideoPlay.setVisibility(View.VISIBLE);
                    holder.ivVideoCover.setVisibility(View.VISIBLE);
                    holder.sdvCoverImg.setVisibility(View.INVISIBLE);
                }
            });

            if (!mediaPlayer.isPlaying()) {
                holder.sfvCoverVideo.setAlpha(0);
            }
        } else {
            holder.ivVideoCover.setVisibility(View.VISIBLE);
            holder.ivVideoPlay.setVisibility(View.VISIBLE);
            holder.sfvCoverVideo.setVisibility(View.INVISIBLE);
        }

        //一旦播放当前播放条目离开屏幕，就停止播放，并将当前播放位置重新置为-1

        Integer formerPosition = (Integer) holder.sfvCoverVideo.getTag();//前世
        if (formerPosition != null && position != currentPlayingPosition && formerPosition == currentPlayingPosition) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            currentPlayingPosition = -1;
            notifyDataSetChanged();
        }
        holder.sfvCoverVideo.setTag(currentPlayingPosition);

        holder.sfvCoverVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    holder.ivVideoPauseStart.setVisibility(View.VISIBLE);
                } else {
                    mediaPlayer.start();
                    holder.ivVideoPauseStart.setVisibility(View.GONE);
                }
            }
        });

        holder.ivVideoPauseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {

                } else {
                    mediaPlayer.start();
                    holder.ivVideoPauseStart.setVisibility(View.GONE);
                }
            }
        });

        Boolean isUp = flags.get(position).get("isUp");
        Boolean isDown = flags.get(position).get("isDown");
        upOrDown(holder.rbtnVideo1, holder.rbtnVideo2, isUp, isDown);

        holder.rbtnVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                Map<String, Boolean> map = flags.get(currentPosition);
                Boolean isUp = map.get("isUp");
                Boolean isDown = map.get("isDown");
                toUp(holder.rbtnVideo1, up, isUp,isDown);
                map.clear();
                map.put("isUp", true);
                map.put("isDown", false);

            }
        });
        holder.rbtnVideo2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentPosition = position;
                Map<String, Boolean> map = flags.get(currentPosition);
                Boolean isUp = map.get("isUp");
                Boolean isDown = map.get("isDown");
                toDown(holder.rbtnVideo2, down,isUp, isDown);
                map.clear();
                map.put("isUp", false);
                map.put("isDown", true);
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");

        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");

        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "share_pic.jpg");//确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");

        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(context);
    }

    private void playVideo(String url, final ViewHolder holder) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(context, Uri.parse(url));
            mediaPlayer.setDisplay(holder.sfvCoverVideo.getHolder());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    holder.sfvCoverVideo.setAlpha(1);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点赞
     */
    private void toUp(Button btn, int count, boolean flag, boolean flag2) {

        if (flag == true || flag2 == true) {
            return;
        } else {
            btn.setText(count + 1 + "");
            btn.setTextColor(0xffff0000);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.afabulous_red);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btn.setCompoundDrawables(drawable, null, null, null);
        }

    }
    private void toDown(Button btn, int count, boolean flag, boolean flag2) {

        if (flag == true || flag2 == true) {
            return;
        } else {
            btn.setText(count + 1 + "");
            btn.setTextColor(0xffff0000);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.trample_red);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btn.setCompoundDrawables(drawable, null, null, null);
        }

    }


    private void upOrDown(Button btn, Button btn2, boolean flag1, boolean flag2) {

        if (flag1 == true) {
            btn.setTextColor(context.getResources().getColor(R.color.red));
            Drawable drawable = context.getResources().getDrawable(R.mipmap.afabulous_red);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btn.setCompoundDrawables(drawable, null, null, null);

        } else {
            btn.setTextColor(context.getResources().getColor(R.color.gray));
            Drawable drawable = context.getResources().getDrawable(R.mipmap.afabulous_gray);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btn.setCompoundDrawables(drawable, null, null, null);
        }

        if (flag2 == true) {
            btn2.setTextColor(context.getResources().getColor(R.color.red));
            Drawable drawable2 = context.getResources().getDrawable(R.mipmap.trample_red);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            btn2.setCompoundDrawables(drawable2, null, null, null);
        } else {
            btn2.setTextColor(context.getResources().getColor(R.color.gray));
            Drawable drawable2 = context.getResources().getDrawable(R.mipmap.trample_gray);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            btn2.setCompoundDrawables(drawable2, null, null, null);
        }
    }

    class ViewHolder {
        @BindView(R.id.video_u_Cover)
        ImageView videoUCover;
        @BindView(R.id.video_u_name)
        TextView videoUName;
        @BindView(R.id.video_tv_passtime)
        TextView videoTvPasstime;
        @BindView(R.id.rlRoot_user)
        RelativeLayout rlRootUser;
        @BindView(R.id.tv_video)
        TextView tvVideo;
        @BindView(R.id.sfvCover_video)
        SurfaceView sfvCoverVideo;
        @BindView(R.id.iv_video_play)
        TextView ivVideoPlay;
        @BindView(R.id.ll_video_play)
        LinearLayout llVideoPlay;
        @BindView(R.id.rbtn_video_1)
        Button rbtnVideo1;
        @BindView(R.id.rbtn_video_2)
        Button rbtnVideo2;
        @BindView(R.id.rbtn_video_3)
        Button rbtnVideo3;
        @BindView(R.id.rbtn_video_4)
        Button rbtnVideo4;
        @BindView(R.id.llRoot_video)
        LinearLayout llRootVideo;
        @BindView(R.id.cvRoot_video)
        CardView cvRootVideo;
        @BindView(R.id.img_u_Cover)
        ImageView imgUCover;
        @BindView(R.id.img_u_name)
        TextView imgUName;
        @BindView(R.id.img_u_passtime)
        TextView imgUPasstime;
        @BindView(R.id.tv_image)
        TextView tvImage;
        @BindView(R.id.sdv_cover_img)
        SimpleDraweeView sdvCoverImg;
        @BindView(R.id.rbtn_image_1)
        Button rbtnImage1;
        @BindView(R.id.rbtn_image_2)
        Button rbtnImage2;
        @BindView(R.id.rbtn_image_3)
        Button rbtnImage3;
        @BindView(R.id.rbtn_image_4)
        Button rbtnImage4;
        @BindView(R.id.llRoot_image)
        LinearLayout llRootImage;
        @BindView(R.id.cvRoot_image)
        CardView cvRootImage;
        @BindView(R.id.iv_text_cover)
        ImageView ivTextCover;
        @BindView(R.id.tv_text_name)
        TextView tvTextName;
        @BindView(R.id.tv_text_passtime)
        TextView tvTextPasstime;
        @BindView(R.id.tv_text)
        TextView tvText;
        @BindView(R.id.rbtn_text_1)
        Button rbtnText1;
        @BindView(R.id.rbtn_text_2)
        Button rbtnText2;
        @BindView(R.id.rbtn_text_3)
        Button rbtnText3;
        @BindView(R.id.rbtn_text_4)
        Button rbtnText4;
        @BindView(R.id.cvRoot_text)
        CardView cvRootText;
        @BindView(R.id.ll_img)
        RelativeLayout llImg;
        @BindView(R.id.ll_full_screen)
        LinearLayout llFullScreen;
        @BindView(R.id.iv_video_pause_start)
        TextView ivVideoPauseStart;
        @BindView(R.id.iv_video_cover)
        ImageView ivVideoCover;
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}


