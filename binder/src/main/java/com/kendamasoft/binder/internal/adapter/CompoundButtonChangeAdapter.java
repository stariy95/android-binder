package com.kendamasoft.binder.internal.adapter;

import android.widget.CompoundButton;

class CompoundButtonChangeAdapter extends ViewChangeAdapter<Boolean, CompoundButton> {

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
