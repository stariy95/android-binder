package com.kendamasoft.binder.internal.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class EditTextChangeAdapter extends ViewChangeAdapter<String, EditText> {

    public EditTextChangeAdapter(final EditText view, final ViewChangeListener changeListener) {
        super(view, changeListener);
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                onUpdate(s.toString());
            }
        });
    }


}
