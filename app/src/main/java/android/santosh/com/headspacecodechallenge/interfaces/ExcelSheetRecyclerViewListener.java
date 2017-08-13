package android.santosh.com.headspacecodechallenge.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Santosh on 8/11/17.
 */

public interface ExcelSheetRecyclerViewListener {

    RecyclerView.ViewHolder onCreateCellDataViewHolder(ViewGroup parent, int viewType);

    void onBindCellDataViewHolder(RecyclerView.ViewHolder holder, int horizontalPosition, int verticalPosition);

    RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    RecyclerView.ViewHolder onCreateColumnViewHolder(ViewGroup parent, int viewType);

    void onBindColumnViewHolder(RecyclerView.ViewHolder holder, int position);

    View onCreateTopLeftView();

    int getCellDataItemViewType(int horizontalPosition,int verticalPosition);

    int getHeaderItemViewType(int position);

    int getColumnItemViewType(int position);
}
