package net.yslibrary.android.headercontrolviewpager.ui.fragment;


import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;

import net.yslibrary.android.headercontrolviewpager.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ObservableWebViewFragment extends Fragment {

    @InjectView(R.id.scrollable)
    ObservableWebView mWebView;

    public ObservableWebViewFragment() {
        // Required empty public constructor
    }

    public static ObservableWebViewFragment newInstance() {
        ObservableWebViewFragment fragment = new ObservableWebViewFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_observable_web_view, container, false);

        ButterKnife.inject(this, view);

        mWebView.loadUrl("file:///android_asset/lipsum.html");

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof ObservableScrollViewCallbacks) {
            mWebView.setScrollViewCallbacks((ObservableScrollViewCallbacks) getActivity());
        }
    }
}
