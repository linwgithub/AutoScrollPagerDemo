package com.linw.pace.autoscrollpagerdemo.imageScrollPager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linw.pace.autoscrollpagerdemo.R;


/**
 * 作者: linw
 * 时间: 17/6/7
 * 内容:
 */

public class ScrollImagePagerIndexAdapter extends RecyclerView.Adapter<ScrollImagePagerIndexAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private int selectedPosition = 0;
    private int count;

    private OnSelectedPointer onSelectedPointer;

    public interface OnSelectedPointer {
        void done(int position);
    }

    public ScrollImagePagerIndexAdapter(Context context, int count, OnSelectedPointer onSelectedPointer) {
        this.inflater = LayoutInflater.from(context);
        this.onSelectedPointer = onSelectedPointer;
        this.count = count;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.view_pointer, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {

        holder.centerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedPosition(position);
            }
        });
        if (selectedPosition == position) {
            //设置选择项的背景颜色
            holder.centerView.setSelected(true);
        } else {
            //设置为选择项的背景颜色
            holder.centerView.setSelected(false);
        }
    }

    @Override
    public int getItemCount()
    {
        return count;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView centerView;

        public MyViewHolder(View view)
        {
            super(view);
            this.centerView = (ImageView) view.findViewById(R.id.img_pointer);
        }
    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        if (selectedPosition != this.selectedPosition
                && selectedPosition >= 0 && selectedPosition < getItemCount()) {
            this.selectedPosition = selectedPosition;
            onSelectedPointer.done(selectedPosition);
            notifyDataSetChanged();
        }
    }
}
