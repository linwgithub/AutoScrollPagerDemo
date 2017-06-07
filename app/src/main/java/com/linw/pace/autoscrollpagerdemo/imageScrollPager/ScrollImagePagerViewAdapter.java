package com.linw.pace.autoscrollpagerdemo.imageScrollPager;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linw.pace.autoscrollpagerdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者: linw
 * 时间: 17/6/7
 * 内容:
 */

public class ScrollImagePagerViewAdapter extends PagerAdapter {

    private static final int INTERVAL_TIME = 2000;
    private Activity activity;
    private LayoutInflater inflater;
    private Map<Integer, View> viewMap = new HashMap<>();
    private Timer timer;
    private ViewPager pager;
    private List<Integer> bgDatas = new ArrayList<>();

    public void startPlay(){
        cancelPlay();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int currentPageIndex = pager.getCurrentItem();
                        currentPageIndex ++;
                        if (currentPageIndex < bgDatas.size()){
                            pager.setCurrentItem(currentPageIndex);
                        }else {
                            pager.setCurrentItem(0);
                        }
                    }
                });

            }
        }, INTERVAL_TIME, INTERVAL_TIME);

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按住事件发生后执行代码的区域

                    case MotionEvent.ACTION_MOVE:
                        // 移动事件发生后执行代码的区域
                        cancelPlay();
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开事件发生后执行代码的区域
                        startPlay();
                        break;
                    default:
                        startPlay();
                        break;
                }
                return false;
            }
        });
    }

    public void cancelPlay(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public ScrollImagePagerViewAdapter(Activity activity, ViewPager pager, List<Integer> bgDatas) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        this.pager = pager;
        this.bgDatas = bgDatas;
    }

    @Override
    public int getCount() {
        return bgDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.card_holder_top_view, null, false);
        viewMap.put(position, view);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.img.setBackgroundColor(bgDatas.get(position));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewMap.get(position) != null) {
            container.removeView(viewMap.get(position));
            viewMap.remove(position);
        }
    }

    class MyViewHolder {

        ImageView img;

        public MyViewHolder(View containerView) {
            img = (ImageView) containerView.findViewById(R.id.img_top);
        }

    }
}
