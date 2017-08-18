package android.santosh.com.codechallenge.interfaces;

import android.santosh.com.codechallenge.model.ColumnTitle;
import android.santosh.com.codechallenge.model.HeaderTitle;
import android.santosh.com.codechallenge.model.TableData;

import java.util.List;

/**
 * Created by Santosh on 8/13/17.
 */

public interface ExcelSheetListener {
    void onExcelSheetLoaded(List<HeaderTitle> headerTitleList,
                            List<ColumnTitle> columnTitleList,
                            List<List<TableData.CellData>> tableDataList);

    void onExcelSheetCellDataRefreshed(List<List<TableData.CellData>> tableDataList);
}
