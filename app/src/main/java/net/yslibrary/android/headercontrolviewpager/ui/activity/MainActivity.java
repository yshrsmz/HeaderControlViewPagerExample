package net.yslibrary.android.headercontrolviewpager.ui.activity;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import net.yslibrary.android.headercontrolviewpager.R;
import net.yslibrary.android.headercontrolviewpager.event.BusProvider;
import net.yslibrary.android.headercontrolviewpager.helper.UIHelper;
import net.yslibrary.android.headercontrolviewpager.ui.adapter.NavigationAdapter;
import net.yslibrary.android.headercontrolviewpager.ui.widget.SlidingTabLayout;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;


public class MainActivity extends ActionBarActivity implements ObservableScrollViewCallbacks {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.header_container)
    View mHeaderView;

    @InjectView(R.id.tabs)
    SlidingTabLayout mSlidingTabLayout;

    @InjectView(R.id.pager)
    ViewPager mViewPager;

    private NavigationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);

        mAdapter = new NavigationAdapter(getSupportFragmentManager());

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mViewPager.getLayoutParams();
        lp.topMargin = UIHelper.getActionBarHeight(this) * 2;
        mViewPager.setLayoutParams(lp);

        mViewPager.setAdapter(mAdapter);

        mSlidingTabLayout.setCustomTabView(R.layout.layout_tab_indicator, android.R.id.text1);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);

        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                String stateStr = "";
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stateStr = "SCROLL_STATE_DRAGGING";
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        stateStr = "SCROLL_STATE_IDLE";
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        stateStr = "SCROLL_STATE_SETTLING";
                        break;
                }
                Timber.d("onPageScrollStateChanged: %s", stateStr);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        BusProvider.get().unregister(this);

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        BusProvider.get().register(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollChanged(int i, boolean b, boolean b2) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollState == ScrollState.UP) {
            if (toolbarIsShown()) {
                hideToolbar();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (toolbarIsHidden()) {
                showToolbar();
            }
        }
    }

    private int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }

    private boolean toolbarIsShown() {
        return mHeaderView.getTranslationY() == 0;
    }

    private boolean toolbarIsHidden() {
        return mHeaderView.getTranslationY() == -mToolbar.getHeight();
    }

    private void showToolbar() {
        moveToolbar(0);
    }

    private void hideToolbar() {
        moveToolbar(-mToolbar.getHeight());
    }

    private void moveToolbar(float toTranslationY) {
        if (mHeaderView.getTranslationY() == toTranslationY) {
            return;
        }

        ValueAnimator animator = ValueAnimator
                .ofFloat(mHeaderView.getTranslationY(), toTranslationY).setDuration(200);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                mHeaderView.setTranslationY(translationY);
                mViewPager.setTranslationY(translationY);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mViewPager
                        .getLayoutParams();
                lp.height = (int) -translationY + getScreenHeight() - lp.topMargin;
                mViewPager.requestLayout();
            }
        });
        animator.start();

    }
}
