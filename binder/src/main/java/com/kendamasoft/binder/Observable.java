package com.kendamasoft.binder;

import com.kendamasoft.binder.internal.BaseBinding;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for model to view binding support.
 * Inherent your model class from Observable and add notifyFieldChange("fieldName") to it setters.
 *
 * class MyModel extends Observable {
 *     &#64;Bind(R.id.nameView)
 *     String name;
 *
 *     public void setName(String name) {
 *         this.name = name;
 *         notifyFieldChange("name");
 *     }
 * }
 *
 */
public class Observable {

    private Map<String, BaseBinding> fieldMap = new ConcurrentHashMap<>();

    public Observable() {
    }

    public void addBinding(String fieldName, BaseBinding binding) {
        fieldMap.put(fieldName, binding);
    }

    /**
     * Notify binder that field value has changed, so it can update bound view.
     * @param name of the field, must be the same as in java code.
     */
    public void notifyFieldChange(String name) {
        BaseBinding field = fieldMap.get(name);
        if(field != null) {
            field.notifyValueChanged();
        }
    }

}
