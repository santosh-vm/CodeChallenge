package android.santosh.com.headspacecodechallenge.recyclerviewadapters;

import android.content.Context;
import android.santosh.com.headspacecodechallenge.Utils;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetRecyclerViewListener;
import android.santosh.com.headspacecodechallenge.views.ExcelSheetView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by Santosh on 8/12/17.
 */

public abstract class MainExcelSheetRecyclerViewAdapter<H, C, CH> implements ExcelSheetRecyclerViewListener {

    private static final int LOADING_VIEW_WIDTH = 30;

    private Context context;
    private ExcelSheetRecyclerViewAdapter headerRecyclerViewAdapter;
    private ExcelSheetRecyclerViewAdapter columnRecyclerViewAdapter;
    private ExcelSheetRecyclerViewAdapter contentRecyclerViewAdapter;
    private View leftTopView;
    private ExcelSheetView excelSheetView;
    protected RecyclerView.OnScrollListener onScrollListener;
    protected List<H> headerData;
    protected List<C> columnData;
    protected List<List<CH>> contentData;
    private int columnWidth;
    private int headerHeight;
    private int amountAxisY = 0;

    public MainExcelSheetRecyclerViewAdapter(Context context) {
        this.context = context;
        initRecyclerViewAdapter();
    }

    private void initRecyclerViewAdapter() {
        headerRecyclerViewAdapter = new HeaderRecyclerViewAdapter(context, headerData, this);
        columnRecyclerViewAdapter = new ColumnRecyclerViewAdapter(context, columnData, this);
        contentRecyclerViewAdapter = new ColumnRecyclerViewAdapter(context, contentData, this);
    }

    public void setHeaderData(List<H> headerData) {
        this.headerData = headerData;
        headerRecyclerViewAdapter.setExcelSheetData(headerData);
    }

    public void setColumnData(List<C> columnData) {
        this.columnData = columnData;
        columnRecyclerViewAdapter.setExcelSheetData(columnData);
    }

    public void setContentData(List<List<CH>> contentData) {
        this.contentData = contentData;
        contentRecyclerViewAdapter.setExcelSheetData(contentData);
    }

    public void setAllData(List<H> headerData, List<C> columnData, List<List<CH>> contentData) {
        setHeaderData(headerData);
        setColumnData(columnData);
        setContentData(contentData);
        excelSheetView.scrollBy(0);
        excelSheetView.fastScrollVerticalLeft();
        if (!Utils.isEmpty(columnData) && !Utils.isEmpty(headerData) && excelSheetView != null
                && !Utils.isEmpty(contentData) && leftTopView == null) {
            leftTopView = onCreateTopLeftView();
            excelSheetView.addView(leftTopView, new FrameLayout.LayoutParams(columnWidth, headerHeight));
        } else if (leftTopView != null) {
            if (Utils.isEmpty(columnData)) {
                leftTopView.setVisibility(View.GONE);
            } else {
                leftTopView.setVisibility(View.VISIBLE);
            }
        }
    }

    public ExcelSheetRecyclerViewAdapter getHeaderRecyclerViewAdapter() {
        return headerRecyclerViewAdapter;
    }

    public ExcelSheetRecyclerViewAdapter getColumnRecyclerViewAdapter() {
        return columnRecyclerViewAdapter;
    }

    public ExcelSheetRecyclerViewAdapter getContentRecyclerViewAdapter() {
        return contentRecyclerViewAdapter;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
        if (contentRecyclerViewAdapter != null && contentRecyclerViewAdapter instanceof ContentHolderRecyclerViewAdapter) {
            ((ContentHolderRecyclerViewAdapter) contentRecyclerViewAdapter).setOnScrollListener(onScrollListener);
        }
    }

    public void setAmountAxisY(int amountAxisY) {
        this.amountAxisY = amountAxisY;
        if (contentRecyclerViewAdapter != null && contentRecyclerViewAdapter instanceof ContentHolderRecyclerViewAdapter) {
            ((ContentHolderRecyclerViewAdapter) contentRecyclerViewAdapter).setAmountAxisY(amountAxisY);
        }
    }

    public void setExcelSheetView(ExcelSheetView excelSheetView) {
        this.excelSheetView = excelSheetView;

    }

