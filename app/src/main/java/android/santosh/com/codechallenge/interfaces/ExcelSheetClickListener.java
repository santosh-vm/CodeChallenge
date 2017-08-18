package android.santosh.com.codechallenge.interfaces;

import android.santosh.com.codechallenge.model.TableData;

/**
 * Created by Santosh on 8/16/17.
 */

public interface ExcelSheetClickListener {

    void onExcelSheetContentClicked(TableData.CellData cellData, int row, int column);

    void onExcelSheetCellDataUpdated(String data, int row, int column);

}
