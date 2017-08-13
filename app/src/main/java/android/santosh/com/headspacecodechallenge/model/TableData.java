package android.santosh.com.headspacecodechallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

/**
 * Created by Santosh on 8/13/17.
 */

public class TableData {
    private LinkedList<RowData> rowDatas;

    public LinkedList<RowData> getRowDatas() {
        return rowDatas;
    }

    public class RowData {
        private LinkedList<CellData> cellDatas;

        public LinkedList<CellData> getCellDatas() {
            return cellDatas;
        }
    }

    public class CellData{
        @SerializedName("data")
        private String data;

        public String getData() {
            return data;
        }
    }

}