    public H getHeaderItem(int position) {
        if (Utils.isEmpty(headerData) || position < 0 || position >= headerData.size()) {
            return null;
        }
        return headerData.get(position);
    }

    public C getColumnItem(int position) {
        if (Utils.isEmpty(columnData) || position < 0 || position >= columnData.size()) {
            return null;
        }
        return columnData.get(position);
    }

    public CH getContentItem(int row, int column) {
        if (Utils.isEmpty(contentData) || row < 0 || row >= contentData.size() || Utils
                .isEmpty(contentData.get(row)) || column < 0 || column >= contentData.get(row).size()) {
            return null;
        }
        return contentData.get(row).get(column);
    }

    private View createTopStaticView() {
        View topStaticView = new View(context);
        int loadingWidth = Utils.dp2px(LOADING_VIEW_WIDTH, context);
        topStaticView.setLayoutParams(new ViewGroup.LayoutParams(loadingWidth, headerHeight));

        return topStaticView;
    }

    private View createContentLoadingView() {
        int loadingWidth = Utils.dp2px(LOADING_VIEW_WIDTH, context);
        LinearLayout loadingView = new LinearLayout(context);
        loadingView.setOrientation(LinearLayout.VERTICAL);
        loadingView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(loadingWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        loadingView.setLayoutParams(lpp);

        ProgressBar progressBar = new ProgressBar(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                Utils.dp2px(16, context), Utils.dp2px(16, context)));
        progressBar.setLayoutParams(lp);

        loadingView.addView(progressBar, lp);

        return loadingView;
    }

    public void enableHeader() {
        if (headerRecyclerViewAdapter != null && contentRecyclerViewAdapter != null && excelSheetView != null &&
                (headerRecyclerViewAdapter.getHeaderViewsCount() <= 0 || contentRecyclerViewAdapter.getHeaderViewsCount() <= 0)) {
            headerRecyclerViewAdapter.setHeaderView(createTopStaticView());
            contentRecyclerViewAdapter.setHeaderView(createContentLoadingView());
            excelSheetView.setHasHeader(true);
            excelSheetView.scrollBy(Utils.dp2px(LOADING_VIEW_WIDTH, context));
        }
    }

    public void enableFooter() {
        if (headerRecyclerViewAdapter != null && contentRecyclerViewAdapter != null && excelSheetView != null &&
                (contentRecyclerViewAdapter.getFooterViewsCount() <= 0 || contentRecyclerViewAdapter.getFooterViewsCount() <= 0)) {
            headerRecyclerViewAdapter.setFooterView(createTopStaticView());
            contentRecyclerViewAdapter.setFooterView(createContentLoadingView());
            //excelSheetView.setHasFooter(true);
        }
    }

    public void disableHeader() {
        if (headerRecyclerViewAdapter != null && contentRecyclerViewAdapter != null && excelSheetView != null &&
                (contentRecyclerViewAdapter.getHeaderViewsCount() > 0 || contentRecyclerViewAdapter.getHeaderViewsCount() > 0)) {
            headerRecyclerViewAdapter.setHeaderView(null);
            contentRecyclerViewAdapter.setHeaderView(null);
            excelSheetView.setHasHeader(false);
            excelSheetView.scrollBy(-Utils.dp2px(LOADING_VIEW_WIDTH, context));
        }
    }

    public void disableFooter() {
        if (headerRecyclerViewAdapter != null && contentRecyclerViewAdapter != null && excelSheetView != null &&
                (contentRecyclerViewAdapter.getHeaderViewsCount() > 0 || contentRecyclerViewAdapter.getHeaderViewsCount() > 0)) {
            headerRecyclerViewAdapter.setFooterView(null);
            contentRecyclerViewAdapter.setFooterView(null);
            //excelSheetView.setHasFooter(false);

        }
    }

    @Override
    public int getCellDataItemViewType(int verticalPosition, int horizontalPosition) {
        return ExcelSheetRecyclerViewAdapter.TYPE_NORMAL;
    }

    @Override
    public int getColumnItemViewType(int position) {
        return ExcelSheetRecyclerViewAdapter.TYPE_NORMAL;
    }

    @Override
    public int getHeaderItemViewType(int position) {
        return ExcelSheetRecyclerViewAdapter.TYPE_NORMAL;
    }
}
