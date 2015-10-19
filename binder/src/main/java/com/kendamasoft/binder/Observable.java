package com.kendamasoft.binder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by stariy on 03.10.2015.
 */
public class Observable {

    public Map<String, BaseBinding> fieldMap = new ConcurrentHashMap<>();

    public Observable() {
    }

    public void notifyFieldChange(String name) {
        BaseBinding field = fieldMap.get(name);
        if(field != null) {
            field.notifyValueChanged();
        }
    }

}
