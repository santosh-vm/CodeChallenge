package android.santosh.com.headspacecodechallenge.views;

import android.content.Context;
import android.os.SystemClock;
import android.santosh.com.headspacecodechallenge.interfaces.ExcelSheetClickListener;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Santosh on 8/16/17.
 */

public class ExcelSheetEditText extends AppCompatEditText {
    private static String TAG = ExcelSheetEditText.class.getSimpleName();
    private int row = 0;
    private int column = 0;
    private String data = null;
    private ExcelSheetClickListener excelSheetClickListener;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable.toString())) {
                excelSheetClickListener.onExcelSheetCellDataUpdated(null, row, column);
            } else {
                excelSheetClickListener.onExcelSheetCellDataUpdated(editable.toString(), row, column);
            }

        }
    };

    public ExcelSheetEditText(Context context) {
        super(context);

    }

    public ExcelSheetEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExcelSheetEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setExcelSheetClickListener(ExcelSheetClickListener excelSheetClickListener) {
        this.excelSheetClickListener = excelSheetClickListener;
    }

    public void setInfo(String data, int row, int column) {
        removeTextChangedListener(watcher);
        this.data = data;
        setText("");
        if (!TextUtils.isEmpty(data)) {
            setText(data);
            setSelection(getText().length());
        }
        addTextChangedListener(watcher);
        this.row = row;
        this.column = column;
        this.requestFocus();
        this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
        this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));

    }
}
