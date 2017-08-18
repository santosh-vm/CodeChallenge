package android.santosh.com.codechallenge.fragment;

import android.os.Bundle;
import android.santosh.com.codechallenge.R;
import android.santosh.com.codechallenge.interfaces.ExcelSheetClickListener;
import android.santosh.com.codechallenge.interfaces.ExcelSheetListener;
import android.santosh.com.codechallenge.model.ColumnTitle;
import android.santosh.com.codechallenge.model.HeaderTitle;
import android.santosh.com.codechallenge.model.TableData;
import android.santosh.com.codechallenge.recyclerviewadapters.CustomAdapter;
import android.santosh.com.codechallenge.views.ExcelSheetEditText;
import android.santosh.com.codechallenge.views.ExcelSheetView;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by Santosh on 8/13/17.
 */

public class ExcelSheetFragment extends BaseFragment implements ExcelSheetListener, ExcelSheetClickListener {
    private static String TAG = ExcelSheetFragment.class.getSimpleName();

    private ProgressBar progress;
    private ExcelSheetView excelSheetView;
    private CustomAdapter customAdapter;
    private ExcelSheetEditText excelSheetEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationAPI.getApplicationController().addExcelSheetListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_excel_fragment, container, false);
        bindUIElements(root);
        applicationAPI.getApplicationController().fetchExcelSheetData();
        return root;
    }

    private void bindUIElements(View rootView) {
        customAdapter = new CustomAdapter(getContext(), this);
        excelSheetView = (ExcelSheetView) rootView.findViewById(R.id.excel_sheet_view);
        excelSheetView.setAdapter(customAdapter);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);
        excelSheetEditText = (ExcelSheetEditText) rootView.findViewById(R.id.excel_sheet_edit_text);
        excelSheetEditText.setExcelSheetClickListener(this);
        excelSheetEditText.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        applicationAPI.getApplicationController().removeExcelSheetListener(this);
    }

    @Override
    public void onExcelSheetLoaded(List<HeaderTitle> headerTitleList, List<ColumnTitle> columnTitleList, List<List<TableData.CellData>> tableDataList) {
        progress.setVisibility(View.GONE);
        excelSheetEditText.setVisibility(View.VISIBLE);
        excelSheetEditText.setText("");
        excelSheetEditText.clearFocus();
        customAdapter.setAllData(headerTitleList, columnTitleList, tableDataList);
    }

    @Override
    public void onExcelSheetContentClicked(TableData.CellData cellData, int row, int column) {
        Log.d(TAG, "cellData.getData(): " + cellData.getData() + ", row: " + row + ", column: " + column);
        applicationAPI.getApplicationController().updateCellSelectedStatus(row, column);
        excelSheetEditText.setInfo(cellData.getData(), row, column);
    }

    @Override
    public void onExcelSheetCellDataUpdated(String data, int row, int column) {
        Log.d(TAG, "onExcelSheetCellDataUpdated, data: " + data + ", row: " + row + ", column: " + column);
        applicationAPI.getApplicationController().updateCellData(data, row, column);
    }

    @Override
    public void onExcelSheetCellDataRefreshed(List<List<TableData.CellData>> tableDataList) {
        customAdapter.setContentData(tableDataList);
    }
}
