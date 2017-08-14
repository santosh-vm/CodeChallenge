package android.santosh.com.headspacecodechallenge.fragment;

import android.os.Bundle;
import android.santosh.com.headspacecodechallenge.R;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetListener;
import android.santosh.com.headspacecodechallenge.model.ColumnTitle;
import android.santosh.com.headspacecodechallenge.model.HeaderTitle;
import android.santosh.com.headspacecodechallenge.model.TableData;
import android.santosh.com.headspacecodechallenge.recyclerviewadapters.CustomAdapter;
import android.santosh.com.headspacecodechallenge.views.ExcelSheetView;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Santosh on 8/13/17.
 */

public class ExcelSheetFragment extends BaseFragment implements ExcelSheetListener{
    private static String TAG = ExcelSheetFragment.class.getSimpleName();

    private ProgressBar progress;
    private ExcelSheetView excelSheetView;
    private CustomAdapter customAdapter;

    private View.OnClickListener cellOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TableData.CellData cellData = (TableData.CellData) view.getTag();
            Toast.makeText(getContext(), "cellData.getData(): " + cellData.getData(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headSpaceAPI.getHeadSpaceController().addExcelSheetListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_excel_fragment, container, false);
        bindUIElements(root);
        headSpaceAPI.getHeadSpaceController().fetchExcelSheetData();
        return root;
    }

    private void bindUIElements(View rootView) {
        customAdapter = new CustomAdapter(getContext(), cellOnClickListener);
        excelSheetView = (ExcelSheetView) rootView.findViewById(R.id.excel_sheet_view);
        excelSheetView.setAdapter(customAdapter);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        headSpaceAPI.getHeadSpaceController().removeExcelSheetListener(this);
    }

    @Override
    public void onExcelSheetLoaded(List<HeaderTitle> headerTitleList, List<ColumnTitle> columnTitleList, List<List<TableData.CellData>> tableDataList) {
        progress.setVisibility(View.GONE);
        customAdapter.setAllData(headerTitleList,columnTitleList,tableDataList);
        customAdapter.disableFooter();
        customAdapter.disableHeader();
    }
}
