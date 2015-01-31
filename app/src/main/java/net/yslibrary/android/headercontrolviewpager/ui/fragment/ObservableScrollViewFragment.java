package net.yslibrary.android.headercontrolviewpager.ui.fragment;


import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;

import net.yslibrary.android.headercontrolviewpager.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ObservableScrollViewFragment extends Fragment {

    @InjectView(R.id.scrollable)
    ObservableScrollView mScrollView;

    public ObservableScrollViewFragment() {
        // Required empty public constructor
    }

    public static ObservableScrollViewFragment newInstance() {
        return new ObservableScrollViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_observable_scroll_view, container, false);

        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof ObservableScrollViewCallbacks) {
            mScrollView.setScrollViewCallbacks((ObservableScrollViewCallbacks) getActivity());
        }
    }
}
