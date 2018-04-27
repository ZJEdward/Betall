package com.betall.app.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.betall.app.R;
import com.betall.app.view.GestureView;

/**
 * Created by fly on 2018/2/5.
 */

public class GestureFragment extends BaseFragment implements GestureView.OnGestureUnlockListener {

    private GestureView gestureView;

    @Override
    protected View onCreateView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_gesture, null);
        this.gestureView = view.findViewById(R.id.gestureView);
        this.gestureView.setOnGestureUnlockListener(this);
        return view;
    }

    @Override
    public void onGestureFinish(String pwd) {
        Log.e(getClass().getSimpleName(), "onGestureFinish: " + pwd);
    }
}
