package net.yslibrary.android.headercontrolviewpager.ui.adapter;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;

import net.yslibrary.android.headercontrolviewpager.ui.fragment.ObservableListFragment;
import net.yslibrary.android.headercontrolviewpager.ui.fragment.ObservableScrollViewFragment;
import net.yslibrary.android.headercontrolviewpager.ui.fragment.ObservableWebViewFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by yshrsmz on 15/01/31.
 */
public class NavigationAdapter extends CacheFragmentStatePagerAdapter {

    private static final String[] TITLES = new String[]{"Applepie", "Butter Cookie", "Cupcake",
            "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich",
            "Jelly Bean", "KitKat", "Lollipop"};

    private int mScrollY;

    public NavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setScrollY(int scrollY) {
        mScrollY = scrollY;
    }

    @Override
    protected Fragment createItem(int position) {
        // Initialize fragments.
        // Please be sure to pass scroll position to each fragments using setArguments.
        Fragment f;
        final int pattern = position % 3;
        switch (pattern) {
            case 0: {
                f = ObservableListFragment.newInstance();
                break;
            }
            case 1: {
                f = ObservableWebViewFragment.newInstance();
                break;
            }
            case 2:
            default: {
                f = ObservableScrollViewFragment.newInstance();
                break;
            }
        }
        return f;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
