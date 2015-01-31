package net.yslibrary.android.headercontrolviewpager.ui.fragment;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;

import net.yslibrary.android.headercontrolviewpager.R;
import net.yslibrary.android.headercontrolviewpager.ui.adapter.DummyData;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ObservableListFragment extends Fragment {

    @InjectView(R.id.scrollable)
    ObservableListView mListView;

    public ObservableListFragment() {
        // Required empty public constructor
    }

    public static ObservableListFragment newInstance() {
        ObservableListFragment fragment = new ObservableListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof ObservableScrollViewCallbacks) {
            mListView.setScrollViewCallbacks((ObservableScrollViewCallbacks) getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_observable_list, container, false);

        ButterKnife.inject(this, view);

        DummyData.setDummyData(mListView);

        return view;
    }

}
