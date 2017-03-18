package com.minzea.base.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by XieZhangxing on 2016/7/13 0013.
 */
public class ItemSpaceDecorationLinearVertical extends RecyclerView.ItemDecoration {

    private final int mCommonSpaceHeight;
    private final int mTopSpaceHeight;
    private final int mBottomSpaceHeight;

    public ItemSpaceDecorationLinearVertical(int commonSpaceHeight, int topSpaceHeight, int bottomSpaceHeight) {
        this.mCommonSpaceHeight = commonSpaceHeight;
        this.mTopSpaceHeight = topSpaceHeight;
        this.mBottomSpaceHeight = bottomSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        // 第一个
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = mTopSpaceHeight;
        }

        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) { // 最后一个
            outRect.bottom = mBottomSpaceHeight;
        }else{ // 其他
            outRect.bottom = mCommonSpaceHeight;
        }
    }
}
