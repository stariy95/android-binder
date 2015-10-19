package com.kendamasoft.binder.adapters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stariy on 03.10.2015.
 */
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
