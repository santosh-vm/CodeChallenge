package android.santosh.com.headspacecodechallenge.recyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santosh on 8/11/17.
 */

public abstract class ExcelSheetRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;

    private Context context;
    private List<T> excelSheetData;
    private LayoutInflater layoutInflater;

    public ExcelSheetRecyclerViewAdapter(Context context) {
        this(context, null);

    }

    public ExcelSheetRecyclerViewAdapter(Context context, List<T> excelSheetData) {
        this.excelSheetData = new ArrayList<>(excelSheetData);
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public abstract RecyclerView.ViewHolder onCreateExcelSheetViewHolder(ViewGroup parent, int viewType);
    public abstract void onBindExcelSheetViewHolder(RecyclerView.ViewHolder holder, int position);

    public void setExcelSheetData(List<T> excelSheetData){
        if(excelSheetData!=null){
            this.excelSheetData = new ArrayList<>(excelSheetData);
        } else {
            this.excelSheetData = null;
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
