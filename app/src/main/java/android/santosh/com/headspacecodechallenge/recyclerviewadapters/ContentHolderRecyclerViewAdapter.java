package android.santosh.com.headspacecodechallenge.recyclerviewadapters;

import android.content.Context;
import android.santosh.com.headspacecodechallenge.Utils;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetRecyclerViewListener;
import android.santosh.com.headspacecodechallenge.views.ExcelSheetView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Santosh on 8/11/17.
 */

public class ContentHolderRecyclerViewAdapter<CH> extends ExcelSheetRecyclerViewAdapter<CH> {
    private ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener;
    private Context context;
    private int amountAxisY = 0;
    private List<String> list;
    private RecyclerView.OnScrollListener onScrollListener;
    private List<RecyclerView.Adapter> adapterList;

    public ContentHolderRecyclerViewAdapter(Context context,
                                            List<CH> excelSheetData,
                                            ExcelSheetRecyclerViewListener excelSheetRecyclerViewListener) {
        super(context, excelSheetData);
        this.context = context;
        this.excelSheetRecyclerViewListener = excelSheetRecyclerViewListener;
        this.adapterList = new LinkedList<>();
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void setExcelSheetData(List<CH> excelSheetData) {
        super.setExcelSheetData(excelSheetData != null ? ((List) excelSheetData.get(0)) : null);
        if (excelSheetData != null) {
            if (list == null || list.size() >= excelSheetData.size()) {//refresh or first time
                list = new LinkedList<>();
            }
            for (int i = list.size(); i < excelSheetData.size(); i++) {
                list.add("");
            }
        } else {
            list = null;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateExcelSheetViewHolder(ViewGroup parent, int viewType) {
        RecyclerView recyclerView = new RecyclerView(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return new RecyclerViewViewHolder(recyclerView);
    }

    @Override
    public void onBindExcelSheetViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof RecyclerViewViewHolder)) {
            return;
        }

        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        CellRecyclerViewAdapter cellRecyclerViewAdapter =
                new CellRecyclerViewAdapter(context, excelSheetRecyclerViewListener, position);
        adapterList.add(cellRecyclerViewAdapter);
        cellRecyclerViewAdapter.setExcelSheetData(list);
        viewHolder.recyclerView.setAdapter(cellRecyclerViewAdapter);

        viewHolder.recyclerView.removeOnScrollListener(onScrollListener);
        viewHolder.recyclerView.addOnScrollListener(onScrollListener);
        ExcelSheetView.fastScrollVertical(amountAxisY, viewHolder.recyclerView);
    }

    private static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        public final RecyclerView recyclerView;

        public RecyclerViewViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView;
        }
    }

    public void setAmountAxisY(int amountAxisY) {
        this.amountAxisY = amountAxisY;
    }

    public void contentHolderNotifyDataSetChanged() {
        if (!Utils.isEmpty(adapterList)) {
            for (RecyclerView.Adapter adapter : adapterList) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}
