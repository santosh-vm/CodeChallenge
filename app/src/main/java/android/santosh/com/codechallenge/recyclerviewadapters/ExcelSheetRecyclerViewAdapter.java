package android.santosh.com.codechallenge.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Santosh on 8/11/17.
 */

public abstract class ExcelSheetRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = ExcelSheetRecyclerViewAdapter.class.getSimpleName();
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;

    private Context context;
    private List<T> excelSheetData;
    private LayoutInflater layoutInflater;

    private View header;
    private View footer;

    public ExcelSheetRecyclerViewAdapter(Context context) {
        this(context, null);

    }

    public ExcelSheetRecyclerViewAdapter(Context context, List<T> excelSheetData) {
        this.excelSheetData = excelSheetData;
        if (excelSheetData != null) {
            this.excelSheetData = new LinkedList<>(excelSheetData);
        }
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class HeaderFooterHolder extends RecyclerView.ViewHolder {
        public HeaderFooterHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateExcelSheetViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindExcelSheetViewHolder(RecyclerView.ViewHolder holder, int position);

    public void setExcelSheetData(List<T> excelSheetData) {
        if (excelSheetData != null) {
            this.excelSheetData = new LinkedList<>(excelSheetData);
        } else {
            this.excelSheetData = null;
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (null != header && position == 0) {
            return TYPE_HEADER;
        }
        if (null != footer
                && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderFooterHolder(header);
        } else if (viewType == TYPE_FOOTER) {
            return new HeaderFooterHolder(footer);
        } else {
            return onCreateExcelSheetViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_NORMAL) {
            onBindExcelSheetViewHolder(holder, position - getHeaderViewsCount());
        }
    }

    @Override
    public int getItemCount() {
        int size = getHeaderViewsCount();
        size += getFooterViewsCount();
        if (null != excelSheetData) {
            size += excelSheetData.size();
        }
        return size;
    }

    public int getHeaderViewsCount() {
        return null == header ? 0 : 1;
    }

    public int getFooterViewsCount() {
        return null == footer ? 0 : 1;
    }

    public void setHeaderView(View headerView) {
        if (null == headerView) {
            if (null != header) {
                header = null;
                notifyItemRemoved(0);
            }
        } else {
            if (null != header) {
                if (header != headerView) {
                    header = headerView;
                    notifyItemChanged(0);
                }
            } else {
                header = headerView;
                notifyItemInserted(0);
            }
        }
    }

    public void setFooterView(View footerView) {
        if (null == footerView) {
            if (null != footer) {
                footer = null;
                notifyItemRemoved(getItemCount());
            }
        } else {
            if (null != footer) {
                if (footer != footerView) {
                    footer = footerView;
                    notifyItemChanged(getItemCount());
                }
            } else {
                footer = footerView;
                notifyItemInserted(getItemCount());
            }
        }
    }
}
