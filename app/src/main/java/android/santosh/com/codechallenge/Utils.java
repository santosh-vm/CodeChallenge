package android.santosh.com.codechallenge;

import android.content.Context;

import java.util.List;

/**
 * Created by Santosh on 8/11/17.
 */

public class Utils {

    public static int dp2px(int dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> int size(List<T> list) {
        int size = 0;
        if (!isEmpty(list)) {
            size = list.size();
        }
        return size;
    }
}
