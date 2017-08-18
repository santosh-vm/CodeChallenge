package android.santosh.com.codechallenge.recyclerviewadapters;

import android.content.Context;
import android.santosh.com.codechallenge.interfaces.ExcelSheetRecyclerViewListener;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Santosh on 8/11/17.
 */

public class HeaderRecyclerViewAdapter<H> extends ExcelSheetRecyclerViewAdapter<H> {
    private ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener;

    public HeaderRecyclerViewAdapter(Context context, List<H> list, ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener) {
        super(context, list);
        this.excelSheetRecyclerViewListener = excelSheetRecyclerViewListener;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        if (viewType == TYPE_NORMAL) {
            viewType = excelSheetRecyclerViewListener.getHeaderItemViewType(position);
        }
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateExcelSheetViewHolder(ViewGroup parent, int viewType) {
        if (excelSheetRecyclerViewListener != null) {
            return excelSheetRecyclerViewListener.onCreateHeaderViewHolder(parent, viewType);
        } else {
            return null;
        }
    }

    @Override
    public void onBindExcelSheetViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (excelSheetRecyclerViewListener != null) {
            excelSheetRecyclerViewListener.onBindHeaderViewHolder(holder, position);
        }
    }
}
