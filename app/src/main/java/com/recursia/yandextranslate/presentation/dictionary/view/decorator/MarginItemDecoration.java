package com.recursia.yandextranslate.presentation.dictionary.view.decorator;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    private final int mMarginLeft;
    private final int mMarginRight;
    private final int mMarginTop;
    private final int mMarginBottom;

    public MarginItemDecoration(Context context, int marginAll) {
        this(context, marginAll, marginAll, marginAll, marginAll);
    }

    public MarginItemDecoration(Context context, int mMarginLeft, int mMarginRight, int mMarginTop, int mMarginBottom) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.mMarginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMarginLeft, metrics);
        this.mMarginRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMarginRight, metrics);
        this.mMarginTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMarginTop, metrics);
        this.mMarginBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMarginBottom, metrics);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mMarginLeft;
        outRect.right = mMarginRight;
        outRect.bottom = mMarginBottom;
        outRect.top = mMarginTop;
    }

}
