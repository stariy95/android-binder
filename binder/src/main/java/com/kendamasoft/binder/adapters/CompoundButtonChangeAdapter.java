package com.kendamasoft.binder.adapters;

import android.widget.CompoundButton;

/**
 * Created by stariy on 03.10.2015.
 */
public class CompoundButtonChangeAdapter extends ViewChangeAdapter<Boolean, CompoundButton> {

    public CompoundButtonChangeAdapter(CompoundButton view, ViewChangeListener changeListener) {
        super(view, changeListener);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onUpdate(isChecked);
            }
        });
    }
}
