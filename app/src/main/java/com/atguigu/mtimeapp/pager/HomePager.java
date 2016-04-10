package com.atguigu.mtimeapp.pager;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.mtimeapp.base.BasePager;

/**
 * Created by FlagWu on 2016/4/8.
 */
public class HomePager extends BasePager{
    private TextView textView;

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        textView = new TextView(mActivity);
        textView.setText("首页");
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.print("首页数据初始化");
        textView.setText("首页");
    }
}
