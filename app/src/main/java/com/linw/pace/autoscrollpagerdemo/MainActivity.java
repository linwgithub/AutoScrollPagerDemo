package com.linw.pace.autoscrollpagerdemo;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.linw.pace.autoscrollpagerdemo.imageScrollPager.ScrollImagePagerIndexAdapter;
import com.linw.pace.autoscrollpagerdemo.imageScrollPager.ScrollImagePagerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager topViewPager;
    private RecyclerView pointerList;

    private List<Integer> bgs = new ArrayList<>();
    private ScrollImagePagerViewAdapter viewAdapter;
    private ScrollImagePagerIndexAdapter indexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topViewPager = (ViewPager) findViewById(R.id.img_view);

        pointerList = (RecyclerView) findViewById(R.id.pointer_list);


        initView();
    }

    private void initView(){

        //添加数据
        bgs.add(Color.BLUE);
        bgs.add(Color.YELLOW);
        bgs.add(Color.GRAY);
        bgs.add(Color.RED);
        bgs.add(Color.GREEN);

        indexAdapter = new ScrollImagePagerIndexAdapter(this, bgs.size(), new ScrollImagePagerIndexAdapter.OnSelectedPointer() {
            @Override
            public void done(int position) {

                if (topViewPager != null && topViewPager.getCurrentItem() != position){
                    topViewPager.setCurrentItem(position);
                }
            }
        });

        pointerList.setAdapter(indexAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pointerList.setLayoutManager(linearLayoutManager);

        viewAdapter = new ScrollImagePagerViewAdapter(this, topViewPager, bgs);
        topViewPager.setAdapter(viewAdapter);
        indexAdapter.setSelectedPosition(0);
        topViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (indexAdapter != null && position != indexAdapter.getSelectedPosition()){
                    indexAdapter.setSelectedPosition(position);
                    pointerList.smoothScrollToPosition(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewAdapter.startPlay();
    }
}
