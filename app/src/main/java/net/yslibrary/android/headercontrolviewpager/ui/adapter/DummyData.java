package net.yslibrary.android.headercontrolviewpager.ui.adapter;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yshrsmz on 15/01/31.
 */
public class DummyData {

    public static final int DEFAULT_ITEM_COUNT = 100;

    public static List<String> getDummyData(int num) {
        List<String> items = new ArrayList<>();

        for (int i = 1; i <= num; i++) {
            items.add(String.format("Item %d", i));
        }

        return items;
    }

    public static List<String> getDummyData() {
        return getDummyData(DEFAULT_ITEM_COUNT);
    }

    public static void setDummyData(ListView listView) {
        listView.setAdapter(
                new ArrayAdapter<>(listView.getContext(), android.R.layout.simple_list_item_1,
                        getDummyData()));
    }

}
