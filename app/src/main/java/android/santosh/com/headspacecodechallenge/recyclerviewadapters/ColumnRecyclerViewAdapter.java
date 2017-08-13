package android.santosh.com.headspacecodechallenge.recyclerviewadapters;

import android.content.Context;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetRecyclerViewListener;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Santosh on 8/11/17.
 */

public class ColumnRecyclerViewAdapter<C> extends ExcelSheetRecyclerViewAdapter<C> {
    private ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener;

    public ColumnRecyclerViewAdapter(Context context, List<C> excelSheetData, ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener) {
        super(context, excelSheetData);
        this.excelSheetRecyclerViewListener = excelSheetRecyclerViewListener;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        if (excelSheetRecyclerViewListener != null) {
            viewType = excelSheetRecyclerViewListener.getColumnItemViewType(position);
        }
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateExcelSheetViewHolder(ViewGroup parent, int viewType) {
        if (excelSheetRecyclerViewListener != null) {
            return excelSheetRecyclerViewListener.onCreateColumnViewHolder(parent, viewType);
        } else {
            return null;
        }
    }

    @Override
    public void onBindExcelSheetViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (excelSheetRecyclerViewListener != null) {
            excelSheetRecyclerViewListener.onBindColumnViewHolder(holder, position);
        }
    }
}
