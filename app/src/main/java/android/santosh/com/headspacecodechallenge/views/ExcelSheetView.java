package android.santosh.com.headspacecodechallenge.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.santosh.com.headspacecodechallenge.R;
import android.santosh.com.headspacecodechallenge.Utils;
import android.santosh.com.headspacecodechallenge.recyclerviewadapters.MainExcelSheetRecyclerViewAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by Santosh on 8/11/17.
 */

public class ExcelSheetView extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final int DEFAULT_LENGTH = 56;
    public static final int LOADING_VIEW_WIDTH = 30;

    private int columnWidth;
    private int headerHeight;
    private int cellWidth;
    private int loadingViewWidth;
    private int amountAxisX = 0;
    private int amountAxisY = 0;
    private boolean hasHeader;

    private View dividerLine;
    private RecyclerView contentRecyclerView;
    private RecyclerView headerRecyclerView;
    private RecyclerView columnRecyclerView;

    private MainExcelSheetRecyclerViewAdapter mainExcelSheetRecyclerViewAdapter;

    public ExcelSheetView(@NonNull Context context) {
        super(context);
    }

    public ExcelSheetView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ExcelSheetView,
                0, 0);
        try {
            columnWidth = (int) a.getDimension(R.styleable.ExcelSheetView_column_width, Utils.dp2px(DEFAULT_LENGTH, getContext()));
            headerHeight = (int) a.getDimension(R.styleable.ExcelSheetView_header_height, Utils.dp2px(DEFAULT_LENGTH, getContext()));
            cellWidth = (int) a.getDimension(R.styleable.ExcelSheetView_cell_width, Utils.dp2px(DEFAULT_LENGTH, getContext()));
        } finally {
            a.recycle();
        }
        loadingViewWidth = Utils.dp2px(LOADING_VIEW_WIDTH, getContext());
        buildView();
    }

    private void buildView() {

        contentRecyclerView = createContent();
        addView(contentRecyclerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LayoutParams contentLinearLayoutParams = (LayoutParams) contentRecyclerView.getLayoutParams();
        contentLinearLayoutParams.leftMargin = columnWidth;
        contentLinearLayoutParams.topMargin = headerHeight;
        contentRecyclerView.setLayoutParams(contentLinearLayoutParams);


        headerRecyclerView = createHeader();
        addView(headerRecyclerView, new LayoutParams(LayoutParams.WRAP_CONTENT, headerHeight));
        LayoutParams hederLinearLayoutParams = (LayoutParams) headerRecyclerView.getLayoutParams();
        hederLinearLayoutParams.leftMargin = columnWidth;
        headerRecyclerView.setLayoutParams(hederLinearLayoutParams);


        columnRecyclerView = createColumn();
        addView(columnRecyclerView, new LayoutParams(columnWidth, LayoutParams.WRAP_CONTENT));
        LayoutParams columnLinearLayoutParams = (LayoutParams) columnRecyclerView.getLayoutParams();
        columnLinearLayoutParams.topMargin = headerHeight;
        columnRecyclerView.setLayoutParams(columnLinearLayoutParams);

        dividerLine = createDividerToColumn();
        addView(dividerLine, new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutParams lineLayoutParameters = (LayoutParams) dividerLine.getLayoutParams();
        lineLayoutParameters.leftMargin = columnWidth;
        dividerLine.setLayoutParams(lineLayoutParameters);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private RecyclerView createContent() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(getContentLayoutManager());
        recyclerView.addOnScrollListener(contentScrollListener);
        return recyclerView;
    }

    private RecyclerView createHeader() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(getHeaderLayoutManager());
        recyclerView.addOnScrollListener(contentScrollListener);
        return recyclerView;
    }

    private RecyclerView createColumn() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(getColumnLayoutManager());
        recyclerView.addOnScrollListener(columnScrollListener);
        return recyclerView;
    }

    private View createDividerToColumn() {
        View view = new View(getContext());
        view.setVisibility(GONE);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.cell_border_color));
        return view;
    }

    protected RecyclerView.LayoutManager getContentLayoutManager() {
        if (null == contentRecyclerView || null == contentRecyclerView.getLayoutManager()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            return layoutManager;
        }
        return contentRecyclerView.getLayoutManager();
    }

    private RecyclerView.LayoutManager getHeaderLayoutManager() {
        if (null == headerRecyclerView || null == headerRecyclerView.getLayoutManager()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            return layoutManager;
        }
        return headerRecyclerView.getLayoutManager();
    }

    private RecyclerView.LayoutManager getColumnLayoutManager() {
        if (null == columnRecyclerView || null == columnRecyclerView.getLayoutManager()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            return layoutManager;
        }
        return columnRecyclerView.getLayoutManager();
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public void setAdapter(MainExcelSheetRecyclerViewAdapter mainExcelSheetRecyclerViewAdapter) {
        if (mainExcelSheetRecyclerViewAdapter != null) {
            this.mainExcelSheetRecyclerViewAdapter = mainExcelSheetRecyclerViewAdapter;
            this.mainExcelSheetRecyclerViewAdapter.setColumnWidth(columnWidth);
            this.mainExcelSheetRecyclerViewAdapter.setHeaderHeight(headerHeight);
            this.mainExcelSheetRecyclerViewAdapter.setOnScrollListener(columnScrollListener);
            this.mainExcelSheetRecyclerViewAdapter.setExcelSheetView(this);
            buildAdapter();
        }
    }

    private void buildAdapter() {
        if (columnRecyclerView != null) {
            contentRecyclerView.setAdapter(mainExcelSheetRecyclerViewAdapter.getColumnRecyclerViewAdapter());
        }
        if (headerRecyclerView != null) {
            headerRecyclerView.setAdapter(mainExcelSheetRecyclerViewAdapter.getHeaderRecyclerViewAdapter());
        }
        if (contentRecyclerView != null) {
            contentRecyclerView.setAdapter(mainExcelSheetRecyclerViewAdapter.getContentRecyclerViewAdapter());
        }
    }

    private RecyclerView.OnScrollListener contentScrollListener
            = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            amountAxisX += dx;
            fastScrollTo(amountAxisX, contentRecyclerView, loadingViewWidth, hasHeader);
            fastScrollTo(amountAxisX, headerRecyclerView, loadingViewWidth, hasHeader);
//            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            int visibleItemCount = recyclerView.getChildCount();
//            int totalItemCount = manager.getItemCount();
//            int firstVisibleItem = manager.findFirstVisibleItemPosition();
//            if (totalItemCount - visibleItemCount <= firstVisibleItem && onLoadMoreListener != null && hasFooter) {
//                onLoadMoreListener.onLoadMore();
//            }
//            if (amountAxisX < loadingViewWidth && onLoadMoreListener != null && hasHeader) {
//                onLoadMoreListener.onLoadHistory();
//            }
//            if (((hasHeader && amountAxisX > loadingViewWidth) || (!hasHeader && amountAxisX > 0)) && dividerLineVisible) {
//                dividerLine.setVisibility(VISIBLE);
//            } else {
//                dividerLine.setVisibility(GONE);
//            }
        }
    };

    private RecyclerView.OnScrollListener columnScrollListener
            = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            amountAxisY += dy;
            for (int i = 0; i < contentRecyclerView.getChildCount(); i++) {
                if (contentRecyclerView.getChildAt(i) instanceof RecyclerView) {
                    RecyclerView recyclerView1 = (RecyclerView) contentRecyclerView.getChildAt(i);
                    fastScrollVertical(amountAxisY, recyclerView1);
                }
            }
            fastScrollVertical(amountAxisY, columnRecyclerView);
            if (mainExcelSheetRecyclerViewAdapter != null) {
                mainExcelSheetRecyclerViewAdapter.setAmountAxisY(amountAxisY);
            }
        }
    };

    public static void fastScrollVertical(int amountAxis, RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //call this method the OnScrollListener's onScrolled will be called，but dx and dy always be zero.
        linearLayoutManager.scrollToPositionWithOffset(0, -amountAxis);
    }

    private void fastScrollTo(int amountAxis, RecyclerView recyclerView, int offset, boolean hasHeader) {
        int position = 0, width = cellWidth;
        if (amountAxis >= offset && hasHeader) {
            amountAxis -= offset;
            position++;
        }
        position += amountAxis / width;
        amountAxis %= width;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //call this method the OnScrollListener's onScrolled will be called，but dx and dy always be zero.
        linearLayoutManager.scrollToPositionWithOffset(position, -amountAxis);
    }

    public void fastScrollVerticalLeft() {
        fastScrollVertical(amountAxisY, columnRecyclerView);
    }

    public void scrollBy(int dx) {
        contentScrollListener.onScrolled(contentRecyclerView, dx, 0);
    }

    @Override
    public void onGlobalLayout() {

    }
}
