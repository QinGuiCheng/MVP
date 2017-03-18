package com.minzea.base.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by XieZhangxing on 2016/7/13 0013.
 */
public class ItemSpaceDecorationLinearHorizontal extends RecyclerView.ItemDecoration {

    private final int mCommonSpaceHeight;
    private final int mStartSpaceHeight;
    private final int mEndSpaceHeight;

    public ItemSpaceDecorationLinearHorizontal(int commonSpaceHeight, int startSpaceHeight, int endSpaceHeight) {
        this.mCommonSpaceHeight = commonSpaceHeight;
        this.mStartSpaceHeight = startSpaceHeight;
        this.mEndSpaceHeight = endSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        // 第一个
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.left = mStartSpaceHeight;
        }

        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) { // 最后一个
            outRect.right = mEndSpaceHeight;
        }else{ // 其他
            outRect.right = mCommonSpaceHeight;
        }
    }
}
