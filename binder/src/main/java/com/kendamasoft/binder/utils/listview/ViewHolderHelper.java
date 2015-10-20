package com.kendamasoft.binder.utils.listview;

import android.content.Context;
import android.view.View;

import com.kendamasoft.binder.internal.adapter.ViewAdapter;

public abstract class ViewHolderHelper<T> implements ViewAdapter {

    protected Context context;

    public ViewHolderHelper() {

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
