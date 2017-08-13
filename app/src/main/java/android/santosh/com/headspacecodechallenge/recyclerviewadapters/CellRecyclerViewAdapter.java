package android.santosh.com.headspacecodechallenge.recyclerviewadapters;

import android.content.Context;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetRecyclerViewListener;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Santosh on 8/11/17.
 */

public class CellRecyclerViewAdapter<C> extends ExcelSheetRecyclerViewAdapter<C> {
    private int verticalPosition;
    ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener;

    public CellRecyclerViewAdapter(Context context,
                                   ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener,
                                   int verticalPosition) {
        super(context);
        this.excelSheetRecyclerViewListener = excelSheetRecyclerViewListener;
        this.verticalPosition = verticalPosition;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        if (viewType == TYPE_NORMAL) {
            viewType = excelSheetRecyclerViewListener.getCellDataItemViewType(position, verticalPosition);
        }
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateExcelSheetViewHolder(ViewGroup parent, int viewType) {
        if (excelSheetRecyclerViewListener != null) {
            return excelSheetRecyclerViewListener.onCreateCellDataViewHolder(parent, viewType);
        } else {
            return null;
        }
    }

    @Override
    public void onBindExcelSheetViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (excelSheetRecyclerViewListener != null) {
            excelSheetRecyclerViewListener.onBindCellDataViewHolder(holder, position, verticalPosition);
        }
    }
}
