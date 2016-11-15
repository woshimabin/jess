package com.mobiletrain.teamapp.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by qcf on 2016/11/7.
 */

public class ExListView extends ListView {

    public ExListView(Context context) {
        this(context, null);
    }

    public ExListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
