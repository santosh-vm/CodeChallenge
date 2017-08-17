package android.santosh.com.headspacecodechallenge;

import android.os.Handler;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetListener;
import android.santosh.com.headspacecodechallenge.model.ColumnTitle;
import android.santosh.com.headspacecodechallenge.model.HeaderTitle;
import android.santosh.com.headspacecodechallenge.model.TableData;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Santosh on 8/13/17.
 */

public class HeadSpaceController {
    private static String TAG = HeadSpaceController.class.getSimpleName();
    private String alphabets = "abcdefghijklmnopqrstuvwxyz";
    private static int ROW_SIZE = 8;
    private static int COLUMN_SIZE = 8;

    private Handler uiHandler;
    private SharedPreferencesWrapper sharedPreferencesWrapper;
    private Gson gson;
    private ExecutorService executorService;
    private List<List<TableData.CellData>> tableDataList;
    private List<HeaderTitle> headerTitleList;
    private List<ColumnTitle> columnTitleList;
    private List<ExcelSheetListener> excelSheetListeners = Collections.synchronizedList(new ArrayList<ExcelSheetListener>());

    public HeadSpaceController(Handler uiHandler, SharedPreferencesWrapper sharedPreferencesWrapper) {
        this.executorService = Executors.newSingleThreadExecutor();
        this.uiHandler = uiHandler;
        this.gson = new GsonBuilder().create();
        this.sharedPreferencesWrapper = sharedPreferencesWrapper;
    }

    public void fetchExcelSheetData() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    generateHeaderData();
                    generateColumnData();
                    loadExcelSheetData();
                    notifyExcelSheetLoaded();
                    List<List<TableData.CellData>> tempTableData = gson.fromJson(gson.toJson(tableDataList,tableDataList.getClass()),tableDataList.getClass());
                    Log.d(TAG,"tempTableData: "+tempTableData.size());
                }
            });
        }

    }

    private void generateHeaderData() {
        headerTitleList = new LinkedList<>();
        for (int i = 0; i < COLUMN_SIZE; i++) {
            HeaderTitle headerTitle = new HeaderTitle();
            headerTitle.setTitle(Character.toString(Character.toUpperCase(alphabets.charAt(i))));
            headerTitleList.add(headerTitle);
        }
    }

    private void generateColumnData() {
        columnTitleList = new LinkedList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            ColumnTitle columnTitle = new ColumnTitle();
            columnTitle.setTitle(Integer.toString(i));
            columnTitleList.add(columnTitle);
        }
    }

    private void loadExcelSheetData(){
        String excelDataAsString = sharedPreferencesWrapper.getExcelSheetDataAsString();
        Log.d(TAG,"loadExcelSheetData() excelDataAsString: "+excelDataAsString);
        if(TextUtils.isEmpty(excelDataAsString)){
            generateDefaultCellData();
        } else {
            tableDataList = gson.fromJson(excelDataAsString,tableDataList.getClass());
        }
    }

    private void generateDefaultCellData() {
        tableDataList = new LinkedList<>();
        TableData tableData = new TableData();
        for (int i = 0; i < ROW_SIZE; i++) {
            List<TableData.CellData> cellDataList = new LinkedList<>();
            tableDataList.add(cellDataList);
            for (int j = 0; j < COLUMN_SIZE; j++) {
                TableData.CellData cellData = tableData.new CellData();
                cellData.setData(null);
                cellDataList.add(cellData);
            }
        }
    }

    public void addExcelSheetListener(ExcelSheetListener excelSheetListener) {
        if (excelSheetListener != null && !excelSheetListeners.contains(excelSheetListener)) {
            excelSheetListeners.add(excelSheetListener);
        }
    }

    public void removeExcelSheetListener(ExcelSheetListener excelSheetListener) {
        if (excelSheetListener != null && excelSheetListeners.contains(excelSheetListener)) {
            excelSheetListeners.remove(excelSheetListener);
        }
    }

    private void notifyExcelSheetLoaded() {
        if (excelSheetListeners != null & excelSheetListeners.size() > 0) {
            for (final ExcelSheetListener excelSheetListener : excelSheetListeners) {
                uiHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "headerTitleList.size(): " + headerTitleList.size() + ", columnTitleList.size(): " + columnTitleList.size() + ", tableDataList.size(): " + tableDataList.size());
                        excelSheetListener.onExcelSheetLoaded(headerTitleList, columnTitleList, tableDataList);
                    }
                }, 1000);
            }
        }
    }
}
