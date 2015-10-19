package com.kendamasoft.binder.adapters;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Created by stariy on 03.10.2015.
 */
public class ViewChangeListenerFactory {

    @SuppressWarnings("unchecked") // @todo safer type cast and type checking?
    static public ViewChangeAdapter getAdapterForView(View view, final ViewChangeListener listener) {
        if(view instanceof EditText) {
            return new EditTextChangeAdapter((EditText)view, listener);
        }

        if(view instanceof CompoundButton) {
            return new CompoundButtonChangeAdapter((CompoundButton)view, listener);
        }

        if(view instanceof SeekBar) {
            return new SeekBarChangeAdapter((SeekBar)view, listener);
        }

        if(view instanceof Spinner) {
            return new SpinnerChangeAdapter((Spinner)view, listener);
        }

        return null;
    }

}
