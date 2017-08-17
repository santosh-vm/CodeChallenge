package android.santosh.com.headspacecodechallenge.interfaces;

import android.santosh.com.headspacecodechallenge.model.TableData;

/**
 * Created by Santosh on 8/16/17.
 */

public interface ExcelSheetClickListener {

    void onExcelSheetContentClicked(TableData.CellData cellData, int row, int column);

    void onExcelSheetCellDataUpdated(String data, int row, int column);

}
