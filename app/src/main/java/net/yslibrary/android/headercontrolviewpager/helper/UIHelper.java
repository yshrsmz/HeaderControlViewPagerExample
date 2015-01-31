package net.yslibrary.android.headercontrolviewpager.helper;

import net.yslibrary.android.headercontrolviewpager.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

/**
 * Created by yshrsmz on 15/01/31.
 */
public class UIHelper {

    public static int getActionBarHeight(Context context) {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};

        int indexOfAttrTextSize = 0;

        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();

        return actionBarSize;
    }

    public static int getScreenHeight(Activity activity) {
        return activity.findViewById(android.R.id.content).getHeight();
    }

}
