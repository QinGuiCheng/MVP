package com.minzea.base.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xie on 2016/11/12.
 */

public class ItemSpaceDecorationGridHorizontal extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount;

    public ItemSpaceDecorationGridHorizontal(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
        outRect.bottom = space;
        if(parent.getChildLayoutPosition(view) % spanCount == 0){
            outRect.top = space;
        }else{
            outRect.top = 0;
        }

        if(parent.getChildLayoutPosition(view) < spanCount){
            outRect.left = space;
        }else{
            outRect.left = 0;
        }
    }
}
