package com.kendamasoft.binder.adapters;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by stariy on 20.10.15.
 */
public class SpinnerChangeAdapter extends ViewChangeAdapter<Integer, Spinner> {

    public SpinnerChangeAdapter(Spinner view, final ViewChangeListener changeListener) {
        super(view, changeListener);
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeListener.onViewChange(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                changeListener.onViewChange(-1);
            }
        });
    }
}
