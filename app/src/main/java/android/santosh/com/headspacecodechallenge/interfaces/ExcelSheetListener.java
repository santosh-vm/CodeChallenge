package android.santosh.com.headspacecodechallenge.interfaces;

import android.santosh.com.headspacecodechallenge.model.ColumnTitle;
import android.santosh.com.headspacecodechallenge.model.HeaderTitle;
import android.santosh.com.headspacecodechallenge.model.TableData;

import java.util.List;

/**
 * Created by Santosh on 8/13/17.
 */

public interface ExcelSheetListener {
    void onExcelSheetLoaded(List<HeaderTitle> headerTitleList,
                            List<ColumnTitle> columnTitleList,
                            List<List<TableData.CellData>> tableDataList);
}
