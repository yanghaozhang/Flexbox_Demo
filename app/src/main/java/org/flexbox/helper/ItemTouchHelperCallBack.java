package org.flexbox.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.android.flexbox.FlexboxLayoutManager;

/**
 * RecyclerView item Touch Helper
 * 长按拖拽,滑动删除
 */

public class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private OnItemDragListener onItemDragListener;

    /**
     * 返回可以滑动的方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        //拖动
        int dragFlags;
        //滑动
        int swipeFlags;
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager || manager instanceof FlexboxLayoutManager) {
            //网格布局管理器允许上下左右拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = 0;
        } else {
            //其他布局管理器允许上下拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 拖拽到新位置时候的回调方法
     *
     * @param recyclerView
     * @param viewHolder   拖动的 item 的下标
     * @param target       目标 item 的下标，目标 item 就是当拖曳过程中，不断和拖动的 item 做位置交换的条目。
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (onItemDragListener != null) {
            onItemDragListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    /**
     * 当用户左右滑动的时候执行的方法
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //这里可以做RecyclerView的滑动操作,比如滑动删除
    }


    /**
     * 当长按选中item的时候（拖拽开始的时候）调用
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
//            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当手指松开的时候（拖拽完成的时候）调用
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
//        viewHolder.itemView.setBackgroundColor(Color.WHITE);
    }

    public void setOnItemDragListeber(OnItemDragListener onItemDragListener) {
        this.onItemDragListener = onItemDragListener;
    }

    public interface OnItemDragListener {

        void onItemMove(int startPos, int endPos);

    }
}