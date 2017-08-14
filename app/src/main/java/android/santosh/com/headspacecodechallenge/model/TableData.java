package android.santosh.com.headspacecodechallenge.model;

import java.util.List;

/**
 * Created by Santosh on 8/13/17.
 */

public class TableData {
    private List<RowData> rowDatas;

    public List<RowData> getRowDatas() {
        return rowDatas;
    }

    public class RowData {
        private List<CellData> cellDatas;

        public List<CellData> getCellDatas() {
            return cellDatas;
        }
    }

    public class CellData{
        private String data;

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

}
