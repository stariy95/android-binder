package com.kendamasoft.binder.internal.adapter;

import java.util.ArrayList;
import java.util.List;

public class ViewChangeListener {

    public interface Callback {
        void call(Object value);
    }

    protected List<Callback> callbackList = new ArrayList<>();

    public void addCallback(Callback callback) {
        callbackList.add(callback);
    }

    public void removeCallback(Callback callback) {
        callbackList.remove(callback);
    }

    public void onViewChange(Object value) {
        for(Callback callback : callbackList) {
            callback.call(value);
        }
    }

}
