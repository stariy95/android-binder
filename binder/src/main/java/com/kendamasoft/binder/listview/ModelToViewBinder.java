package com.kendamasoft.binder.listview;

import android.content.Context;
import android.view.View;

import com.kendamasoft.binder.adapters.ViewAdapter;

/**
 * Created by stariy on 13.10.15.
 */
public abstract class ModelToViewBinder<T> implements ViewAdapter {

    protected Context context;

    public ModelToViewBinder() {

    }

    public void applyView(T model, View rootView) {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    //public void viewToModel()
    //public void modelToView()

}
